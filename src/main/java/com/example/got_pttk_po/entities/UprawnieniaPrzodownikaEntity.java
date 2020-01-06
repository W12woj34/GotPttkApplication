package com.example.got_pttk_po.entities;

import javax.persistence.*;

@Entity
@Table(name = "Uprawnienia_Przodownika", schema = "xnWwlKKwT8")
@IdClass(UprawnieniaPrzodownikaEntityPK.class)
public class UprawnieniaPrzodownikaEntity {
    private String przodownik;
    private String grupa;

    @Id
    @Column(name = "przodownik")
    public String getPrzodownik() {
        return przodownik;
    }

    public void setPrzodownik(String przodownik) {
        this.przodownik = przodownik;
    }

    @Id
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

        UprawnieniaPrzodownikaEntity that = (UprawnieniaPrzodownikaEntity) o;

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
