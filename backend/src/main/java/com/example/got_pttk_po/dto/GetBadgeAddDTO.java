package com.example.got_pttk_po.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Create data for a user badge")
public class GetBadgeAddDTO {
    @ApiModelProperty(notes = "Badge tourist id", example = "7URY5T4")
    private String touristId;
    @ApiModelProperty(notes = "User badge badge name", example = "Mała Brązowa")
    private String badgeId;

    GetBadgeAddDTO(String touristId, String badgeId){
        this.touristId = touristId;
        this.badgeId = badgeId;
    }

    public GetBadgeAddDTO() {
    }

    public String getTouristId() {
        return touristId;
    }

    public String getBadgeId() {
        return badgeId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public void setBadgeId(String badgeId) {
        this.badgeId = badgeId;
    }
}
