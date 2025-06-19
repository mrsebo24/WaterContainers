package pl.kurs.watercontrainers.models;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

//datę i czas jej wykonania, jej nazwę, zbiornik na którym była ona wykonana oraz ilość wody jaka była brana pod uwagę, oraz to czy operacja się powiodła czy nie.
public class WaterContainerSaveOperations {
    private LocalDateTime localDateTime;
    private String operationName;
    private WaterContainer waterContainer;
    private double waterAmount;
    private boolean isSuccess;

    public WaterContainerSaveOperations(LocalDateTime localDateTime, String operationName, WaterContainer waterContainer, double waterAmount, boolean isSuccess) {
        this.localDateTime = localDateTime;
        this.operationName = operationName;
        this.waterContainer = waterContainer;
        this.waterAmount = waterAmount;
        this.isSuccess = isSuccess;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public WaterContainer getWaterContainer() {
        return waterContainer;
    }

    public void setWaterContainer(WaterContainer waterContainer) {
        this.waterContainer = waterContainer;
    }

    public double getWaterAmount() {
        return waterAmount;
    }

    public void setWaterAmount(double waterAmount) {
        this.waterAmount = waterAmount;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        WaterContainerSaveOperations that = (WaterContainerSaveOperations) o;
        return Double.compare(waterAmount, that.waterAmount) == 0 && isSuccess == that.isSuccess && Objects.equals(localDateTime, that.localDateTime) && Objects.equals(operationName, that.operationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(localDateTime, operationName, waterAmount, isSuccess);
    }

    @Override
    public String toString() {
        return "WaterContainerSaveOperations{" +
                "localDateTime=" + localDateTime +
                ", operationName='" + operationName + '\'' +
                ", waterContainer=" + waterContainer +
                ", waterAmount=" + waterAmount +
                ", isSuccess=" + isSuccess +
                '}';
    }
}
