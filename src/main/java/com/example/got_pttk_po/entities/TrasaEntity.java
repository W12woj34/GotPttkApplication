package com.example.got_pttk_po.entities;

import javax.persistence.*;

@Entity
@Table(name = "Trasa", schema = "xnWwlKKwT8")
public class TrasaEntity {
    private int numer;
    private int punkty;
    private int dlugsc;
    private boolean czyWGore;
    private String poczatkowy;
    private String koncowy;
    private String podgrupa;

    @Id
    @Column(name = "numer")
    public int getNumer() {
        return numer;
    }

    public void setNumer(int numer) {
        this.numer = numer;
    }

    @Basic
    @Column(name = "punkty")
    public int getPunkty() {
        return punkty;
    }

    public void setPunkty(int punkty) {
        this.punkty = punkty;
    }

    @Basic
    @Column(name = "dlugsc")
    public int getDlugsc() {
        return dlugsc;
    }

    public void setDlugsc(int dlugsc) {
        this.dlugsc = dlugsc;
    }

    @Basic
    @Column(name = "czy_w_gore")
    public boolean isCzyWGore() {
        return czyWGore;
    }

    public void setCzyWGore(boolean czyWGore) {
        this.czyWGore = czyWGore;
    }

    @Basic
    @Column(name = "poczatkowy")
    public String getPoczatkowy() {
        return poczatkowy;
    }

    public void setPoczatkowy(String poczatkowy) {
        this.poczatkowy = poczatkowy;
    }

    @Basic
    @Column(name = "koncowy")
    public String getKoncowy() {
        return koncowy;
    }

    public void setKoncowy(String koncowy) {
        this.koncowy = koncowy;
    }

    @Basic
    @Column(name = "podgrupa")
    public String getPodgrupa() {
        return podgrupa;
    }

    public void setPodgrupa(String podgrupa) {
        this.podgrupa = podgrupa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrasaEntity that = (TrasaEntity) o;

        if (numer != that.numer) return false;
        if (punkty != that.punkty) return false;
        if (dlugsc != that.dlugsc) return false;
        if (czyWGore != that.czyWGore) return false;
        if (poczatkowy != null ? !poczatkowy.equals(that.poczatkowy) : that.poczatkowy != null) return false;
        if (koncowy != null ? !koncowy.equals(that.koncowy) : that.koncowy != null) return false;
        if (podgrupa != null ? !podgrupa.equals(that.podgrupa) : that.podgrupa != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numer;
        result = 31 * result + punkty;
        result = 31 * result + dlugsc;
        result = 31 * result + (czyWGore ? 1 : 0);
        result = 31 * result + (poczatkowy != null ? poczatkowy.hashCode() : 0);
        result = 31 * result + (koncowy != null ? koncowy.hashCode() : 0);
        result = 31 * result + (podgrupa != null ? podgrupa.hashCode() : 0);
        return result;
    }
}
