package pl.kurs.watercontrainers.app;

import pl.kurs.watercontrainers.models.WaterContainer;
import pl.kurs.watercontrainers.service.WaterContainerService;
import java.util.Arrays;


public class App {
    public static void main(String[] args) {

        WaterContainer beczka = WaterContainer.create("beczka", 100.0, 50.0);
        WaterContainer mauser = WaterContainer.create("mauser", 1000.0, 500.0);
        WaterContainer konefka = WaterContainer.create("konefka", 100.0, 50.0);

        mauser.addWater(600);
        mauser.addWater(300);
        mauser.addWater(300);
        mauser.addWater(300);
        mauser.addWater(300);
        konefka.addWater(49);

        beczka.exchangeWater(mauser, 140);


        WaterContainerService waterContainerService = new WaterContainerService(Arrays.asList(null, null, beczka, null, mauser, konefka));



    }
}
