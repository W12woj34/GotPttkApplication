package com.example.got_pttk_po.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Date;

@ApiModel(description = "Update data for a trip route")
public class TripRouteUpdateDTO {
    @ApiModelProperty(notes = "Trip route date", example = "2010-10-11")
    private Date date;

    public TripRouteUpdateDTO(Date date) {
        this.date = date;

    }

    public TripRouteUpdateDTO() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
