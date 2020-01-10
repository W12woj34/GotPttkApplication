package com.example.got_pttk_po.entities;

import javax.persistence.*;

@Entity
@Table(name = "Odznaka", schema = "xnWwlKKwT8")
public class OdznakaEntity {
    private String nazwa;
    private int wymaganePunkty;

    @Id
    @Column(name = "nazwa")
    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @Basic
    @Column(name = "wymagane_punkty")
    public int getWymaganePunkty() {
        return wymaganePunkty;
    }

    public void setWymaganePunkty(int wymaganePunkty) {
        this.wymaganePunkty = wymaganePunkty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OdznakaEntity that = (OdznakaEntity) o;

        if (wymaganePunkty != that.wymaganePunkty) return false;
        if (nazwa != null ? !nazwa.equals(that.nazwa) : that.nazwa != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nazwa != null ? nazwa.hashCode() : 0;
        result = 31 * result + wymaganePunkty;
        return result;
    }
}
