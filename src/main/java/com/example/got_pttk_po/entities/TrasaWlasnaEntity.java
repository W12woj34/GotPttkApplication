package com.example.got_pttk_po.entities;

import javax.persistence.*;

@Entity
@Table(name = "Trasa_Wlasna", schema = "xnWwlKKwT8", catalog = "")
public class TrasaWlasnaEntity {
    private int numer;
    private int roznicaWysokosci;

    @Id
    @Column(name = "numer")
    public int getNumer() {
        return numer;
    }

    public void setNumer(int numer) {
        this.numer = numer;
    }

    @Basic
    @Column(name = "roznica_wysokosci")
    public int getRoznicaWysokosci() {
        return roznicaWysokosci;
    }

    public void setRoznicaWysokosci(int roznicaWysokosci) {
        this.roznicaWysokosci = roznicaWysokosci;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrasaWlasnaEntity that = (TrasaWlasnaEntity) o;

        if (numer != that.numer) return false;
        if (roznicaWysokosci != that.roznicaWysokosci) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numer;
        result = 31 * result + roznicaWysokosci;
        return result;
    }
}
