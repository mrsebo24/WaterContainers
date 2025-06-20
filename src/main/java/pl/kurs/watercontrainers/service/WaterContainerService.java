package pl.kurs.watercontrainers.service;

import pl.kurs.watercontrainers.models.WaterContainer;
import pl.kurs.watercontrainers.models.WaterContainerSaveOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WaterContainerService {
    private List<WaterContainer> waterContainers;

    public WaterContainerService(List<WaterContainer> waterContainers) {
        this.waterContainers = waterContainers;
    }

    //- znalezc zbiornik w ktorym jest najwiecej wody
    public Optional<WaterContainer> findWaterContainerMaxCapacity(){
        if(waterContainers.isEmpty()) return Optional.empty();

        WaterContainer result = waterContainers.getFirst();
        for (WaterContainer waterContainer : waterContainers) {
            if (waterContainer.getWaterLevel() > result.getWaterLevel()){
                result = waterContainer;
            }
        }return Optional.of(result);
    }
    //- znalezc zbiornik ktory jest najbardziej zapelniony
    //- znalezc wszystkie puste zbiorniki.
    public List<WaterContainer> findAllEmptyWaterContainer(){
        if(waterContainers.isEmpty()) return waterContainers;
        List<WaterContainer> emptyWaterContainer = new ArrayList<>();
        for (WaterContainer waterContainer : waterContainers) {
            if (waterContainer.getWaterLevel() == 0){
                emptyWaterContainer.add(waterContainer);
            }
        }return emptyWaterContainer;
    }
    //- pozwalają znaleźć zbiornik na którym było najwiecej operacji zakonczonych niepowodzeniem
    public Optional<WaterContainer> findWaterContainerWithMostNegativeOperation(){
        if(waterContainers.isEmpty()) return Optional.empty();
        int counter = 0;
        WaterContainer result = waterContainers.getFirst();

        for (WaterContainer waterContainer : waterContainers) {
            int counterNegativeOperation = counterNegativeOperation(waterContainer);
            if (counterNegativeOperation > counter){
                result = waterContainer;
                counter = counterNegativeOperation;
            }
        }return Optional.of(result);
    }

    //- pozwalają znaleźć zbiornik w którym było najwięcej operacji danego typu (typ podajemy jako argument metody)
    public Optional<WaterContainer> findWaterContainerWithMostUsedOperation(String operationName){
        if(waterContainers.isEmpty()) return Optional.empty();
        int counter = 0;
        WaterContainer result = waterContainers.getFirst();

        for (WaterContainer waterContainer : waterContainers) {
            int counterMostUsedOperation = counterMostUsedOperation(operationName, waterContainer);
            if (counterMostUsedOperation > counter){
                result = waterContainer;
                counter = counterMostUsedOperation;
            }
        }return Optional.of(result);
    }

    private int counterMostUsedOperation(String operationName, WaterContainer waterContainer) {
        int counter = 0;
        for (WaterContainerSaveOperations waterContainerSaveOperations : waterContainer.getWaterContainerSaveOperationsList()) {
            if (waterContainerSaveOperations.getOperationName().equals(operationName)){
                counter++;
            }
        }
        return counter;
    }


    private int counterNegativeOperation(WaterContainer waterContainer) {
        int counter = 0;
        for (WaterContainerSaveOperations waterContainerSaveOperations : waterContainer.getWaterContainerSaveOperationsList()) {
            if (!waterContainerSaveOperations.isSuccess()){
                counter++;
            }
        }return counter;
    }
}
