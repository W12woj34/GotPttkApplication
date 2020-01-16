package com.example.got_pttk_po.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Response data for a user")
public class UserReplyDTO {

    @ApiModelProperty(notes = "Unique user id", example = "U53R")
    private String id;
    @ApiModelProperty(notes = "User name", example = "Jan")
    private String imie;
    @ApiModelProperty(notes = "User surname", example = "Kowalski")
    private String nazwisko;

    public UserReplyDTO(String id, String imie, String nazwisko) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public UserReplyDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
}
