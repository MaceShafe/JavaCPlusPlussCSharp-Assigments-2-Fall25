package ui;

import dataaccess.SensorReadingJSONParser;
import domain.Sensor;
import domain.SensorReading;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<SensorReading> sensorReadings = new ArrayList<SensorReading>();
        Sensor sensor = new Sensor (2,1,1,"Heat register");
        try {
            SensorReadingJSONParser.readFile("Unit 1/EX2F1/EX2F1/resources/readings.json");
            sensor.setSensorReadings(SensorReadingJSONParser.getSensorReadings());
        } catch (Exception e) {
            System.out.println(e);
        }

        int index = sensor.findMinReadingIndex();
        System.out.println("\nMin: index = " + index + ", " + sensor.getSensorReadings().get(index).toString());
        index = sensor.findMaxReadingIndex();
        System.out.println("Max: index = " + index + ", " + sensor.getSensorReadings().get(index).toString());
//        for (SensorReading r : sensorReadings) {
//            System.out.println(r.toString());
//        }
    }
}