package com.gojek.impl;

import com.gojek.validator.ParkingLotException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ParkingLot {

    void allocalteParkingSlots (String fileName) throws FileNotFoundException, IOException, ParkingLotException;

}
