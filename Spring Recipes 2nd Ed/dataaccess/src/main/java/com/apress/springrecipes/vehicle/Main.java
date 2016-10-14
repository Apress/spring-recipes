package com.apress.springrecipes.vehicle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        //		  You may test various parts of the system by uncommenting the following code 
        //        VehicleDao vehicleDao = (VehicleDao) context.getBean("vehicleDao");
        //        Vehicle vehicle = new Vehicle("TEM0001", "Red", 4, 4);
        //        vehicleDao.insert(vehicle);

        //        VehicleDao vehicleDao = (VehicleDao) context.getBean("vehicleDao");
        //        Vehicle vehicle = vehicleDao.findByVehicleNo("TEM0001");
        //        System.out.println("Vehicle No: " + vehicle.getVehicleNo());
        //        System.out.println("Color: " + vehicle.getColor());
        //        System.out.println("Wheel: " + vehicle.getWheel());
        //        System.out.println("Seat: " + vehicle.getSeat());

        //        VehicleDao vehicleDao = (VehicleDao) context.getBean("vehicleDao");
        //        Vehicle vehicle1 = new Vehicle("TEM0002", "Blue", 4, 4);
        //        Vehicle vehicle2 = new Vehicle("TEM0003", "Black", 4, 6);
        //        vehicleDao.insertBatch(
        //                Arrays.asList(new Vehicle[] { vehicle1, vehicle2 }));

        //        VehicleDao vehicleDao = (VehicleDao) context.getBean("vehicleDao");
        //        List<Vehicle> vehicles = vehicleDao.findAll();
        //        for (Vehicle vehicle : vehicles) {
        //            System.out.println("Vehicle No: " + vehicle.getVehicleNo());
        //            System.out.println("Color: " + vehicle.getColor());
        //            System.out.println("Wheel: " + vehicle.getWheel());
        //            System.out.println("Seat: " + vehicle.getSeat());
        //        }

        //        VehicleDao vehicleDao = (VehicleDao) context.getBean("vehicleDao");
        //        int count = vehicleDao.countAll();
        //        System.out.println("Vehicle Count: " + count);
        //        String color = vehicleDao.getColor("TEM0001");
        //        System.out.println("Color for [TEM0001]: " + color);

        //        VehicleInsertOperation operation = 
        //            (VehicleInsertOperation) context.getBean("vehicleInsertOperation");
        //        Vehicle vehicle = new Vehicle("OBJ0001", "Red", 4, 4);
        //        operation.perform(vehicle);

        //        VehicleQueryOperation operation =
        //            (VehicleQueryOperation) context.getBean("vehicleQueryOperation");
        //        Vehicle vehicle = operation.perform("OBJ0001");
        //        System.out.println("Vehicle No: " + vehicle.getVehicleNo());
        //        System.out.println("Color: " + vehicle.getColor());
        //        System.out.println("Wheel: " + vehicle.getWheel());
        //        System.out.println("Seat: " + vehicle.getSeat());

        //        VehicleCountOperation operation =
        //            (VehicleCountOperation) context.getBean("vehicleCountOperation");
        //        int count = operation.perform();
        //        System.out.println("Vehicle Count: " + count);
        VehicleDao vehicleDao = (VehicleDao) context.getBean("vehicleDao");
        Vehicle vehicle = new Vehicle("EX0001", "Green", 4, 4);
        vehicleDao.insert(vehicle);
    }
}
