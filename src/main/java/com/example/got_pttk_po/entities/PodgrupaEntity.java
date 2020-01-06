package com.example.got_pttk_po.entities;

import javax.persistence.*;

@Entity
@Table(name = "Podgrupa", schema = "xnWwlKKwT8", catalog = "")
public class PodgrupaEntity {
    private String id;
    private String nazwa;
    private String grupa;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nazwa")
    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @Basic
    @Column(name = "grupa")
    public String getGrupa() {
        return grupa;
    }

    public void setGrupa(String grupa) {
        this.grupa = grupa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PodgrupaEntity that = (PodgrupaEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (nazwa != null ? !nazwa.equals(that.nazwa) : that.nazwa != null) return false;
        if (grupa != null ? !grupa.equals(that.grupa) : that.grupa != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nazwa != null ? nazwa.hashCode() : 0);
        result = 31 * result + (grupa != null ? grupa.hashCode() : 0);
        return result;
    }
}
