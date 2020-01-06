package com.example.got_pttk_po.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Trasa_Zdenifiowana", schema = "xnWwlKKwT8", catalog = "")
public class TrasaZdenifiowanaEntity {
    private int numer;

    @Id
    @Column(name = "numer")
    public int getNumer() {
        return numer;
    }

    public void setNumer(int numer) {
        this.numer = numer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrasaZdenifiowanaEntity that = (TrasaZdenifiowanaEntity) o;

        if (numer != that.numer) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return numer;
    }
}
