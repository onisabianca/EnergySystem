package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.EnergyConsumption;

import javax.transaction.Transactional;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface EnergyConsumptionRepository extends JpaRepository<EnergyConsumption, UUID> {

    @Transactional
    @Modifying
    @Query(value = "Delete from EnergyConsumption d where d.id = :id")
    void deleteEnergyConsumption(@Param("id") UUID id);

    @Transactional
    @Modifying
    @Query(value = "Update EnergyConsumption d set d.id_device= :id_device, d.timestamp= :timestamp, d.consumption= :consumption where d.id = :id")
    void updateEnergyConsumption(@Param("id") UUID id, @Param("id_device") UUID id_device, @Param("timestamp") Date time, @Param("consumption") float consumption);

    @Transactional
    @Query(value = "SELECT d from EnergyConsumption d where d.id_device = :id_device and CAST(d.timestamp as date) = :timestamp")
    List<EnergyConsumption> findEnergyByDeviceAndDate(@Param("id_device") UUID id_device, @Param("timestamp") Date timestamp);

    @Transactional
    @Query(value = "SELECT max_hourly_consumption from Device d where d.id = :id_device")
    float findMaxEnergyByDevice(@Param("id_device") UUID id_device);
}
