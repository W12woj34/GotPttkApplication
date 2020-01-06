package com.example.got_pttk_po.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Wycieczka", schema = "xnWwlKKwT8", catalog = "")
public class WycieczkaEntity {
    private int numer;
    private int status;
    private Date dataRozpoczecia;
    private Date dataZakonczenia;
    private int odznaka;
    private String przodownik;

    @Id
    @Column(name = "numer")
    public int getNumer() {
        return numer;
    }

    public void setNumer(int numer) {
        this.numer = numer;
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
    @Column(name = "data_rozpoczecia")
    public Date getDataRozpoczecia() {
        return dataRozpoczecia;
    }

    public void setDataRozpoczecia(Date dataRozpoczecia) {
        this.dataRozpoczecia = dataRozpoczecia;
    }

    @Basic
    @Column(name = "data_zakonczenia")
    public Date getDataZakonczenia() {
        return dataZakonczenia;
    }

    public void setDataZakonczenia(Date dataZakonczenia) {
        this.dataZakonczenia = dataZakonczenia;
    }

    @Basic
    @Column(name = "odznaka")
    public int getOdznaka() {
        return odznaka;
    }

    public void setOdznaka(int odznaka) {
        this.odznaka = odznaka;
    }

    @Basic
    @Column(name = "przodownik")
    public String getPrzodownik() {
        return przodownik;
    }

    public void setPrzodownik(String przodownik) {
        this.przodownik = przodownik;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WycieczkaEntity that = (WycieczkaEntity) o;

        if (numer != that.numer) return false;
        if (status != that.status) return false;
        if (odznaka != that.odznaka) return false;
        if (dataRozpoczecia != null ? !dataRozpoczecia.equals(that.dataRozpoczecia) : that.dataRozpoczecia != null)
            return false;
        if (dataZakonczenia != null ? !dataZakonczenia.equals(that.dataZakonczenia) : that.dataZakonczenia != null)
            return false;
        if (przodownik != null ? !przodownik.equals(that.przodownik) : that.przodownik != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numer;
        result = 31 * result + status;
        result = 31 * result + (dataRozpoczecia != null ? dataRozpoczecia.hashCode() : 0);
        result = 31 * result + (dataZakonczenia != null ? dataZakonczenia.hashCode() : 0);
        result = 31 * result + odznaka;
        result = 31 * result + (przodownik != null ? przodownik.hashCode() : 0);
        return result;
    }
}
