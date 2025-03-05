package org.learning.lld.revision_practice;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

enum VehicleType {
    CAR,
    MOTORCYCLE,
    TRUCK
}

@Getter
@AllArgsConstructor
abstract class Vehicle {
    protected String licencePlate;
    protected VehicleType type;
}
class MotorCycle extends Vehicle {
    public MotorCycle(String licencePlate) {
        super(licencePlate, VehicleType.MOTORCYCLE);
    }
}
class Car extends Vehicle {
    public Car(String licencePlate) {
        super(licencePlate, VehicleType.CAR);
    }
}
class Truck extends Vehicle {
    public Truck(String licencePlate) {
        super(licencePlate, VehicleType.TRUCK);
    }
}

// ------------------------------
@Getter
class ParkingSpot {
    private final int spotNumber;
    private final VehicleType vechileType;
    private Vehicle parkedVechile;

    public ParkingSpot(int spotNumber, VehicleType vechileType) {
        this.spotNumber = spotNumber;
        this.vechileType = vechileType;
    }

    public synchronized boolean isAvailable() {
        return parkedVechile == null;
    }

    public synchronized void parkVechile(Vehicle vehicle) {
        if (isAvailable() && vehicle.getType() == vechileType){
            parkedVechile = vehicle;
        } else {
            throw new IllegalArgumentException("Invalid vechile type or spot already occupied");
        }
    }

    public synchronized void unparkVechile() {
        parkedVechile = null;
    }
}

// ----------------------

class Level {
    private final int floor;
    private final List<ParkingSpot> parkingSpots;

    public Level(int floor, int numSpots) {
        this.floor = floor;
        parkingSpots = new ArrayList<>();

        double spotForBikes = 0.5;
        double spotForCars = 0.4;

        int numBikes = (int) (numSpots * spotForBikes);
        int numCars = (int) (numSpots * spotForCars);

        for (int i=1;i<=numBikes;i++) {
            parkingSpots.add(new ParkingSpot(i, VehicleType.MOTORCYCLE));
        }

        for (int i=1;i<=numCars;i++) {
            parkingSpots.add(new ParkingSpot(numBikes+i, VehicleType.CAR));
        }

        for (int i=numBikes + numCars +1 ; i<=numSpots;i++) {
            parkingSpots.add(new ParkingSpot(i, VehicleType.TRUCK));
        }
    }

    public synchronized boolean parkVehicle(Vehicle vehicle) {
        for (ParkingSpot spot: parkingSpots) {
            if (spot.isAvailable() && spot.getVechileType() == vehicle.getType()) {
                spot.parkVechile(vehicle);
                return true;
            }
        }

        return false;
    }

    public synchronized boolean unparkVehicle(Vehicle vehicle) {
        for (ParkingSpot spot: parkingSpots) {
            if (!spot.isAvailable() && spot.getParkedVechile().equals(vehicle)) {
                spot.unparkVechile();
                return true;
            }
        }

        return false;
    }

    public void displayAvailability() {
        System.out.println("Level: " + floor + " Availability: ");
        for (ParkingSpot spot: parkingSpots) {
            System.out.printf("Spot %d : %s [%s]\n", spot.getSpotNumber(), spot.isAvailable() ? "is available for": "is occupied by", spot.getVechileType());
        }
    }
}
//--------------------------------

class ParkingLot {
    private static ParkingLot instance;
    private final List<Level> levels;

    private ParkingLot() {
        levels = new ArrayList<>();
    }

    public static synchronized ParkingLot getInstance() {
        if (instance == null) {
            instance = new ParkingLot();
        }

        return instance;
    }

    public void addLevel(Level level) {
        levels.add(level);
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for (Level level: levels) {
            if (level.parkVehicle(vehicle)) {
                System.out.println("Vehicle parked successfully.");
                return true;
            }
        }

        System.out.println("Couldn't park vehicle.");
        return false;
    }

    public boolean unparkVehicle(Vehicle vehicle) {
        for (Level level: levels) {
            if (level.unparkVehicle(vehicle)) {
                return true;
            }
        }
        return false;
    }

    public void displayAvailability() {
        for (Level level: levels) {
            level.displayAvailability();
        }
    }
}

public class ParkingLotProblemPractice {
    public static void main(String[] args) {
        ParkingLot parkingLot = ParkingLot.getInstance();
        parkingLot.addLevel(new Level(1,100));
        parkingLot.addLevel(new Level(2,80));

        Vehicle car = new Car("ABC123");
        Vehicle truck = new Truck("XYZ12443");
        Vehicle motorcycle = new MotorCycle("m1243");

        parkingLot.parkVehicle(car);
        parkingLot.parkVehicle(truck);
        parkingLot.parkVehicle(motorcycle);

        parkingLot.displayAvailability();
    }
}
