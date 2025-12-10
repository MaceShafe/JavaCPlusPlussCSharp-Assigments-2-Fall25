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


        index = sensor.findMinReadingIndex(24,48);
        System.out.println("\nMin: index = " + index + ", " + sensor.getSensorReadings().get(index).toString());
        index = sensor.findMaxReadingIndex(24,48);
        System.out.println("Max: index = " + index + ", " + sensor.getSensorReadings().get(index).toString());

        try {
            index = sensor.findMinReadingIndex(-1, 5000);
            System.out.println("\nMin: index = " + index + ", " + sensor.getSensorReadings().get(index).toString());
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
        index = sensor.findMaxReadingIndex(-1,2543);
        System.out.println("Max: index = " + index + ", " + sensor.getSensorReadings().get(index).toString());}
        catch (Exception e) {
            System.out.println(e);
        }

        index = sensor.findNextCycleMaxIndex(0);
        System.out.println("First max after index 0: index = " + index + ", " + sensor.getSensorReadings().get(index).toString());

        index = sensor.findNextCycleMinIndex(0);
        System.out.println("First max after index 0: index = " + index + ", " + sensor.getSensorReadings().get(index).toString());
        int prevIndex = 0;
        index = sensor.findNextCycleMaxIndex(0);
        while (index > prevIndex) {
            prevIndex = index;
            System.out.println("index = " + index + ", Max: " + sensor.getSensorReadings(index));
            index = sensor.findNextCycleMinIndex(index);
            if (index >prevIndex)
                System.out.println("index = " + index + ", Min: " + sensor.getSensorReadings(index));
            index=sensor.findNextCycleMaxIndex(index);
        }
    }
}