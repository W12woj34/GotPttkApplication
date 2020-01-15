package com.example.got_pttk_po.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Response data for a badge")
public class BadgeReplyDTO {
    @ApiModelProperty(notes = "Unique badge name", example = "Mała Brązowa")
    private String nazwa;
    @ApiModelProperty(notes = "Points needed to get badge", example = "300")
    private int wymagane_punkty;

    public BadgeReplyDTO(String nazwa, int wymagane_punkty) {
        this.nazwa = nazwa;
        this.wymagane_punkty = wymagane_punkty;
    }

    public BadgeReplyDTO() {
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getWymagane_punkty() {
        return wymagane_punkty;
    }

    public void setWymagane_punkty(int wymagane_punkty) {
        this.wymagane_punkty = wymagane_punkty;
    }


}
