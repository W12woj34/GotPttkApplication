package com.example.got_pttk_po.dto;

import java.sql.Date;

public class GetBadgeReplyDTO {

    private int id;
    private Date data_zdobycia;
    private int status;
    private int punkty;
    private String turysta;
    private String odznaka;
    private String referat;

    public GetBadgeReplyDTO(int id, Date data_zdobycia, int status, int punkty, String turysta, String odznaka, String referat) {
        this.id = id;
        this.data_zdobycia = data_zdobycia;
        this.status = status;
        this.punkty = punkty;
        this.turysta = turysta;
        this.odznaka = odznaka;
        this.referat = referat;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData_zdobycia() {
        return data_zdobycia;
    }

    public void setData_zdobycia(Date data_zdobycia) {
        this.data_zdobycia = data_zdobycia;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPunkty() {
        return punkty;
    }

    public void setPunkty(int punkty) {
        this.punkty = punkty;
    }

    public String getTurysta() {
        return turysta;
    }

    public void setTurysta(String turysta) {
        this.turysta = turysta;
    }

    public String getOdznaka() {
        return odznaka;
    }

    public void setOdznaka(String odznaka) {
        this.odznaka = odznaka;
    }

    public String getReferat() {
        return referat;
    }

    public void setReferat(String referat) {
        this.referat = referat;
    }
}
