package com.gojek.validator;

public enum  ParkingLotValidator implements Validator {

    PARKING_LOT_VALIDATOR;

     public void checkIfCommandIsValid (String commands) throws ParkingLotException {
        if(commands == null)
            throw new ParkingLotException("Invalid Commmand....");

        String[] command = commands.split (" ");

         if(command.length>3 || command.length < 1)
         {
             throw new ParkingLotException("Invalid Commmand....");
         }

        if(command.length==1)
        {
            SingleCommandValidator.SINGLE_COMMAND_VALIDATOR.checkIfCommandIsValid (commands);
        }

        if(command.length==2)
        {
            TwoCommandValidator.TWO_COMMAND_VALIDATOR.checkIfCommandIsValid (commands);
        }

        if(command.length==3)
        {
            ThreeCommandValidator.THREE_COMMAND_VALIDATOR.checkIfCommandIsValid (commands);
        }

     }
}
