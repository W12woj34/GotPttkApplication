package com.example.got_pttk_po.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Date;

@ApiModel(description = "Create data for a trip route")
public class TripRouteAddDTO {
    @ApiModelProperty(notes = "Trip route date", example = "2010-3-12")
    private Date date;
    @ApiModelProperty(notes = "Trio route unique trip id", example = "1")
    private Integer trip;
    @ApiModelProperty(notes = "Trip road unique road id", example = "1")
    private Integer route;

    public TripRouteAddDTO(Date date, Integer trip, Integer route) {
        this.date = date;
        this.trip = trip;
        this.route = route;
    }

    public TripRouteAddDTO() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getTrip() {
        return trip;
    }

    public void setTrip(Integer trip) {
        this.trip = trip;
    }

    public Integer getRoute() {
        return route;
    }

    public void setRoute(Integer route) {
        this.route = route;
    }
}
