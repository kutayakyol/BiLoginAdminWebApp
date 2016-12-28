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
public class RolKullanici {

    private int id;
    private BigDecimal rol;
    private BigDecimal kullanici;
    private String ekleyenkisi;
    private Date eklemetarihi;
    private BigDecimal aktifmi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getRol() {
        return rol;
    }

    public void setRol(BigDecimal rol) {
        this.rol = rol;
    }

    public BigDecimal getKullanici() {
        return kullanici;
    }

    public void setKullanici(BigDecimal kullanici) {
        this.kullanici = kullanici;
    }

    public String getEkleyenkisi() {
        return ekleyenkisi;
    }

    public void setEkleyenkisi(String ekleyenkisi) {
        this.ekleyenkisi = ekleyenkisi;
    }

    public Date getEklemetarihi() {
        return eklemetarihi;
    }

    public void setEklemetarihi(Date eklemetarihi) {
        this.eklemetarihi = eklemetarihi;
    }

    public BigDecimal getAktifmi() {
        return aktifmi;
    }

    public void setAktifmi(BigDecimal aktifmi) {
        this.aktifmi = aktifmi;
    }
}
