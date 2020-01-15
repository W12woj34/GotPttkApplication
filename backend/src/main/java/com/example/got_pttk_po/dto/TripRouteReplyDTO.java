package com.example.got_pttk_po.dto;

import java.sql.Date;

public class TripRouteReplyDTO {

    private int numer;
    private boolean powtozona;
    private int indeks;
    private Date data;
    private int wycieczka;
    private int trasa;

    public TripRouteReplyDTO(int numer, boolean powtozona, int indeks, Date data, int wycieczka, int trasa) {
        this.numer = numer;
        this.powtozona = powtozona;
        this.indeks = indeks;
        this.data = data;
        this.wycieczka = wycieczka;
        this.trasa = trasa;
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
