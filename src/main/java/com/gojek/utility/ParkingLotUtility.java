package com.gojek.utility;


import com.gojek.validator.ParkingLotException;
import com.gojek.validator.ParkingLotValidator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ParkingLotUtility {

    public static Matcher getNumberPlateMatcher (String input) {
        String numberPlateRegex = "^[A-Z]{2}[ -][0-9]{1,2}(?:[ -][A-Z])?(?:[ -][A-Z]*)?[ -][0-9]*";
        Pattern pattern = Pattern.compile (numberPlateRegex);
        return pattern.matcher (input);
    }

    public static List<String> getAllCommands (String fileName) throws IOException, ParkingLotException {
        File file = new File (fileName);

        BufferedReader bufferedReader = new BufferedReader (new FileReader (file));

        List<String> listOfCommands = new LinkedList<> ();

        String commands;

        while ((commands = bufferedReader.readLine ()) != null) {
            listOfCommands.add (commands);
        }
        return listOfCommands;
    }

}
