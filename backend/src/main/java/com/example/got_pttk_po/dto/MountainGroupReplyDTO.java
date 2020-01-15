package com.example.got_pttk_po.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Response data for a mountain group")
public class MountainGroupReplyDTO {
    @ApiModelProperty(notes = "Unique mountain group name", example = "Góry Świętokrzyskie")
    private String nazwa;

    public MountainGroupReplyDTO(String nazwa) {
        this.nazwa = nazwa;
    }

    public MountainGroupReplyDTO() {
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
}
