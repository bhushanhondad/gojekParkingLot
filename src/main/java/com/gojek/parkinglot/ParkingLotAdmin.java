package com.gojek.parkinglot;

import com.gojek.impl.ParkingLotImpl;
import com.gojek.validator.ParkingLotException;

import java.io.IOException;

/**
 *
 *
 */
public class ParkingLotAdmin
{
    public static void main( String[] args )
    {
        ParkingLotImpl parkingLot = new ParkingLotImpl ();
        try {

            parkingLot.allocalteParkingSlots ("./src/main/Resource/parking_lot_file_inputs.txt");
        }
        catch (ParkingLotException | IOException e)
        {
            e.printStackTrace ();
        }
    }
}
