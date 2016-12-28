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
public class UserLog {

    private BigDecimal id;
    private String kullaniciid;
    private String kullaniciadi;
    private String sifre;
    private String statu;
    private String kaydiyapankullanici;
    private String kaydiguncelleyenkullanici;
    private Date kayiteklemetarihi;
    private Date kayitguncellemetarihi;
    private Date kayitsilinmetarihi;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getKullaniciid() {
        return kullaniciid;
    }

    public void setKullaniciid(String kullaniciid) {
        this.kullaniciid = kullaniciid;
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

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
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

    public Date getKayitsilinmetarihi() {
        return kayitsilinmetarihi;
    }

    public void setKayitsilinmetarihi(Date kayitsilinmetarihi) {
        this.kayitsilinmetarihi = kayitsilinmetarihi;
    }
}
