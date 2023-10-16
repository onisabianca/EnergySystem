package ro.tuc.ds2020.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "energy_consumption")
public class EnergyConsumption implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Type(type = "uuid-binary")
    @Column(name = "id_device")
    private UUID id_device;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp", nullable = false)
    private Date timestamp;

    @Column(name = "consumption", nullable = false)
    private float consumption;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_device", insertable = false, updatable = false)
    private Device device;

    public EnergyConsumption(){
    }

    public EnergyConsumption(UUID id_device, Date timestamp, float consumption)
    {
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
