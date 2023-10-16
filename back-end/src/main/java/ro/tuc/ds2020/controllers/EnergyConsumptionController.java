package ro.tuc.ds2020.controllers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.TextMessageDTO;
import ro.tuc.ds2020.dtos.*;
import ro.tuc.ds2020.services.EnergyConsumptionService;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@EnableRabbit
@CrossOrigin()
@RequestMapping(value="/energyconsumption")
public class EnergyConsumptionController {
    private final EnergyConsumptionService energyConsumptionService;
    private final static String QUEUE_NAME="Message_Queue";

    @Autowired
    SimpMessagingTemplate template;

    @Autowired
    public EnergyConsumptionController(EnergyConsumptionService energyConsumptionService) {
        this.energyConsumptionService = energyConsumptionService;
    }


    @RabbitListener(queues="Message_Queue")
    public void listener (String message)
    {
        System.out.println(message);

        String timestamp = message.split(",")[0].split(": ")[1];
        String device_id = message.split(",")[1].split(": ")[1].split("\"")[1];
        String measurement_value = message.split(",")[2].split(": ")[1];

        Date dateTime = new Date (Long.parseLong(timestamp) * 1000);

        EnergyConsumptionDetailsDTO energyConsumptionDetailsDTO = new EnergyConsumptionDetailsDTO();
        energyConsumptionDetailsDTO.setId_device(UUID.fromString(device_id));
        energyConsumptionDetailsDTO.setTimestamp(dateTime);
        energyConsumptionDetailsDTO.setConsumption(Float.parseFloat(measurement_value));

        energyConsumptionService.insert(energyConsumptionDetailsDTO);


        float max_consumption=energyConsumptionService.findMaxEnergyByDevice(UUID.fromString(device_id));
        if(Float.parseFloat(measurement_value)>max_consumption) {
            TextMessageDTO textMessageDTO = new TextMessageDTO();
            textMessageDTO.setMessage("Valoarea "+Float.parseFloat(measurement_value)+" e prea mare pentru device-ul "+ device_id +"! "+" Valoarea maxima: "+ max_consumption);
            template.convertAndSend("/topic/message", textMessageDTO);
        }
    }

    @GetMapping()
    public ResponseEntity<List<EnergyConsumptionDTO>> getEnergyConsumptions() throws Exception{
        List<EnergyConsumptionDTO> dtos = energyConsumptionService.findEnergyConsumption();
        for (EnergyConsumptionDTO dto : dtos) {
            Link energyConsumptionLink = linkTo(methodOn(EnergyConsumptionController.class)
                    .getEnergyConsumption(dto.getId())).withRel("energyConsumptionDetails");
            dto.add(energyConsumptionLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping(value = "/insert")
    public ResponseEntity<UUID> insertEnergyConsumption(@Valid @RequestBody EnergyConsumptionDetailsDTO energyConsumptionDTO) {
        UUID energyConsumptionId = energyConsumptionService.insert(energyConsumptionDTO);
        return new ResponseEntity<>(energyConsumptionId, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EnergyConsumptionDetailsDTO> getEnergyConsumption(@PathVariable("id") UUID energyConsumptionId) {
        EnergyConsumptionDetailsDTO dto = energyConsumptionService.findEnergyConsumptionById(energyConsumptionId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<UUID> deleteEnergyConsumtpion(@PathVariable("id") UUID energyId) {
        energyConsumptionService.deleteEnergyConsumption(energyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("update/{id}")
    public ResponseEntity<UUID> updateEnergyConsumption(@PathVariable("id") UUID energyId, @Valid @RequestBody EnergyConsumptionDetailsDTO energyDTO) {
        energyConsumptionService.updateEnergyConsumption(energyId,energyDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/charts")
    public ResponseEntity<List<EnergyConsumptionDTO>> getEnergyConsumptionsForDevice(@Valid @RequestBody EnergyChart energyChart) {
        System.out.println((energyChart.id_device));
        List<EnergyConsumptionDTO> dtos = energyConsumptionService.findEnergyByDeviceAndDate(energyChart.id_device,energyChart.timestamp);
        for (EnergyConsumptionDTO dto : dtos) {
            dto.setTimestamp(dto.getTimestamp());
            Link energyLink = linkTo(methodOn(EnergyConsumptionController.class)
                    .getEnergyConsumption(dto.getId())).withRel("energyDetails");
            dto.add(energyLink);
            System.out.println(dto.getConsumption());
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}
