package com.example.got_pttk_po.dto;

public class SubgroupReplyDTO {

    private String id;
    private String nazwa;
    private String grupa;

    public SubgroupReplyDTO(String id, String nazwa, String grupa) {
        this.id = id;
        this.nazwa = nazwa;
        this.grupa = grupa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getGrupa() {
        return grupa;
    }

    public void setGrupa(String grupa) {
        this.grupa = grupa;
    }
}
