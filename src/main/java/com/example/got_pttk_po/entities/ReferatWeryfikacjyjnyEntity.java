package com.example.got_pttk_po.entities;

import javax.persistence.*;

@Entity
@Table(name = "Referat_Weryfikacjyjny", schema = "xnWwlKKwT8")
public class ReferatWeryfikacjyjnyEntity {
    private String id;
    private int rodzaj;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "rodzaj")
    public int getRodzaj() {
        return rodzaj;
    }

    public void setRodzaj(int rodzaj) {
        this.rodzaj = rodzaj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReferatWeryfikacjyjnyEntity that = (ReferatWeryfikacjyjnyEntity) o;

        if (rodzaj != that.rodzaj) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + rodzaj;
        return result;
    }
}
