package com.example.got_pttk_po.entities;

import javax.persistence.*;

@Entity
@Table(name = "Punkt_Kontrolny_Sasiedzi", schema = "xnWwlKKwT8")
@IdClass(PunktKontrolnySasiedziEntityPK.class)
public class PunktKontrolnySasiedziEntity {
    private String punkt;
    private String sasiad;

    @Id
    @Column(name = "punkt")
    public String getPunkt() {
        return punkt;
    }

    public void setPunkt(String punkt) {
        this.punkt = punkt;
    }

    @Id
    @Column(name = "sasiad")
    public String getSasiad() {
        return sasiad;
    }

    public void setSasiad(String sasiad) {
        this.sasiad = sasiad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PunktKontrolnySasiedziEntity that = (PunktKontrolnySasiedziEntity) o;

        if (punkt != null ? !punkt.equals(that.punkt) : that.punkt != null) return false;
        if (sasiad != null ? !sasiad.equals(that.sasiad) : that.sasiad != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = punkt != null ? punkt.hashCode() : 0;
        result = 31 * result + (sasiad != null ? sasiad.hashCode() : 0);
        return result;
    }
}
