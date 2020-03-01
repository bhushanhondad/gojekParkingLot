package com.gojek.parkinglot;

import com.gojek.impl.ParkingLotImpl;
import com.gojek.utility.ParkingLotUtility;
import com.gojek.validator.ParkingLotException;
import com.gojek.validator.SingleCommandValidator;
import com.gojek.validator.ThreeCommandValidator;
import com.gojek.validator.TwoCommandValidator;
import junit.framework.TestCase;
import org.junit.Before;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Unit test for ParkingLot App.
 */
public class ParkingLotAppTest
    extends TestCase
{
    private File outputFile;
    private FileWriter writer;
    private FileReader reader;

    @Before
    protected void setUp () throws IOException {
            outputFile = new File ("./src/main/Resource/parking_lot_file_output.txt");
            reader = new FileReader ("./src/main/Resource/parking_lot_file_output.txt");
            writer = new FileWriter (outputFile);
            writer.write ("");
            writer.close ();
    }

    public void testHappyFlow()
    {
        ParkingLotImpl parkingLot = new ParkingLotImpl ();
        try {
            parkingLot.allocalteParkingSlots ("./src/main/Resource/parking_lot_file_inputs.txt");

            List<String> outputOfeachCommandList = ParkingLotUtility.getAllCommands ("./src/main/Resource/parking_lot_file_output.txt");
            String expectedOutput[] ={"Created a parking lot with 6 slots",
                    "Allocated slot number: 1",
                    "Allocated slot number: 2",
                    "Allocated slot number: 3",
                    "Allocated slot number: 4",
                    "Allocated slot number: 5",
                    "Allocated slot number: 6",
                    "Slot number: 4 is free",
                    "Slot No.  Registration No   Colour",
                    "1          KA-01-HH-1234     WHITE",
                    "2          KA-01-HH-9999     WHITE",
                    "3          KA-01-BB-0001     BLACK",
                    "5          KA-01-HH-2701     BLUE",
                    "6          KA-01-HH-3141     BLACK",
                    "Allocated slot number: 4",
                    "Parking Lot is Full....",
                    "1          KA-01-HH-1234     WHITE",
                    "2          KA-01-HH-9999     WHITE",
                    "4          KA-01-P-333     WHITE",
                    "6          KA-01-HH-3141     BLACK",
                    "Not found"};


            for(int i=0;i<outputOfeachCommandList.size ();i++)
            {
                assertEquals (expectedOutput[i],outputOfeachCommandList.get (i));
            }

        }
        catch (ParkingLotException | IOException e)
        {
            fail ();
            e.printStackTrace ();
        }
    }


    public void testnegativeCasesForSingleCommand()
    {
        try {
            SingleCommandValidator.SINGLE_COMMAND_VALIDATOR.checkIfCommandIsValid ("statuses");
            fail ();
        }
        catch (ParkingLotException e)
        {
            assertEquals ("Please check this command: statuses",e.getMessage ());
        }
    }


    public void testnegativeCasesForTwoWordCommandCreate_parking_lot()
    {
        try {
            TwoCommandValidator.TWO_COMMAND_VALIDATOR.checkIfCommandIsValid ("create_parking_lot o");
            fail ();
        }
        catch (ParkingLotException e)
        {
            assertEquals ("Please check this command: create_parking_lot o",e.getMessage ());
        }
    }

    public void testnegativeCasesForTwoWordCommandLeave()
    {
        try {
            TwoCommandValidator.TWO_COMMAND_VALIDATOR.checkIfCommandIsValid ("leave o");
            fail ();
        }
        catch (ParkingLotException e)
        {
            assertEquals ("Please check this command: leave o",e.getMessage ());
        }
    }

    public void testnegativeCasesForTwoWordCommandSlot_number_for_registration_number()
    {
        try {
            TwoCommandValidator.TWO_COMMAND_VALIDATOR.checkIfCommandIsValid ("slot_number_for_registration_number o");
            fail ();
        }
        catch (ParkingLotException e)
        {
            assertEquals ("Please check this command: slot_number_for_registration_number o",e.getMessage ());
        }
    }

    public void testnegativeCasesForTwoWordCommandSlot_numbers_for_cars_with_colour()
    {
        try {
            TwoCommandValidator.TWO_COMMAND_VALIDATOR.checkIfCommandIsValid ("slot_numbers_for_cars_with_colour o");
            fail ();
        }
        catch (ParkingLotException e)
        {
            assertEquals ("Please check this command: slot_numbers_for_cars_with_colour o",e.getMessage ());
        }
    }
    public void testnegativeCasesForThreeWordCommandSlot_numbers_for_cars_with_colour()
    {
        try {
            TwoCommandValidator.TWO_COMMAND_VALIDATOR.checkIfCommandIsValid ("slot_numbers_for_cars_with_colour o o");
            fail ();
        }
        catch (ParkingLotException e)
        {
            assertEquals ("Please check this command: slot_numbers_for_cars_with_colour o o",e.getMessage ());
        }
    }

    public void testnegativeCasesForThreeWordCommandPark()
    {
        try {
            ThreeCommandValidator.THREE_COMMAND_VALIDATOR.checkIfCommandIsValid ("park KA-22CA2001 WHITE");
            fail ();
        }
        catch (ParkingLotException e)
        {
            assertEquals ("Please check this command: park KA-22CA2001 WHITE",e.getMessage ());
        }
    }

}
