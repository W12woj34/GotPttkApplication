package com.example.got_pttk_po.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class UprawnieniaPrzodownikaEntityPK implements Serializable {
    private String przodownik;
    private String grupa;

    @Column(name = "przodownik")
    @Id
    public String getPrzodownik() {
        return przodownik;
    }

    public void setPrzodownik(String przodownik) {
        this.przodownik = przodownik;
    }

    @Column(name = "grupa")
    @Id
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

        UprawnieniaPrzodownikaEntityPK that = (UprawnieniaPrzodownikaEntityPK) o;

        if (przodownik != null ? !przodownik.equals(that.przodownik) : that.przodownik != null) return false;
        if (grupa != null ? !grupa.equals(that.grupa) : that.grupa != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = przodownik != null ? przodownik.hashCode() : 0;
        result = 31 * result + (grupa != null ? grupa.hashCode() : 0);
        return result;
    }
}
