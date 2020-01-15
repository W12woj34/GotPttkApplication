package com.example.got_pttk_po.dto;

import com.example.got_pttk_po.entities.OdznakaEntity;

public class BadgeReplyDTO {
    private String nazwa;
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
