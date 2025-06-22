package pl.kurs.watercontrainers.models;

import java.sql.Timestamp;
import java.util.Objects;

public class OperationLog {
    private Timestamp timestamp;
    private OperationType operationType;
    private WaterContainer waterContainer;
    private double waterAmount;
    private boolean isSuccess;

    public OperationLog(Timestamp timestamp, OperationType operationType, WaterContainer waterContainer, double waterAmount, boolean isSuccess) {
        this.timestamp = timestamp;
        this.operationType = operationType;
        this.waterContainer = waterContainer;
        this.waterAmount = waterAmount;
        this.isSuccess = isSuccess;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
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
        OperationLog that = (OperationLog) o;
        return Double.compare(waterAmount, that.waterAmount) == 0 && isSuccess == that.isSuccess && Objects.equals(timestamp, that.timestamp) && Objects.equals(operationType, that.operationType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, operationType, waterAmount, isSuccess);
    }

    @Override
    public String toString() {
        return "OperationLog{" +
                "timestamp=" + timestamp +
                ", operationType=" + operationType +
                ", waterContainer=" + waterContainer +
                ", waterAmount=" + waterAmount +
                ", isSuccess=" + isSuccess +
                '}';
    }

    public enum OperationType{
        ADD_WATER, REMOVE_WATER
    }
}
