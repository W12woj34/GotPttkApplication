package com.example.got_pttk_po.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Update data for a user badge")
public class GetBadgeUpdateDTO {
    @ApiModelProperty(notes = "New user badge name", example = "Mała Brązowa")
    private String newBadgeId;

    public GetBadgeUpdateDTO(String newBadgeId) {
        this.newBadgeId = newBadgeId;
    }

    public GetBadgeUpdateDTO() {
    }

    public String getNewBadgeId() {
        return newBadgeId;
    }

    public void setNewBadgeId(String newBadgeId) {
        this.newBadgeId = newBadgeId;
    }
}
