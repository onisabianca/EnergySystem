package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.*;
import ro.tuc.ds2020.dtos.builders.DeviceBuilder;
import ro.tuc.ds2020.dtos.builders.EnergyConsumptionBuilder;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.EnergyConsumption;
import ro.tuc.ds2020.repositories.EnergyConsumptionRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EnergyConsumptionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EnergyConsumptionService.class);
    private final EnergyConsumptionRepository energyConsumptionRepository;

    @Autowired
    public EnergyConsumptionService(EnergyConsumptionRepository energyConsumptionRepository) {
        this.energyConsumptionRepository = energyConsumptionRepository;
    }

    public List<EnergyConsumptionDTO> findEnergyConsumption() {
        List<EnergyConsumption> energyConsumptionList = energyConsumptionRepository.findAll();
        return energyConsumptionList.stream()
                .map(EnergyConsumptionBuilder::toEnergyConsumptionDTO)
                .collect(Collectors.toList());
    }

    public EnergyConsumptionDetailsDTO findEnergyConsumptionById(UUID id) {
        Optional<EnergyConsumption> prosumerOptional = energyConsumptionRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Energy consumption with id {} was not found in db", id);
            throw new ResourceNotFoundException(EnergyConsumption.class.getSimpleName() + " with id: " + id);
        }
        return EnergyConsumptionBuilder.toEnergyConsumptionDetailsDTO(prosumerOptional.get());
    }

    public UUID insert(EnergyConsumptionDetailsDTO energyConsumptionDTO) {

        EnergyConsumption energyConsumption = EnergyConsumptionBuilder.toEntity(energyConsumptionDTO);
        energyConsumption = energyConsumptionRepository.save(energyConsumption);
        LOGGER.debug("Energy consumption with id {} was inserted in db", energyConsumption.getId());
        return energyConsumption.getId();
    }

    public void deleteEnergyConsumption(UUID id)
    {
        Optional<EnergyConsumption> energyConsumptionOptional = energyConsumptionRepository.findById(id);
        if (!energyConsumptionOptional.isPresent()) {
            LOGGER.error("Energy consumption with id {} was not found in db", id);
            throw new ResourceNotFoundException(EnergyConsumption.class.getSimpleName() + " with id: " + id);
        }
        else {
            energyConsumptionRepository.deleteEnergyConsumption(id);
        }
    }

    public void updateEnergyConsumption(UUID id, EnergyConsumptionDetailsDTO energyNou)
    {
        Optional<EnergyConsumption> energyOptional = energyConsumptionRepository.findById(id);
        if (!energyOptional.isPresent()) {
            LOGGER.error("Energy Consumption with id {} was not found in db", id);
            throw new ResourceNotFoundException(EnergyConsumption.class.getSimpleName() + " with id: " + id);
        }
        else {
            energyConsumptionRepository.updateEnergyConsumption(id,energyNou.getId_device(),energyNou.getTimestamp(),energyNou.getConsumption());
        }
    }

    public List<EnergyConsumptionDTO> findEnergyByDeviceAndDate(UUID id_device, Date timestamp)
    {
        List<EnergyConsumption> energyConsumptions = energyConsumptionRepository.findEnergyByDeviceAndDate(id_device,timestamp);
        if (energyConsumptions.isEmpty()) {
            return null;
        }
        return energyConsumptions.stream().map(EnergyConsumptionBuilder::toEnergyConsumptionDTO).collect(Collectors.toList());
    }

    public float findMaxEnergyByDevice(UUID id_device)
    {
        float max_consumption = energyConsumptionRepository.findMaxEnergyByDevice(id_device);
        return max_consumption;
    }
}
