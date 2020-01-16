package com.example.got_pttk_po.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Response data for a route")
public class RouteReplyDTO {
    @ApiModelProperty(notes = "Unique route id", example = "1")
    private int numer;
    @ApiModelProperty(notes = "Number of points for a route", example = "6")
    private int punkty;
    @ApiModelProperty(notes = "Length of a route", example = "600")
    private int dlugosc;
    @ApiModelProperty(notes = "If the route is upslope", example = "true")
    private boolean czy_w_gore;
    @ApiModelProperty(notes = "Start point id", example = "P0C74TK0WY")
    private String poczatkowy;
    @ApiModelProperty(notes = "End point id", example = "K0NC0WY")
    private String koncowy;
    @ApiModelProperty(notes = "Route mountain subgroup id", example = "Ł01")
    private String podgrupa;

    public RouteReplyDTO(int numer, int punkty, int dlugosc, boolean czy_w_gore, String poczatkowy, String koncowy, String podgrupa) {
        this.numer = numer;
        this.punkty = punkty;
        this.dlugosc = dlugosc;
        this.czy_w_gore = czy_w_gore;
        this.poczatkowy = poczatkowy;
        this.koncowy = koncowy;
        this.podgrupa = podgrupa;
    }

    public RouteReplyDTO() {
    }

    public int getNumer() {
        return numer;
    }

    public void setNumer(int numer) {
        this.numer = numer;
    }

    public int getPunkty() {
        return punkty;
    }

    public void setPunkty(int punkty) {
        this.punkty = punkty;
    }

    public int getDlugosc() {
        return dlugosc;
    }

    public void setDlugosc(int dlugosc) {
        this.dlugosc = dlugosc;
    }

    public boolean isCzy_w_gore() {
        return czy_w_gore;
    }

    public void setCzy_w_gore(boolean czy_w_gore) {
        this.czy_w_gore = czy_w_gore;
    }

    public String getPoczatkowy() {
        return poczatkowy;
    }

    public void setPoczatkowy(String poczatkowy) {
        this.poczatkowy = poczatkowy;
    }

    public String getKoncowy() {
        return koncowy;
    }

    public void setKoncowy(String koncowy) {
        this.koncowy = koncowy;
    }

    public String getPodgrupa() {
        return podgrupa;
    }

    public void setPodgrupa(String podgrupa) {
        this.podgrupa = podgrupa;
    }
}
