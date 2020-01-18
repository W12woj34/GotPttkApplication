package com.example.got_pttk_po.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Date;

@ApiModel(description = "Response data for a trip")
public class TripReplyDTO {
    @ApiModelProperty(notes = "Unique trip id", example = "1")
    private int numer;
    @ApiModelProperty(notes = "Trip status; 0 - not verified, 1 - positively verified, 2 - negatively verified, 3 - forwarded", example = "01")
    private int status;
    @ApiModelProperty(notes = "Trip start date", example = "2011-11-12")
    private Date dataRozpoczecia;
    @ApiModelProperty(notes = "Trip end date", example = "2011-11-13")
    private Date dataZakonczenia;
    @ApiModelProperty(notes = "User badge id for a trip", example = "1")
    private int odznaka;
    @ApiModelProperty(notes = "Id of leader who verify trip", example = "PR70D0WN1K")
    private String przodownik;

    public TripReplyDTO(int numer, int status, Date dataRozpoczecia, Date dataZakonczenia, int odznaka, String przodownik) {
        this.numer = numer;
        this.status = status;
        this.dataRozpoczecia = dataRozpoczecia;
        this.dataZakonczenia = dataZakonczenia;
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

    public Date getDataRozpoczecia() {
        return dataRozpoczecia;
    }

    public void setDataRozpoczecia(Date dataRozpoczecia) {
        this.dataRozpoczecia = dataRozpoczecia;
    }

    public Date getDataZakonczenia() {
        return dataZakonczenia;
    }

    public void setDataZakonczenia(Date dataZakonczenia) {
        this.dataZakonczenia = dataZakonczenia;
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
