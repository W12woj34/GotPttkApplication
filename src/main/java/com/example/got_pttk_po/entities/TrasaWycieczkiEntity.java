package com.example.got_pttk_po.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Trasa_Wycieczki", schema = "xnWwlKKwT8", catalog = "")
public class TrasaWycieczkiEntity {
    private int numer;
    private boolean powtozona;
    private int indeks;
    private Date data;
    private int wycieczka;
    private int trasa;

    @Id
    @Column(name = "numer")
    public int getNumer() {
        return numer;
    }

    public void setNumer(int numer) {
        this.numer = numer;
    }

    @Basic
    @Column(name = "powtozona")
    public boolean isPowtozona() {
        return powtozona;
    }

    public void setPowtozona(boolean powtozona) {
        this.powtozona = powtozona;
    }

    @Basic
    @Column(name = "indeks")
    public int getIndeks() {
        return indeks;
    }

    public void setIndeks(int indeks) {
        this.indeks = indeks;
    }

    @Basic
    @Column(name = "data")
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Basic
    @Column(name = "wycieczka")
    public int getWycieczka() {
        return wycieczka;
    }

    public void setWycieczka(int wycieczka) {
        this.wycieczka = wycieczka;
    }

    @Basic
    @Column(name = "trasa")
    public int getTrasa() {
        return trasa;
    }

    public void setTrasa(int trasa) {
        this.trasa = trasa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrasaWycieczkiEntity that = (TrasaWycieczkiEntity) o;

        if (numer != that.numer) return false;
        if (powtozona != that.powtozona) return false;
        if (indeks != that.indeks) return false;
        if (wycieczka != that.wycieczka) return false;
        if (trasa != that.trasa) return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numer;
        result = 31 * result + (powtozona ? 1 : 0);
        result = 31 * result + indeks;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + wycieczka;
        result = 31 * result + trasa;
        return result;
    }
}
