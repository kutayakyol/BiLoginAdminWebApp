/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xxxt.bilogin.objects;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author admin
 */
public class RolObj {
    private Integer id;
     private String roladi;
     private String aciklama;
     private String ekleyenkisi;
     private String guncelleyenkisi;
     private Boolean aktifmi;
    
     private Date eklemetarihi;
     private Date guncellemetarihi;

    
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
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
    public Boolean getAktifmi() {
        return this.aktifmi;
    }
    
    public void setAktifmi(Boolean aktifmi) {
        this.aktifmi = aktifmi;
    }
    
    public Date getEklemetarihi() {
        return this.eklemetarihi;
    }
    
    public void setEklemetarihi(Date eklemetarihi) {
        this.eklemetarihi = eklemetarihi;
    }
    public Date getGuncellemetarihi() {
        return this.guncellemetarihi;
    }
    
    public void setGuncellemetarihi(Date guncellemetarihi) {
        this.guncellemetarihi = guncellemetarihi;
    }


      private List<String> roles;

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    
       private List<String> erpResponsibilities;

    public List<String> getErpResponsibilities() {
        return erpResponsibilities;
    }

    public void setErpResponsibilities(List<String> erpResponsibilities) {
        this.erpResponsibilities = erpResponsibilities;
    }

  
   
    
}
