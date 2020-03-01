package com.gojek.validator;

import com.gojek.dto.VehicleColor;
import com.gojek.utility.ParkingLotUtility;

import java.util.regex.Matcher;

public enum  ThreeCommandValidator implements Validator{

    THREE_COMMAND_VALIDATOR;

    public void checkIfCommandIsValid (String commands) throws ParkingLotException {

        String command[] = commands.split (" ");

        if(command.length!=3)
        {
            System.out.println ("Please check this command: "+commands);
            throw new ParkingLotException ("Invalid Command...");
        }

        Matcher matcher = ParkingLotUtility.getNumberPlateMatcher (command[1]);

        if(!(command[0].equals ("park") && matcher.find () &&  VehicleColor.valueOf (command[2].toUpperCase ())!=null))
        {
            System.out.println ("Please check this command: "+commands);
            throw new ParkingLotException ("Please check this command: "+commands);
        }
    }


}
