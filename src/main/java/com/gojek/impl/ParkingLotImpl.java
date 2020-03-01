package com.gojek.impl;

import com.gojek.dal.ParkingLotDataBase;
import com.gojek.dto.VehicleColor;
import com.gojek.dto.VehicleInfoDto;
import com.gojek.utility.ParkingLotUtility;
import com.gojek.validator.ParkingLotException;
import com.gojek.validator.ParkingLotValidator;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ParkingLotImpl implements ParkingLot {

    private  static File outputFile;
    private  static FileWriter writer;
    private static StringBuffer outputString;

    static {
        outputString = new StringBuffer ();
        outputFile = new File ("./src/main/Resource/parking_lot_file_output.txt/hwlkl/");
        try {
            writer = new FileWriter (outputFile);
        } catch (IOException e) {
            e.printStackTrace ();
        }

    }

    /**
     * Performs All the operation of the parking lot App.
     * @param fileName
     * @throws IOException
     * @throws ParkingLotException
     */
    public void allocalteParkingSlots (String fileName) throws IOException, ParkingLotException {

        List<String> listOfCommands = ParkingLotUtility.getAllCommands (fileName);

        Map<Integer, VehicleInfoDto> parkingLotStatus;

        for (String singleCommands : listOfCommands) {

            ParkingLotValidator.PARKING_LOT_VALIDATOR.checkIfCommandIsValid (singleCommands);

            String command[] = singleCommands.split (" ");

            if (command[0].equals ("create_parking_lot")) {
                ParkingLotDataBase.INSTNCE.createParkingLot (Integer.parseInt (command[1]));
                outputString.append ("Created a parking lot with " + command[1] + " slots");
                outputString.append(System.lineSeparator());
                System.out.println ("Created a parking lot with " + command[1] + " slots");

            } else if (command[0].equals ("park")) {

                VehicleInfoDto vehicleInfoDto = new VehicleInfoDto ();
                vehicleInfoDto.setColour (VehicleColor.getVehicleColor (command[2]));
                vehicleInfoDto.setRegistrationNo (command[1]);

                int vehicleAddedAtPosition = ParkingLotDataBase.INSTNCE.addNewVehileToParkingLot (vehicleInfoDto);

                if(vehicleAddedAtPosition>0)
                {
                    System.out.println ("Allocated slot number: " + vehicleAddedAtPosition);
                    outputString.append ("Allocated slot number: " + vehicleAddedAtPosition);
                    outputString.append(System.lineSeparator());
                }
                else
                {
                    System.out.println ("Parking Lot is Full....");
                    outputString.append ("Parking Lot is Full....");
                    outputString.append(System.lineSeparator());
                }


            } else if (command[0].equals ("leave")) {

                int parkingLotAccocated = ParkingLotDataBase.INSTNCE.freeVehicleFromParkingLot (Integer.parseInt (command[1]));

                if(parkingLotAccocated > 0)
                {
                    System.out.println ("Slot number: " + parkingLotAccocated + " is free");
                    outputString.append ("Slot number: " + parkingLotAccocated + " is free");
                    outputString.append(System.lineSeparator());
                }
                else
                {
                    System.out.println ("Parking Lot is already Empty...");
                    outputString.append ("Parking Lot is already Empty...");
                    outputString.append(System.lineSeparator());
                }


            } else if (command[0].equals ("status")) {
                parkingLotStatus = ParkingLotDataBase.INSTNCE.getParkingLotDtoMap ();

                System.out.println ("Slot No.  Registration No   Colour");
                outputString.append ("Slot No.  Registration No   Colour");
                outputString.append(System.lineSeparator());
                parkingLotStatus.forEach ((key,value) -> printParkingLotInfo (key,value));

            } else if (command[0].equals ("registration_numbers_for_cars_with_colour")) {
                parkingLotStatus = ParkingLotDataBase.INSTNCE.getParkingLotDtoMap ();

                slotWithColor (command[1],parkingLotStatus);
            } else if (command[0].equals ("slot_number_for_registration_number")) {
                parkingLotStatus = ParkingLotDataBase.INSTNCE.getParkingLotDtoMap ();

                slotWithRegistationNumber (command[1],parkingLotStatus);
            }
        }

        writer.write (outputString.toString ());
        writer.close ();
    }

    private void slotWithColor (String anObject,Map<Integer, VehicleInfoDto> parkingLotStatus) {
        Boolean isSlotAvailable = false;

        for (Map.Entry<Integer, VehicleInfoDto> entry : parkingLotStatus.entrySet ()) {
            Integer key = entry.getKey ();
            VehicleInfoDto value = entry.getValue ();
            if (value != null && value.getColour ().toString ().toLowerCase ().equals (anObject.toLowerCase ())) {
                printParkingLotInfo (key,value);
                isSlotAvailable = true;
            }
        }

        if (!isSlotAvailable) {
            outputString.append ("Not found");
            outputString.append(System.lineSeparator());
            System.out.println ("Not found");
        }
    }

    private void slotWithRegistationNumber (String anObject,Map<Integer, VehicleInfoDto> parkingLotStatus) {
        Boolean isSlotAvailable = false;

        for (Map.Entry<Integer, VehicleInfoDto> entry : parkingLotStatus.entrySet ()) {
            Integer key = entry.getKey ();
            VehicleInfoDto value = entry.getValue ();
            if (value != null && value.getRegistrationNo ().equals (anObject)) {
                printParkingLotInfo (key,value);
                isSlotAvailable = true;
            }
        }

        if (!isSlotAvailable) {
            outputString.append ("Not found");
            outputString.append(System.lineSeparator());
            System.out.println ("Not found");
        }
    }

    private void printParkingLotInfo (Integer key,VehicleInfoDto vehicleInfoDto) {
        if (vehicleInfoDto != null) {
            outputString.append (key + "          " + vehicleInfoDto.getRegistrationNo () + "     " + vehicleInfoDto.getColour ().toString ());
            outputString.append(System.lineSeparator());
            System.out.println (key + "          " + vehicleInfoDto.getRegistrationNo () + "     " + vehicleInfoDto.getColour ().toString ());
        }
    }
}
