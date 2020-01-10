package com.example.got_pttk_po.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class ZdjecieEntityPK implements Serializable {
    private int wycieczka;
    private int indeks;

    @Column(name = "wycieczka")
    @Id
    public int getWycieczka() {
        return wycieczka;
    }

    public void setWycieczka(int wycieczka) {
        this.wycieczka = wycieczka;
    }

    @Column(name = "indeks")
    @Id
    public int getIndeks() {
        return indeks;
    }

    public void setIndeks(int indeks) {
        this.indeks = indeks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ZdjecieEntityPK that = (ZdjecieEntityPK) o;

        if (wycieczka != that.wycieczka) return false;
        if (indeks != that.indeks) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = wycieczka;
        result = 31 * result + indeks;
        return result;
    }
}
