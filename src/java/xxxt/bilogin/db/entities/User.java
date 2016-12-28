/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xxxt.bilogin.db.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author kutay.akyol
 */

public class User implements Serializable {
    @Id
    private BigDecimal id;
     private String kullaniciadi;
     private BigDecimal kisiid;
     private String sifre;
     private String kaydiyapankullanici;
     private String kaydiguncelleyenkullanici;
     private Date kayiteklemetarihi;
     private Date kayitguncellemetarihi;

    public BigDecimal getKisiid() {
        return kisiid;
    }

    public void setKisiid(BigDecimal kisiid) {
        this.kisiid = kisiid;
    }


    public String getKaydiyapankullanici() {
        return kaydiyapankullanici;
    }

    public void setKaydiyapankullanici(String kaydiyapankullanici) {
        this.kaydiyapankullanici = kaydiyapankullanici;
    }

    public String getKaydiguncelleyenkullanici() {
        return kaydiguncelleyenkullanici;
    }

    public void setKaydiguncelleyenkullanici(String kaydiguncelleyenkullanici) {
        this.kaydiguncelleyenkullanici = kaydiguncelleyenkullanici;
    }

    public Date getKayiteklemetarihi() {
        return kayiteklemetarihi;
    }

    public void setKayiteklemetarihi(Date kayiteklemetarihi) {
        this.kayiteklemetarihi = kayiteklemetarihi;
    }

    public Date getKayitguncellemetarihi() {
        return kayitguncellemetarihi;
    }

    public void setKayitguncellemetarihi(Date kayitguncellemetarihi) {
        this.kayitguncellemetarihi = kayitguncellemetarihi;
    }
     

     public User() {
    }

    public User(BigDecimal id) {
        this.id = id;
    }
    
    public User(BigDecimal id, String kullaniciadi, String sifre)
    {
       this.id = id;
       this.kullaniciadi = kullaniciadi;
       this.sifre = sifre;
    }
     
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getKullaniciadi() {
        return kullaniciadi;
    }

    public void setKullaniciadi(String kullaniciadi) {
        this.kullaniciadi = kullaniciadi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }
     
    
}
