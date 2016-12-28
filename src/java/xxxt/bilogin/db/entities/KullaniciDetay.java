/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xxxt.bilogin.db.entities;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author admin
 */
@Entity
@Table(name="KULLANICI_DETAY_MV"
)
public class KullaniciDetay implements java.io.Serializable{
    
    private BigDecimal kisiId;
    private String tckimlikno;
    private String ad;
    private String soyad;
    private String yerlesimkod;
    private String organizationId;
    private String name;
    private String birim;
    private String orgkod;
    private Long tasramerkezId;
    private String tasramerkez;
    private String unvani;   
    private BigDecimal personeltipId;
    private String sicilno;

    @Id
    public BigDecimal getKisiId() {
        return kisiId;
    }

    public void setKisiId(BigDecimal kisiId) {
        this.kisiId = kisiId;
    }

    public String getTckimlikno() {
        return tckimlikno;
    }

    public void setTckimlikno(String tckimlikno) {
        this.tckimlikno = tckimlikno;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getYerlesimkod() {
        return yerlesimkod;
    }

    public void setYerlesimkod(String yerlesimkod) {
        this.yerlesimkod = yerlesimkod;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirim() {
        return birim;
    }

    public void setBirim(String birim) {
        this.birim = birim;
    }

    public String getOrgkod() {
        return orgkod;
    }

    public void setOrgkod(String orgkod) {
        this.orgkod = orgkod;
    }

    public Long getTasramerkezId() {
        return tasramerkezId;
    }

    public void setTasramerkezId(Long tasramerkezId) {
        this.tasramerkezId = tasramerkezId;
    }

    public String getTasramerkez() {
        return tasramerkez;
    }

    public void setTasramerkez(String tasramerkez) {
        this.tasramerkez = tasramerkez;
    }

    public String getUnvani() {
        return unvani;
    }

    public void setUnvani(String unvani) {
        this.unvani = unvani;
    }

    public BigDecimal getPersoneltipId() {
        return personeltipId;
    }

    public void setPersoneltipId(BigDecimal personeltipId) {
        this.personeltipId = personeltipId;
    }
    
    public String getSicilno() {
        return sicilno;
    }

    public void setSicilno(String sicilno) {
        this.sicilno = sicilno;
    }

}
