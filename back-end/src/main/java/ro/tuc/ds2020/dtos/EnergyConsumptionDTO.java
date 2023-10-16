package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class EnergyConsumptionDTO extends RepresentationModel<EnergyConsumptionDTO> {
    private UUID id;
    private UUID id_device;
    private Date timestamp;
    private float consumption;

    public EnergyConsumptionDTO(){
    }

    public EnergyConsumptionDTO(UUID id, UUID id_device, Date timestamp, float consumption)
    {
        this.id=id;
        this.id_device=id_device;
        this.timestamp=timestamp;
        this.consumption=consumption;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId_device() {
        return id_device;
    }

    public void setId_device(UUID id_device) {
        this.id_device = id_device;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public float getConsumption() {
        return consumption;
    }

    public void setConsumption(float consumption) {
        this.consumption = consumption;
    }
}
