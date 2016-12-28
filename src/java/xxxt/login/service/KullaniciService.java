/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xxxt.login.service;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.List;
import xxxt.bilogin.db.entities.ErpOrganizasyon;
import xxxt.bilogin.db.entities.Isletme;
import xxxt.bilogin.db.entities.IsletmeAdmin;
import xxxt.bilogin.db.entities.IsletmeTipi;
import xxxt.bilogin.db.entities.KisiResim;
import xxxt.bilogin.db.entities.Kullanici;
import xxxt.bilogin.db.entities.KullaniciDetay;
import xxxt.bilogin.db.entities.OrgCode;
import xxxt.bilogin.db.entities.PersonelTip;
import xxxt.bilogin.db.entities.Rol;
import xxxt.bilogin.db.entities.RolDuyuru;
import xxxt.bilogin.db.entities.Rolk;
import xxxt.bilogin.db.entities.UserLog;
import xxxt.bilogin.objects.PersonObj;

/**
 *
 * @author admin
 */
public interface KullaniciService {
    
    public List<Kullanici> getAllwithParam(PersonObj person,String orgId);
    public List<Kullanici> getAll(String orgId) ;
    public List<String> getAllIsletmeTipi();
    public List<IsletmeTipi> getIsletmeTipi(String isletmetipi);
    public List<ErpOrganizasyon> getAllorg(String isletmeadi);
    public String getpassword();
    public List<UserLog> getPasswordLogWithId(String id);
    public ErpOrganizasyon getorganizasyon(Long id);
    public List<KullaniciDetay> getKullaniciDetay(String tckimlikno);
    public List<KullaniciDetay> getKullaniciDetayWithOrgId(String tckimlikno, String orgName);
    public List<Rolk> getAllrol(String loginuser, boolean pIsletmeAdmin);
    public void create(Kullanici k);
    public void remove(Kullanici k);
    public void edit(Kullanici k);
    public void edit_notcommit(Kullanici k);
    public Kullanici getKullanici(int id);
    public PersonelTip getPersonelTipTanim(BigDecimal id);
    void saveImage(KisiResim kr);
    byte[] getImage(BigDecimal kisiid,String p);
    byte[] getImagePrev(BigDecimal kisiid);
    List<Kullanici> getPersonWithPersonId(BigDecimal kisiid);
    String getUrl(String url);
    List<Isletme> getAllIsletme();
    List<OrgCode> getOrgCodeList();
    List<Isletme> getOrgIdWithOrgName(String orgName);
    String getOrgCodeWithOrgName(String orgName);
    List<RolDuyuru> getDuyuruList(BigDecimal userId);
    String getOrgid(String kisiid);
    IsletmeAdmin getIsletmeAdmin(BigDecimal kisiid);
}
