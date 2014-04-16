package com.Doric.CarBook.search;

import java.util.ArrayList;
import java.util.Collections;

public class CarSeableData {
    public static ArrayList<CarSeable> mCarSeable;

    public static void getData() {
        mCarSeable = new ArrayList<CarSeable>();
        CarSeable a = new CarSeable("����", "100");
        CarSeable b = new CarSeable("����", "101");
        CarSeable c = new CarSeable("����", "102");
        CarSeable d = new CarSeable("����", "103");
        a.LoadCar();
        b.LoadCar();
        c.LoadCar();
        d.LoadCar();

        mCarSeable.add(a);
        mCarSeable.add(b);
        mCarSeable.add(c);
        mCarSeable.add(d);


        Collections.sort(mCarSeable, new ComparatorCarSeable());
    }

    public static CarSeable find(String cName) {
        for (CarSeable cs : mCarSeable) {
            if (cs.getCarSeableName().equals(cName))
                return cs;
        }
        return null;
    }
}
