package com.example.got_pttk_po.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Response data for a leader id")
public class LeaderReplyDTO {
    @ApiModelProperty(notes = "Unique leader id", example = "PR70D0WN1K")
    private String nr_licencji;

    public LeaderReplyDTO(String nr_licencji) {
        this.nr_licencji = nr_licencji;
    }

    public LeaderReplyDTO() {
    }

    public String getNr_licencji() {
        return nr_licencji;
    }

    public void setNr_licencji(String nr_licencji) {
        this.nr_licencji = nr_licencji;
    }
}
