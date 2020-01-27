package January.jan23;

import java.util.Map;
public class OOAD {

    /**
     * Question:
     * <p>
     * 1. How are ParkingSlotType and VehicleType Co-related?
     * 2.
     */

    static class ParkingLotProblem {
        enum VehicleType {
            CAR, BUS, TRACTOR, LEMO;
        }

        enum ParkingSpotType {
            ELECTRIC, MOROTBIKE, COMPACT, LARGET, HANDICAP;
        }

        enum AccountStatus {
            ACTIVE, BLOCKED, BANNED, ARCHIVED;
        }

        enum ParkingTicketStatus {
            ACTIVE, PAID, LOST
        }

        class Address {
            /**
             * address related info
             *
             * **/
        }

        class ParkingTicket {

        }

        abstract class Vehicle {
            String number;
            VehicleType vehicleType;

            ParkingTicket parkingTicket;

            public Vehicle(VehicleType vehicleType) {
                this.vehicleType = vehicleType;
            }
        }

        public void assign(ParkingTicket parkingTicket) {
//            this.parkingTicket = parkingTicket;
        }

        class Car extends Vehicle {
            public Car() {
                super(VehicleType.CAR);
            }
        }

        class Person {
            Address address;
            /**
             * Person related info
             */
        }

        class ParkingFloor {

            String name;
            Map<String, ParkingSpot> handiCapMap;
            Map<String, ParkingSpot> electricSpotMap;
            private ParkingDisplayBoard displayBoard;

            public void addParkingSpot(ParkingSpot parkingSpot) {
                switch (parkingSpot.parkingSlotType) {
                    case ELECTRIC:
                        electricSpotMap.put(parkingSpot.number, parkingSpot);
                        break;
                    case MOROTBIKE:
                        break;
                    case COMPACT:
                        break;
                    case LARGET:
                        break;
                    case HANDICAP:
                        handiCapMap.put(parkingSpot.number, parkingSpot);
                        break;
                    default:
                        System.out.println("Wrong type");
                }
            }

            public void assignVehicleToSpot(Vehicle vehicle, ParkingSpot spot) {
                spot.assign(vehicle);
                switch (spot.parkingSlotType) {
                    case ELECTRIC:
                        updateBoardForEletric(spot);
                        break;
                    case MOROTBIKE:
                        break;
                    case COMPACT:
                        break;
                    case LARGET:
                        break;
                    case HANDICAP:
                        break;
                }
            }

            private void updateBoardForEletric(ParkingSpot spot) {
                //Update electic spot information.
            }

            class ParkingSlot {

            }

            class ParkingDisplayBoard {
                private String id;
                private HandiCap handicappedFreeSpot;
                private ElectricSpot electricFreeSpot;
            }

            class CustomerInfoPanel {

            }

            class EntrancePanel {

            }

            class ExitPanel {

            }

            abstract class Account {
                String userName;
                String password;
                AccountStatus status;
                Person person;

                public abstract boolean resetPassword();
            }

            class Admin extends Account {

                public boolean addParkingFloor(ParkingFloor parkingFloor) {
                    return false;
                }

                public boolean addParkingSpot(ParkingFloor parkingFloor, ParkingSlot parkingSlot) {
                    return false;
                }

                public boolean addParkingDisplayBoard(String floorName, ParkingDisplayBoard parkingDisplayBoard) {
                    return false;
                }

                public boolean addCustomerInfoPanel(String floorName, CustomerInfoPanel parkingDisplayBoard) {
                    return false;
                }

                public boolean addEntrancePanel(String floorName, EntrancePanel entrancePanel) {
                    return false;
                }

                public boolean addExitPanel(String floorName, ExitPanel exitPanel) {
                    return false;
                }

                public boolean resetPassword() {
                    return false;
                }
            }

            class ParkingAttendant extends Account {
                boolean processTicket(String ticket) {
                    return false;
                }

                public boolean resetPassword() {
                    return false;
                }
            }

            abstract class ParkingSpot {
                String number;
                boolean free = true;
                Vehicle vehicle;
                ParkingSpotType parkingSlotType;

                public ParkingSpot(ParkingSpotType parkingSlotType) {
                    this.parkingSlotType = parkingSlotType;
                }

                public void assign(Vehicle vehicle) {
                    this.vehicle = vehicle;
                    free = false;
                }

                public void vacateSpot() {
                    free = false;
                    vehicle = null;
                }
            }

            class HandiCap extends ParkingSpot {

                public HandiCap() {
                    super(ParkingSpotType.HANDICAP);
                }
            }

            class ElectricSpot extends ParkingSpot {

                public ElectricSpot() {
                    super(ParkingSpotType.ELECTRIC);
                }
            }

        }
    }
}
