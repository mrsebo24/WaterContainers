package pl.kurs.watercontrainers.models;

import pl.kurs.watercontrainers.exceptions.InvalidCapacityException;
import pl.kurs.watercontrainers.exceptions.InvalidLevelException;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class WaterContainer implements Serializable {
    private static final long serialVersionUID = 42L;

    private String name;
    private double maxCapacity;
    private double waterLevel;
    private List<WaterContainerSaveOperations> waterContainerSaveOperationsList;

    private WaterContainer(String name, double maxCapacity, double waterLevel) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.waterLevel = waterLevel;
    }

    public static WaterContainer create(String name, double maxCapacity, double waterLevel){
        if (maxCapacity <= 0) {
            throw new InvalidCapacityException("Max capacity must by more than 0");
        }
        if (waterLevel > 0 && waterLevel > maxCapacity) {
            throw new InvalidLevelException("Invalid water level value");
        }
        return new WaterContainer(name, maxCapacity, waterLevel);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(double maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public double getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(double waterLevel) {
        this.waterLevel = waterLevel;
    }

    public List<WaterContainerSaveOperations> getWaterContainerSaveOperationsList() {
        return waterContainerSaveOperationsList;
    }

    public void setWaterContainerSaveOperationsList(List<WaterContainerSaveOperations> waterContainerSaveOperationsList) {
        this.waterContainerSaveOperationsList = waterContainerSaveOperationsList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        WaterContainer that = (WaterContainer) o;
        return Double.compare(maxCapacity, that.maxCapacity) == 0 && Double.compare(waterLevel, that.waterLevel) == 0 && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, maxCapacity, waterLevel);
    }

    @Override
    public String toString() {
        return "WaterContainer{" +
                "name='" + name + '\'' +
                ", maxCapacity=" + maxCapacity +
                ", waterLevel=" + waterLevel +
                '}';
    }
}
