package com.example.got_pttk_po.dto;

import java.sql.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Response data for a user badge")
public class GetBadgeReplyDTO {

    @ApiModelProperty(notes = "Unique user badge id", example = "1")
    private int id;
    @ApiModelProperty(notes = "Date of getting badge", example = "2015-03-30")
    private Date data_zdobycia;
    @ApiModelProperty(notes = "User badge status", example = "0")
    private int status;
    @ApiModelProperty(notes = "Own points for this badge", example = "153")
    private int punkty;
    @ApiModelProperty(notes = "Badge owner id", example = "7URY574")
    private String turysta;
    @ApiModelProperty(notes = "Unique badge name", example = "Mała Brązowa")
    private String odznaka;
    @ApiModelProperty(notes = "Institute id which give badge status", example = "R3F3R47")
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

    public GetBadgeReplyDTO() {
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
