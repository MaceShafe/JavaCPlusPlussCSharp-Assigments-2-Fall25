package domain;

import java.util.ArrayList;
import java.util.Objects;

public class Sensor {
    private int sensorId;
    private int roomId;
    private int sensorTypeId;
    private String description;
    private Room room;
    private SensorType sensorType;
    private ArrayList<SensorReading> sensorReadings;

    public Sensor(int sensorId, int roomId, int sensorTypeId, String description) {
        this.sensorId = sensorId;
        this.roomId = roomId;
        this.sensorTypeId = sensorTypeId;
        this.description = description;
    }

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getSensorTypeId() {
        return sensorTypeId;
    }

    public void setSensorTypeId(int sensorTypeId) {
        this.sensorTypeId = sensorTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public domain.Room getRoom() {
        return room;
    }

    public void setRoom(domain.Room room) {
        this.room = room;
    }

    public domain.SensorType getSensorType() {
        return sensorType;
    }

    public void setSensorType(domain.SensorType sensorType) {
        this.sensorType = sensorType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sensor)) return false;
        Sensor sensor = (Sensor) o;
        return sensorId == sensor.sensorId &&
                roomId == sensor.roomId &&
                sensorTypeId == sensor.sensorTypeId &&
                Objects.equals(description, sensor.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sensorId, roomId, sensorTypeId, description);
    }

    @Override
    public String toString() {
        return Integer.toString(sensorId) +
                ", roomId=" + roomId +
                ", sensorTypeId=" + sensorTypeId +
                ", " + description;
    }

    public ArrayList<SensorReading> getSensorReadings() {
        return sensorReadings;
    }

    public void setSensorReadings(ArrayList<SensorReading> sensorReadings) {
        this.sensorReadings = sensorReadings;
    }

    public int findMinReadingIndex() {
        float prevReadingLowest= 1000; //random high number that sensors should never reach
        int minReadingIndex = 0;
        int currentIndex = 0;

        for (SensorReading r : sensorReadings) {
            if (r.getValue() < prevReadingLowest) {
                prevReadingLowest = r.getValue();
                minReadingIndex = currentIndex;
            }
            currentIndex++;
        }
        return minReadingIndex;
    }

    public int findMinReadingIndex(int startIndex, int endIndex) {
        float prevReadingLowest= 1000; //random high number that sensors should never reach
        int minReadingIndex = 0;
        int currentIndex = startIndex;
        if (endIndex > sensorReadings.size() || startIndex <= 0 ||startIndex > endIndex) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        } else {
        for (SensorReading r : sensorReadings) {
            if (r.getValue() < prevReadingLowest) {
                if (currentIndex >= endIndex) {
                    break;
                } else {
                prevReadingLowest = r.getValue();
                minReadingIndex = currentIndex;
                }
            }
            currentIndex++;
        }
        return minReadingIndex;
        }
    }

    public int findMaxReadingIndex() {
        float prevReadingHighest= 0;
        int maxReadingIndex = 0;
        int currentIndex = 0;

        for (SensorReading r : sensorReadings) {
            if (r.getValue() > prevReadingHighest) {
                prevReadingHighest = r.getValue();
                maxReadingIndex = currentIndex;
            }
            currentIndex++;

        }
//        int maxReadingIndex = (int) prevReadingHighest;
        return maxReadingIndex;
    }

    public int findMaxReadingIndex(int startIndex, int endIndex) {

        float prevReadingHighest= 0;
        int maxReadingIndex = 0;
        int currentIndex = startIndex;
        if (endIndex > sensorReadings.size() || startIndex <= 0 ||startIndex > endIndex) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        } else {
            for (SensorReading r : sensorReadings) {
                if (r.getValue() > prevReadingHighest) {
                    if (currentIndex >= endIndex) {
                        break;
                    } else {
                        prevReadingHighest = r.getValue();
                        maxReadingIndex = currentIndex;
                    }
                }
                currentIndex++;

            }
//        int maxReadingIndex = (int) prevReadingHighest;
            return maxReadingIndex;
        }
    }

    public int findNextCycleMaxIndex(int startIndex) {
        SensorReading rMax = this.sensorReadings.get(startIndex);
        int i = startIndex +1;

        for (; i < this.sensorReadings.size(); i++) {
            if (rMax.getValue() < this.sensorReadings.get(i).getValue()) {
                rMax = this.sensorReadings.get(i);
            }
            else {
                break;
            }
        }
        return i-1;
    }

    public int findNextCycleMinIndex(int startIndex) {
        SensorReading rMin = this.sensorReadings.get(startIndex);
        int i = startIndex +1;

        for (; i < this.sensorReadings.size(); i++) {
            if (rMin.getValue() > this.sensorReadings.get(i).getValue()) {
                rMin = this.sensorReadings.get(i);
            }
            else {
                break;
            }
        }
        return i-1;
    }

    public SensorReading getSensorReadings(int index) {
        return this.sensorReadings.get(index);
    }


}
