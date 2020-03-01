package com.gojek.validator;

import com.gojek.dto.VehicleColor;
import com.gojek.utility.ParkingLotUtility;
import org.apache.commons.lang.StringUtils;


public enum  TwoCommandValidator implements Validator{

    TWO_COMMAND_VALIDATOR;

    public void checkIfCommandIsValid (String commands) throws ParkingLotException {

        String command[] = commands.split (" ");

        if(command.length!=2)
        {
            throw new ParkingLotException ("Please check this command: "+commands);
        }

        if((command[0].equals ("slot_numbers_for_cars_with_colour")||command[0].equals ("registration_numbers_for_cars_with_colour")))
            if( VehicleColor.getVehicleColor (command[1]) == null ) {
                System.out.println ("Please check this command: "+commands);
                throw new ParkingLotException ("Please check this command: "+commands);
            }


        if(((command[0].equals ("leave") || command[0].equals ("create_parking_lot")))) {
           if(!StringUtils.isNumeric (command[1])) {
               System.out.println ("Please check this command: "+commands);
               throw new ParkingLotException ("Please check this command: "+commands);
           }
        }


        if((command[0].equals ("slot_number_for_registration_number") ))
            if(! ParkingLotUtility.getNumberPlateMatcher (command[1]).find ()) {
                System.out.println ("Please check this command: "+commands);
                throw new ParkingLotException ("Please check this command: "+commands);
            }

    }
}
