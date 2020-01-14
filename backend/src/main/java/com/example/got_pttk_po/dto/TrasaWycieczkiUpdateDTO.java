package com.example.got_pttk_po.dto;

import java.sql.Date;

public class TrasaWycieczkiUpdateDTO  {

    private Date date;

    public TrasaWycieczkiUpdateDTO(Date date) {
        this.date = date;

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
