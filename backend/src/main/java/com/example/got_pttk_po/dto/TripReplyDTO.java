package com.example.got_pttk_po.dto;

import java.sql.Date;

public class TripReplyDTO {

    private int numer;
    private int status;
    private Date data_rozpoczenia;
    private Date data_zakonczenia;
    private int odznaka;
    private String przodownik;

    public TripReplyDTO(int numer, int status, Date data_rozpoczenia, Date data_zakonczenia, int odznaka, String przodownik) {
        this.numer = numer;
        this.status = status;
        this.data_rozpoczenia = data_rozpoczenia;
        this.data_zakonczenia = data_zakonczenia;
        this.odznaka = odznaka;
        this.przodownik = przodownik;
    }

    public TripReplyDTO() {
    }

    public int getNumer() {
        return numer;
    }

    public void setNumer(int numer) {
        this.numer = numer;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getData_rozpoczenia() {
        return data_rozpoczenia;
    }

    public void setData_rozpoczenia(Date data_rozpoczenia) {
        this.data_rozpoczenia = data_rozpoczenia;
    }

    public Date getData_zakonczenia() {
        return data_zakonczenia;
    }

    public void setData_zakonczenia(Date data_zakonczenia) {
        this.data_zakonczenia = data_zakonczenia;
    }

    public int getOdznaka() {
        return odznaka;
    }

    public void setOdznaka(int odznaka) {
        this.odznaka = odznaka;
    }

    public String getPrzodownik() {
        return przodownik;
    }

    public void setPrzodownik(String przodownik) {
        this.przodownik = przodownik;
    }
}
