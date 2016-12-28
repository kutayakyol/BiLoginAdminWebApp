/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xxxt.bilogin.dao;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import xxxt.bilogin.db.SessionFactoryUtil;
import xxxt.bilogin.db.entities.ErpOrganizasyon;
import xxxt.bilogin.db.entities.Isletme;
import xxxt.bilogin.db.entities.IsletmeAdmin;
import xxxt.bilogin.db.entities.IsletmeDetay;
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
public interface KullaniciRepository {
    
    List<Kullanici> getAllwithParam(PersonObj person,String orgId);
    List<Kullanici> getAll(String orgId);
    List<String> getAllIsletmeTipi();
    List<IsletmeTipi> getIsletmeTipi(String isletmetipi);
    List<ErpOrganizasyon> getAllorg(String isletmeadi);
    String getpassword();
    List<UserLog> getPasswordLogWithId(String id);
    ErpOrganizasyon getorganizasyon(Long id);
    List<KullaniciDetay> getKullaniciDetay(String tckimlikno);
    List<Rolk> getAllrol(String loginuser, boolean pIsletmeAdmin);
    void create(Kullanici k);
    void remove(Kullanici k);
    void edit(Kullanici k);
    void edit_notcommit(Kullanici k);
    Kullanici getKullanici(int id);
    PersonelTip getPersonelTipTanim(BigDecimal id);
    void saveImage(KisiResim kr);
    byte[] getImage(BigDecimal kisiid,String p);
    byte[] getImagePrev(BigDecimal kisiid);
    public List<Kullanici> getPersonWithPersonId(BigDecimal kisiid);
    String getUrl(String url);
    List<Isletme> getAllIsletme();
    List<Isletme> getOrgIdWithOrgName(String orgName);
    List<OrgCode> getOrgCodeList();
    String getOrgCodeWithOrgName(String orgName);
    List<RolDuyuru> getDuyuruList(BigDecimal userId);
    String getOrgid(String kisiid);
    IsletmeAdmin getIsletmeAdmin(BigDecimal kisiid);
    List<KullaniciDetay> getKullaniciDetayWithOrgId(String tckimlikno, String orgid);
}
