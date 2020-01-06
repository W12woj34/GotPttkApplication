package com.example.got_pttk_po.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Zdobywana_Odznaka", schema = "xnWwlKKwT8")
public class ZdobywanaOdznakaEntity {
    private int id;
    private Date dataZdobycia;
    private int status;
    private int punkty;
    private String turysta;
    private String odznaka;
    private String referat;

    @Id
    @Column(name = "Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "data_zdobycia")
    public Date getDataZdobycia() {
        return dataZdobycia;
    }

    public void setDataZdobycia(Date dataZdobycia) {
        this.dataZdobycia = dataZdobycia;
    }

    @Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "punkty")
    public int getPunkty() {
        return punkty;
    }

    public void setPunkty(int punkty) {
        this.punkty = punkty;
    }

    @Basic
    @Column(name = "turysta")
    public String getTurysta() {
        return turysta;
    }

    public void setTurysta(String turysta) {
        this.turysta = turysta;
    }

    @Basic
    @Column(name = "odznaka")
    public String getOdznaka() {
        return odznaka;
    }

    public void setOdznaka(String odznaka) {
        this.odznaka = odznaka;
    }

    @Basic
    @Column(name = "referat")
    public String getReferat() {
        return referat;
    }

    public void setReferat(String referat) {
        this.referat = referat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ZdobywanaOdznakaEntity that = (ZdobywanaOdznakaEntity) o;

        if (id != that.id) return false;
        if (status != that.status) return false;
        if (punkty != that.punkty) return false;
        if (dataZdobycia != null ? !dataZdobycia.equals(that.dataZdobycia) : that.dataZdobycia != null) return false;
        if (turysta != null ? !turysta.equals(that.turysta) : that.turysta != null) return false;
        if (odznaka != null ? !odznaka.equals(that.odznaka) : that.odznaka != null) return false;
        if (referat != null ? !referat.equals(that.referat) : that.referat != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dataZdobycia != null ? dataZdobycia.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + punkty;
        result = 31 * result + (turysta != null ? turysta.hashCode() : 0);
        result = 31 * result + (odznaka != null ? odznaka.hashCode() : 0);
        result = 31 * result + (referat != null ? referat.hashCode() : 0);
        return result;
    }
}
