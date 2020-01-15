package com.example.got_pttk_po.dto;

public class NewGetBadgeDTO {

    private String touristId;
    private String badgeId;

    NewGetBadgeDTO(String touristId, String badgeId){
        this.touristId = touristId;
        this.badgeId = badgeId;
    }

    public NewGetBadgeDTO() {
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
