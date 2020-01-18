package com.example.got_pttk_po.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Response data for a badge")
public class BadgeReplyDTO {
    @ApiModelProperty(notes = "Unique badge name", example = "Mała Brązowa")
    private String nazwa;
    @ApiModelProperty(notes = "Points needed to get badge", example = "300")
    private int wymaganePunkty;

    public BadgeReplyDTO(String nazwa, int wymaganePunkty) {
        this.nazwa = nazwa;
        this.wymaganePunkty = wymaganePunkty;
    }

    public BadgeReplyDTO() {
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getWymaganePunkty() {
        return wymaganePunkty;
    }

    public void setWymaganePunkty(int wymaganePunkty) {
        this.wymaganePunkty = wymaganePunkty;
    }


}
