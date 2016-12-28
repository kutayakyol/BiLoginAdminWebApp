/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xxxt.bilogin.db.entities;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author kutay.akyol
 */
public class PersonelTip {
    BigDecimal id;
    String tanim;
    BigDecimal ekleyenid;
    BigDecimal guncelleyenid;
    Date eklemetarihi;
    Date guncellemetarihi;
    BigDecimal aktif;
    
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }
    
    public String getTanim() {
        return tanim;
    }

    public void setTanim(String tanim) {
        this.tanim = tanim;
    }

    public BigDecimal getEkleyenid() {
        return ekleyenid;
    }

    public void setEkleyenid(BigDecimal ekleyenid) {
        this.ekleyenid = ekleyenid;
    }

    public BigDecimal getGuncelleyenid() {
        return guncelleyenid;
    }

    public void setGuncelleyenid(BigDecimal guncelleyenid) {
        this.guncelleyenid = guncelleyenid;
    }

    public Date getEklemetarihi() {
        return eklemetarihi;
    }

    public void setEklemetarihi(Date eklemetarihi) {
        this.eklemetarihi = eklemetarihi;
    }

    public Date getGuncellemetarihi() {
        return guncellemetarihi;
    }

    public void setGuncellemetarihi(Date guncellemetarihi) {
        this.guncellemetarihi = guncellemetarihi;
    }

    public BigDecimal getAktif() {
        return aktif;
    }

    public void setAktif(BigDecimal aktif) {
        this.aktif = aktif;
    }
    
}
