package com.gojek.dto;

import java.util.Arrays;

public enum VehicleColor {
    //Add new colors
    RED,GREEN,WHITE,BLACK,BROWN,YELLOW,BLUE;


    public static VehicleColor getVehicleColor(String vehicleColor) {
        return Arrays.stream (VehicleColor.values ()).filter (vc -> vc.name ().equalsIgnoreCase (vehicleColor)).findFirst ().orElse (null);
    }

}
