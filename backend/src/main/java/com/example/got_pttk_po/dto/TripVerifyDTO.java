package com.example.got_pttk_po.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description = "Trips data for verification")
public class TripVerifyDTO {

    @ApiModelProperty(notes = "List of trips ids to verify", example = "[1, 2, 5, 7]")
    private List<Integer> ids;

    public TripVerifyDTO(List<Integer> ids) {
        this.ids = ids;
    }

    public TripVerifyDTO() {
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
