package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.EnergyConsumptionDTO;
import ro.tuc.ds2020.dtos.EnergyConsumptionDetailsDTO;
import ro.tuc.ds2020.entities.EnergyConsumption;

public class EnergyConsumptionBuilder {

    private EnergyConsumptionBuilder(){
    }

    public static EnergyConsumptionDTO toEnergyConsumptionDTO(EnergyConsumption energyConsumption)
    {
        return new EnergyConsumptionDTO(energyConsumption.getId(),energyConsumption.getId_device(),energyConsumption.getTimestamp(),energyConsumption.getConsumption());
    }

    public static EnergyConsumptionDetailsDTO toEnergyConsumptionDetailsDTO(EnergyConsumption energyConsumption)
    {
        return new EnergyConsumptionDetailsDTO(energyConsumption.getId(),energyConsumption.getId_device(),energyConsumption.getTimestamp(),energyConsumption.getConsumption());
    }

    public static EnergyConsumption toEntity(EnergyConsumptionDetailsDTO energyConsumptionDetailsDTO)
    {
        return new EnergyConsumption(energyConsumptionDetailsDTO.getId_device(),energyConsumptionDetailsDTO.getTimestamp(),energyConsumptionDetailsDTO.getConsumption());
    }
}
