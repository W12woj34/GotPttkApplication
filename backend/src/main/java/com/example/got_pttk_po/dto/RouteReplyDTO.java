package com.example.got_pttk_po.dto;

public class RouteReplyDTO {

    private int numer;
    private int punkty;
    private int dlugosc;
    private boolean czy_w_gore;
    private String poczatkowy;
    private String koncowy;
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
