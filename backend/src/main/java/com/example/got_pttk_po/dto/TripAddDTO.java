package com.example.got_pttk_po.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Create data for a trip")
public class TripAddDTO {

    @ApiModelProperty(notes = "User badge for trip id", example = "1")
    private int newTripGetBadge;

    public TripAddDTO(int newTripGetBadge) {
        this.newTripGetBadge = newTripGetBadge;
    }

    public TripAddDTO() {
    }

    public int getNewTripGetBadge() {
        return newTripGetBadge;
    }

    public void setNewTripGetBadge(int newTripGetBadge) {
        this.newTripGetBadge = newTripGetBadge;
    }

}
