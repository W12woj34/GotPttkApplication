package com.example.got_pttk_po.entities;

import javax.persistence.*;

@Entity
@Table(name = "Punkt_Kontrolny", schema = "xnWwlKKwT8")
public class PunktKontrolnyEntity {
    private String nazwa;
    private int wysokosc;

    @Id
    @Column(name = "nazwa")
    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @Basic
    @Column(name = "wysokosc")
    public int getWysokosc() {
        return wysokosc;
    }

    public void setWysokosc(int wysokosc) {
        this.wysokosc = wysokosc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PunktKontrolnyEntity that = (PunktKontrolnyEntity) o;

        if (wysokosc != that.wysokosc) return false;
        if (nazwa != null ? !nazwa.equals(that.nazwa) : that.nazwa != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nazwa != null ? nazwa.hashCode() : 0;
        result = 31 * result + wysokosc;
        return result;
    }
}
