package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;
import java.util.UUID;

public class DeviceDTO extends RepresentationModel<DeviceDTO> {

    private UUID id;
    private UUID id_user;
    private String description;
    private String address;
    private float max_hourly_consumption;

    public DeviceDTO(){
    }

    public DeviceDTO(UUID id, UUID id_user, String description,String address, float max_hourly_consumption)
    {
        this.id=id;
        this.id_user=id_user;
        this.description=description;
        this.address=address;
        this.max_hourly_consumption=max_hourly_consumption;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId_user() {
        return id_user;
    }

    public void setId_user(UUID id_user) {
        this.id_user = id_user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getMax_hourly_consumption() {
        return max_hourly_consumption;
    }

    public void setMax_hourly_consumption(float max_hourly_consumption) {
        this.max_hourly_consumption = max_hourly_consumption;
    }
}
