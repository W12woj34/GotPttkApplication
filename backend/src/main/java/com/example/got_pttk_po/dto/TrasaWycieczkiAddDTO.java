package com.example.got_pttk_po.dto;

import java.sql.Date;

public class TrasaWycieczkiAddDTO {

    private Date date;
    private Integer trip;
    private Integer route;

    public TrasaWycieczkiAddDTO(Date date, Integer trip, Integer route) {
        this.date = date;
        this.trip = trip;
        this.route = route;
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
