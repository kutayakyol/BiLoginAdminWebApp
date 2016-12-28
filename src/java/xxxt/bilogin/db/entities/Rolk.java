package xxxt.bilogin.db.entities;
// Generated Jun 24, 2016 10:03:53 AM by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Rol generated by hbm2java
 */
@Entity
@Table(name="ROL"
)
public class Rolk  implements java.io.Serializable {

    
     private BigDecimal id;
     private String roladi;
     private String aciklama;
     private String ekleyenkisi;
     private String guncelleyenkisi;
     private BigDecimal aktifmi;
     private Date eklemetarihi;
     private Date guncellemetarihi;

    public Rolk() {
    }

	
    public Rolk(BigDecimal id) {
        this.id = id;
    }
    public Rolk(BigDecimal id, String roladi, String aciklama, String ekleyenkisi, String guncelleyenkisi, BigDecimal aktifmi, Date eklemetarihi, Date guncellemetarihi) {
       this.id = id;
       this.roladi = roladi;
       this.aciklama = aciklama;
       this.ekleyenkisi = ekleyenkisi;
       this.guncelleyenkisi = guncelleyenkisi;
       this.aktifmi = aktifmi;
       this.eklemetarihi = eklemetarihi;
       this.guncellemetarihi = guncellemetarihi;
    }
   
    @Id
    public BigDecimal getId() {
        return this.id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }
    public String getRoladi() {
        return this.roladi;
    }
    
    public void setRoladi(String roladi) {
        this.roladi = roladi;
    }
    public String getAciklama() {
        return this.aciklama;
    }
    
    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }
    public String getEkleyenkisi() {
        return this.ekleyenkisi;
    }
    
    public void setEkleyenkisi(String ekleyenkisi) {
        this.ekleyenkisi = ekleyenkisi;
    }
    public String getGuncelleyenkisi() {
        return this.guncelleyenkisi;
    }
    
    public void setGuncelleyenkisi(String guncelleyenkisi) {
        this.guncelleyenkisi = guncelleyenkisi;
    }
    public BigDecimal getAktifmi() {
        return this.aktifmi;
    }
    
    public void setAktifmi(BigDecimal aktifmi) {
        this.aktifmi = aktifmi;
    }
     @Temporal(TemporalType.DATE)
    public Date getEklemetarihi() {
        return this.eklemetarihi;
    }
    
    public void setEklemetarihi(Date eklemetarihi) {
        this.eklemetarihi = eklemetarihi;
    }
    @Temporal(TemporalType.DATE)
    public Date getGuncellemetarihi() {
        return this.guncellemetarihi;
    }
    
    public void setGuncellemetarihi(Date guncellemetarihi) {
        this.guncellemetarihi = guncellemetarihi;
    }




}

