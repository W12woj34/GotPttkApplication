package com.example.got_pttk_po.dto;

public class LeaderReplyDTO {

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
