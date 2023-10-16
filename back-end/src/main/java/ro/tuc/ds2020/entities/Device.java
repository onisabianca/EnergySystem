package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "device")
public class Device implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Type(type = "uuid-binary")
    @Column(name = "id_user")
    private UUID id_user;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "max_hourly_consumption", nullable = false)
    private float max_hourly_consumption;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_user", insertable = false, updatable = false)
    private Users user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="device")
    private List<EnergyConsumption> energyConsumptions;

    public Device()
    {}

    public Device(UUID id_user,String description, String address, float max_hourly_consumption)
    {
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
