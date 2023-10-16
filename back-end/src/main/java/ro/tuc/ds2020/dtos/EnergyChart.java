package ro.tuc.ds2020.dtos;

import java.util.Date;
import java.util.UUID;

public class EnergyChart {
    public UUID id_device;
    public Date timestamp;

    public EnergyChart(UUID id_device, Date timestamp)
    {
        this.id_device=id_device;
        this.timestamp=timestamp;
    }
}
