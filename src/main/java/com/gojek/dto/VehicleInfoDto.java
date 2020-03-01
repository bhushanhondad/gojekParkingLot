package com.gojek.dto;

public class VehicleInfoDto {

    private String registrationNo;

    private VehicleColor colour;

    private Integer slotNo;

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public VehicleColor getColour() {
        return colour;
    }

    public void setColour(VehicleColor colour) {
        this.colour = colour;
    }

    public Integer getSlotNo() {
        return slotNo;
    }

    public void setSlotNo(Integer slotNo) {
        this.slotNo = slotNo;
    }
}
