package com.gojek.validator;

public enum  SingleCommandValidator implements Validator {

    SINGLE_COMMAND_VALIDATOR;

    public void checkIfCommandIsValid (String commands) throws ParkingLotException {

        String command[] = commands.split (" ");

        if(command.length!=1)
        {
            throw new ParkingLotException ("Invalid Command...");
        }

        if(!commands.equals ("status"))
        {
            throw new ParkingLotException ("Please check this command: "+commands);
        }
    }
}
