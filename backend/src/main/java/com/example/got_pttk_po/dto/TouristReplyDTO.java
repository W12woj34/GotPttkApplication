package com.example.got_pttk_po.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Response data for a tourist id")
public class TouristReplyDTO {
    @ApiModelProperty(notes = "Unique tourist id", example = "7URY574")
    private String nazwa;

    public TouristReplyDTO(String nazwa) {
        this.nazwa = nazwa;
    }

    public TouristReplyDTO() {
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
}
