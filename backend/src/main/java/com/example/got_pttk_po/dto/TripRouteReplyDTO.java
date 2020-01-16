package com.example.got_pttk_po.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Date;

@ApiModel(description = "Response data for a trip route")
public class TripRouteReplyDTO {

    @ApiModelProperty(notes = "Unique trip route id", example = "1")
    private int numer;
    @ApiModelProperty(notes = "If route is repeat", example = "true")
    private boolean powtozona;
    @ApiModelProperty(notes = "Trip route in the trip index", example = "7")
    private int indeks;
    @ApiModelProperty(notes = "Trip route date", example = "2011-11-21")
    private Date data;
    @ApiModelProperty(notes = "Trip route trip id", example = "1")
    private int wycieczka;
    @ApiModelProperty(notes = "Trip route route id", example = "1")
    private int trasa;

    public TripRouteReplyDTO(int numer, boolean powtozona, int indeks, Date data, int wycieczka, int trasa) {
        this.numer = numer;
        this.powtozona = powtozona;
        this.indeks = indeks;
        this.data = data;
        this.wycieczka = wycieczka;
        this.trasa = trasa;
    }

    public TripRouteReplyDTO() {
    }

    public int getNumer() {
        return numer;
    }

    public void setNumer(int numer) {
        this.numer = numer;
    }

    public boolean isPowtozona() {
        return powtozona;
    }

    public void setPowtozona(boolean powtozona) {
        this.powtozona = powtozona;
    }

    public int getIndeks() {
        return indeks;
    }

    public void setIndeks(int indeks) {
        this.indeks = indeks;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getWycieczka() {
        return wycieczka;
    }

    public void setWycieczka(int wycieczka) {
        this.wycieczka = wycieczka;
    }

    public int getTrasa() {
        return trasa;
    }

    public void setTrasa(int trasa) {
        this.trasa = trasa;
    }
}
