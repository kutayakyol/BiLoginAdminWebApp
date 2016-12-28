/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xxxt.bilogin.objects;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author admin
 */
public class PersonObj implements Serializable{
    private BigDecimal id;
     private String kullaniciadi;
     private String ad;
     private String soyad;
     private String adsoyad;
     private String sifre;
     private String sicilno;
     private String tcno;
     private String organizasyonId;
     private BigDecimal kisiid;
     private String eposta;
     private Boolean sifredegistir;
     private String isletmetipi;
     private String kaydiyapankullanici;
     private String kaydiguncelleyenkullanici;
     private BigDecimal isletmetipiId;
     private String isletmeadi;
     private String calistigibirim;

     private Boolean aktif;
     private String yerlesimkod;
     private String orgKod;
     private String name;
    
     private Date kayiteklemetarihi;
     private Date kayitguncellemetarihi;
     private BigDecimal personelTip;
     private Boolean isyazilim;
     private String unvan;

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

    public String getAdsoyad() {
        return adsoyad;
    }

    public void setAdsoyad(String adsoyad) {
        this.adsoyad = adsoyad;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getSicilno() {
        return sicilno;
    }

    public void setSicilno(String sicilno) {
        this.sicilno = sicilno;
    }

    public String getTcno() {
        return tcno;
    }

    public void setTcno(String tcno) {
        this.tcno = tcno;
    }

    public String getOrganizasyonId() {
        return organizasyonId;
    }

    public void setOrganizasyonId(String organizasyonId) {
        this.organizasyonId = organizasyonId;
    }

    public BigDecimal getKisiid() {
        return kisiid;
    }

    public void setKisiid(BigDecimal kisiid) {
        this.kisiid = kisiid;
    }

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }

    public Boolean getSifredegistir() {
        return sifredegistir;
    }

    public void setSifredegistir(Boolean sifredegistir) {
        this.sifredegistir = sifredegistir;
    }

    public String getIsletmetipi() {
        return isletmetipi;
    }

    public void setIsletmetipi(String isletmetipi) {
        this.isletmetipi = isletmetipi;
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

    public BigDecimal getIsletmetipiId() {
        return isletmetipiId;
    }

    public void setIsletmetipiId(BigDecimal isletmetipiId) {
        this.isletmetipiId = isletmetipiId;
    }

    public String getIsletmeadi() {
        return isletmeadi;
    }

    public void setIsletmeadi(String isletmeadi) {
        this.isletmeadi = isletmeadi;
    }

    public String getCalistigibirim() {
        return calistigibirim;
    }

    public void setCalistigibirim(String calistigibirim) {
        this.calistigibirim = calistigibirim;
    }

   

    public Boolean getAktif() {
        return aktif;
    }

    public void setAktif(Boolean aktif) {
        this.aktif = aktif;
    }

    public String getYerlesimkod() {
        return yerlesimkod;
    }

    public void setYerlesimkod(String yerlesimkod) {
        this.yerlesimkod = yerlesimkod;
    }

    public String getOrgKod() {
        return orgKod;
    }

    public void setOrgKod(String orgKod) {
        this.orgKod = orgKod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public BigDecimal getPersonelTip() {
        return personelTip;
    }

    public void setPersonelTip(BigDecimal personelTip) {
        this.personelTip = personelTip;
    }

    public Boolean getIsyazilim() {
        return isyazilim;
    }

    public void setIsyazilim(Boolean isyazilim) {
        this.isyazilim = isyazilim;
    }

    public String getUnvan() {
        return unvan;
    }

    public void setUnvan(String unvan) {
        this.unvan = unvan;
    }
     
    
}
