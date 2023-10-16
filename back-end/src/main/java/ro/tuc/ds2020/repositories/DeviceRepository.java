package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.Users;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public interface DeviceRepository extends JpaRepository<Device, UUID> {

    @Transactional
    @Modifying
    @Query(value = "Delete from Device d where d.id = :id")
    void deleteDevice(@Param("id") UUID id);

    @Transactional
    @Modifying
    @Query(value = "Update EnergyConsumption d set id_device=null where d.id_device = :id")
    void updateEnergyForDevice(@Param("id") UUID id);

    @Transactional
    @Modifying
    @Query(value = "Update Device d set d.id_user= :id_user, d.description= :description, d.address= :address, d.max_hourly_consumption= :max_cons where d.id = :id")
    void updateDevice(@Param("id") UUID id, @Param("id_user") UUID id_user, @Param("description") String description, @Param("address") String address, @Param("max_cons") float max_consumption);

    @Transactional
    @Query(value = "SELECT d from Device d where d.id_user = :id_user")
    List<Device> findDevicesByUser(@Param("id_user") UUID id_user);
}
