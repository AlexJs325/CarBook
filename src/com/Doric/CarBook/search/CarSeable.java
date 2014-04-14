package com.Doric.CarBook.search;

import java.util.ArrayList;
import java.util.Collections;


class CarSeable {

    private ArrayList<CarInfor> carList;    //����list
    private String carSeableName; //������
    private String carSeableId;        //id
    private int count;   ///������

    //��ʼ��
    public CarSeable(String carSeableName, String id) {
        this.carSeableName = carSeableName;
        this.carSeableId = id;

    }


    public void LoadCar() {
        carList = new ArrayList<CarInfor>();
        if (carSeableName.equals("����")) {
            for (int i = 1; i < 10; i++) {
                carList.add(new CarInfor(i, "M", "test", "test", 200.0, "D" + i, 20.0, 100.0, 200.0));
                count++;
            }
        } else if (carSeableName.equals("����")) {
            for (int i = 1; i < 10; i++) {
                carList.add(new CarInfor(i, "S", "test", "test", 200.0, "F" + i, 20.0, 100.0, 200.0));
                count++;
            }
        } else if (carSeableName.equals("����")) {
            for (int i = 1; i < 10; i++) {
                carList.add(new CarInfor(i, "S", "test", "test", 200.0, "B" + i, 20.0, 100.0, 200.0));
                count++;
            }
        } else if (carSeableName.equals("����")) {
            for (int i = 1; i < 10; i++) {
                carList.add(new CarInfor(i, "L", "test", "test", 200.0, "S" + i, 20.0, 100.0, 200.0));
                count++;
            }
        }
        Collections.sort(carList, new ComparatorCarInfo());
    }


    public ArrayList<CarInfor> getCarList() {
        return carList;
    }

    public void setCarList(ArrayList<CarInfor> carList) {
        this.carList = carList;
    }

    public String getCarSeableName() {
        return carSeableName;
    }

    public void setCarSeableName(String carSeableName) {
        this.carSeableName = carSeableName;
    }

    public String getCarSeableId() {
        return carSeableId;
    }

    public void setCsId(String csId) {
        this.carSeableId = csId;
    }


}









	

