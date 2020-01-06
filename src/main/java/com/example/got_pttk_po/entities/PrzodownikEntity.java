package com.example.got_pttk_po.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Przodownik", schema = "xnWwlKKwT8")
public class PrzodownikEntity {
    private String nrLicencji;

    @Id
    @Column(name = "nr_licencji")
    public String getNrLicencji() {
        return nrLicencji;
    }

    public void setNrLicencji(String nrLicencji) {
        this.nrLicencji = nrLicencji;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrzodownikEntity that = (PrzodownikEntity) o;

        if (nrLicencji != null ? !nrLicencji.equals(that.nrLicencji) : that.nrLicencji != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return nrLicencji != null ? nrLicencji.hashCode() : 0;
    }
}
