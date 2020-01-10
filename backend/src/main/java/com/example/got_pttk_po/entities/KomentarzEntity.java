package com.example.got_pttk_po.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Komentarz", schema = "xnWwlKKwT8")
public class KomentarzEntity {
    private int id;
    private String tresc;
    private Date dataDodania;
    private int trasa;
    private String autor;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "tresc")
    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    @Basic
    @Column(name = "data_dodania")
    public Date getDataDodania() {
        return dataDodania;
    }

    public void setDataDodania(Date dataDodania) {
        this.dataDodania = dataDodania;
    }

    @Basic
    @Column(name = "trasa")
    public int getTrasa() {
        return trasa;
    }

    public void setTrasa(int trasa) {
        this.trasa = trasa;
    }

    @Basic
    @Column(name = "autor")
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KomentarzEntity that = (KomentarzEntity) o;

        if (id != that.id) return false;
        if (trasa != that.trasa) return false;
        if (tresc != null ? !tresc.equals(that.tresc) : that.tresc != null) return false;
        if (dataDodania != null ? !dataDodania.equals(that.dataDodania) : that.dataDodania != null) return false;
        if (autor != null ? !autor.equals(that.autor) : that.autor != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (tresc != null ? tresc.hashCode() : 0);
        result = 31 * result + (dataDodania != null ? dataDodania.hashCode() : 0);
        result = 31 * result + trasa;
        result = 31 * result + (autor != null ? autor.hashCode() : 0);
        return result;
    }
}
