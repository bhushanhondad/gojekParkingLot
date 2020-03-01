package com.gojek.dal;


import com.gojek.dto.VehicleInfoDto;
import com.gojek.validator.ParkingLotException;

import java.util.*;

public enum ParkingLotDataBase {

    INSTNCE;

    private Map<Integer, VehicleInfoDto> parkingLotDtoMap;
    private List<Integer> freeParkingLots;

    public Map<Integer, VehicleInfoDto> createParkingLot (int parkingLotSize) {
        if (parkingLotDtoMap == null) {
            return intialParkingLotAssignment (parkingLotSize);
        }

        return parkingLotDtoMap;
    }

    public Map<Integer, VehicleInfoDto> intialParkingLotAssignment (int parkingLotSize) {
        parkingLotDtoMap = new HashMap<Integer, VehicleInfoDto> (parkingLotSize);
        freeParkingLots = new LinkedList<Integer> ();

        for (int i = 1 ; i <= parkingLotSize ; i++) {
            freeParkingLots.add (i);
            parkingLotDtoMap.put (i, null);
        }

        return parkingLotDtoMap;
    }

    public Map<Integer, VehicleInfoDto> getParkingLotDtoMap () {
        return parkingLotDtoMap;
    }

    public int addNewVehileToParkingLot (VehicleInfoDto vehicleInfoDto) throws ParkingLotException {
        if (freeParkingLots.isEmpty ()) {
            return -1;
        }

        Integer freeParkingSpace = freeParkingLots.get (0);
        vehicleInfoDto.setSlotNo (freeParkingSpace);
        freeParkingLots.remove (0);
        parkingLotDtoMap.put (freeParkingSpace, vehicleInfoDto);

        return freeParkingSpace;
    }

    public int freeVehicleFromParkingLot (Integer parkingLotNo) throws ParkingLotException {
        if (parkingLotDtoMap.get (parkingLotNo) == null) {
            return -1;
        }

        freeParkingLots.add (parkingLotNo);
        parkingLotDtoMap.put (parkingLotNo, null);

        return parkingLotNo;
    }
}
