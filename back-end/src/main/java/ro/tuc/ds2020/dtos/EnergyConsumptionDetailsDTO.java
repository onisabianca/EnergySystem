package ro.tuc.ds2020.dtos;

import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class EnergyConsumptionDetailsDTO {
    private UUID id;

    private UUID id_device;
    @NotNull
    private Date timestamp;
    @NotNull
    private float consumption;

    public EnergyConsumptionDetailsDTO(){
    }

    public EnergyConsumptionDetailsDTO(UUID id_device, Date timestamp, float consumption)
    {
        this.id_device=id_device;
        this.timestamp=timestamp;
        this.consumption=consumption;
    }

    public EnergyConsumptionDetailsDTO(UUID id,UUID id_device, Date timestamp, float consumption)
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
