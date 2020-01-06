package com.example.got_pttk_po.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Grupa_Gorska", schema = "xnWwlKKwT8", catalog = "")
public class GrupaGorskaEntity {
    private String nazwa;

    @Id
    @Column(name = "nazwa")
    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GrupaGorskaEntity that = (GrupaGorskaEntity) o;

        if (nazwa != null ? !nazwa.equals(that.nazwa) : that.nazwa != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return nazwa != null ? nazwa.hashCode() : 0;
    }
}
