package pl.kurs.watercontrainers.service;

import pl.kurs.watercontrainers.models.WaterContainer;
import pl.kurs.watercontrainers.models.OperationLog;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WaterContainerService {
    private final List<WaterContainer> waterContainers;

    public WaterContainerService(List<WaterContainer> waterContainers) {
        this.waterContainers = removeAllNullObjectsFromList(waterContainers);
    }

    private List<WaterContainer> removeAllNullObjectsFromList(List<WaterContainer> waterContainers) {
        List<WaterContainer> list1 = new ArrayList<>();
        for (WaterContainer waterContainer : waterContainers) {
            if (waterContainer != null){
                list1.add(waterContainer);
            }
        }return list1;
    }

    public Optional<WaterContainer> findContainerWithTheBiggestAmountOfWater() {
        if (waterContainers.isEmpty()) return Optional.empty();
        WaterContainer maxWaterContainer = waterContainers.getFirst();
        for (WaterContainer waterContainer : waterContainers) {
            if (waterContainer.getWaterLevel() > maxWaterContainer.getWaterLevel()){
                maxWaterContainer = waterContainer;
            }
        }
        return Optional.ofNullable(maxWaterContainer);
    }

    public Optional<WaterContainer> findMostFilledWaterContainer(){
        if (waterContainers.isEmpty()) return Optional.empty();
        WaterContainer mostFilledWaterContainer = waterContainers.getFirst();
        double freeSpace = mostFilledWaterContainer.getWaterLevel() - mostFilledWaterContainer.getMaxCapacity();
        for (WaterContainer waterContainer : waterContainers) {
            double currentFreeSpace = waterContainer.getWaterLevel() - waterContainer.getMaxCapacity();
            if (freeSpace < currentFreeSpace){
                freeSpace = currentFreeSpace;
                mostFilledWaterContainer = waterContainer;
            }
        }return Optional.of(mostFilledWaterContainer);
    }

    public List<WaterContainer> findAllEmptyWaterLevelInWaterContainer() {
        if (waterContainers.isEmpty()) return waterContainers;
        List<WaterContainer> emptyWaterContainer = new ArrayList<>();
        for (WaterContainer waterContainer : waterContainers) {
            if (waterContainer.getWaterLevel() == 0) {
                emptyWaterContainer.add(waterContainer);
            }
        }
        return emptyWaterContainer;
    }

    public Optional<WaterContainer> findWaterContainerWithMostNegativeOperation() {
        if (waterContainers.isEmpty()) return Optional.empty();
        int counter = 0;
        WaterContainer result = waterContainers.getFirst();

        for (WaterContainer waterContainer : waterContainers) {
            if (!waterContainer.getWaterContainerSaveOperationsList().isEmpty()){
                int counterNegativeOperation = counterNegativeOperation(waterContainer);
                if (counterNegativeOperation > counter) {
                    result = waterContainer;
                    counter = counterNegativeOperation;
                }
            }
        }
        return Optional.of(result);
    }

    public Optional<WaterContainer> findWaterContainerWithMostUsedOperation(OperationLog.OperationType operationType) {
        if (waterContainers.isEmpty()) return Optional.empty();
        int counter = 0;
        WaterContainer result = waterContainers.getFirst();

        for (WaterContainer waterContainer : waterContainers) {
            if (!waterContainer.getWaterContainerSaveOperationsList().isEmpty()){
                int counterMostUsedOperation = counterMostUsedOperation(operationType, waterContainer);
                if (counterMostUsedOperation > counter) {
                    result = waterContainer;
                    counter = counterMostUsedOperation;
                }
            }
        }
        return Optional.of(result);
    }

    private int counterMostUsedOperation(OperationLog.OperationType operationType, WaterContainer waterContainer) {
        int counter = 0;
        for (OperationLog operationLog : waterContainer.getWaterContainerSaveOperationsList()) {
            if (operationLog.getOperationType().equals(operationType)) {
                counter++;
            }
        }
        return counter;
    }


    private int counterNegativeOperation(WaterContainer waterContainer) {
        int counter = 0;
        for (OperationLog operationLog : waterContainer.getWaterContainerSaveOperationsList()) {
            if (!operationLog.isSuccess()) {
                counter++;
            }
        }
        return counter;
    }
}
