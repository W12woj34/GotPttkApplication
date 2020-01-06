package com.example.got_pttk_po.entities;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "Zdjecie", schema = "xnWwlKKwT8")
@IdClass(ZdjecieEntityPK.class)
public class ZdjecieEntity {
    private int wycieczka;
    private int indeks;
    private byte[] zdjecie;

    @Id
    @Column(name = "wycieczka")
    public int getWycieczka() {
        return wycieczka;
    }

    public void setWycieczka(int wycieczka) {
        this.wycieczka = wycieczka;
    }

    @Id
    @Column(name = "indeks")
    public int getIndeks() {
        return indeks;
    }

    public void setIndeks(int indeks) {
        this.indeks = indeks;
    }

    @Basic
    @Column(name = "zdjecie")
    public byte[] getZdjecie() {
        return zdjecie;
    }

    public void setZdjecie(byte[] zdjecie) {
        this.zdjecie = zdjecie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ZdjecieEntity that = (ZdjecieEntity) o;

        if (wycieczka != that.wycieczka) return false;
        if (indeks != that.indeks) return false;
        if (!Arrays.equals(zdjecie, that.zdjecie)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = wycieczka;
        result = 31 * result + indeks;
        result = 31 * result + Arrays.hashCode(zdjecie);
        return result;
    }
}
