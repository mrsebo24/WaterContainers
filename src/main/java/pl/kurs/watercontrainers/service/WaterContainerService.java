package pl.kurs.watercontrainers.service;

import pl.kurs.watercontrainers.models.WaterContainer;

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
    //- pozwalają znaleźć zbiornik w którym było najwięcej operacji danego typu (typ podajemy jako argument metody)
}
