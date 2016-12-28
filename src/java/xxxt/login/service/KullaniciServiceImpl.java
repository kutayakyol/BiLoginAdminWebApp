/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xxxt.login.service;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xxxt.bilogin.dao.KullaniciRepository;
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
@Service("KullaniciService")
public class KullaniciServiceImpl implements KullaniciService {

    // @Autowired
    private KullaniciRepository kullaniciRepository;

    public KullaniciServiceImpl() {
    }

    /// Constructor injection 
    public KullaniciServiceImpl(KullaniciRepository kullaniciRepository) {
        this.kullaniciRepository = kullaniciRepository;
    }

    /// Setter injection 
    @Autowired
    public void setKullaniciRepository(KullaniciRepository kullaniciRepository) {
        this.kullaniciRepository = kullaniciRepository;
    }

    @Override
    public List<Kullanici> getAllwithParam(PersonObj person,String orgId) {
        return kullaniciRepository.getAllwithParam(person,orgId);
    }

    @Override
    public List<Kullanici> getAll(String orgId) {
        return kullaniciRepository.getAll(orgId);
    }

    @Override
    public List<String> getAllIsletmeTipi() {
        return kullaniciRepository.getAllIsletmeTipi();
    }

    ;
     
     @Override
    public List<IsletmeTipi> getIsletmeTipi(String isletmetipi) {
        return kullaniciRepository.getIsletmeTipi(isletmetipi);
    }

    ;
     
    @Override
    public List<ErpOrganizasyon> getAllorg(String isletmeadi) {
        return kullaniciRepository.getAllorg(isletmeadi);
    }

    @Override
    public String getpassword() {
        return kullaniciRepository.getpassword();
    }

    @Override
    public List<UserLog> getPasswordLogWithId(String id){
        return kullaniciRepository.getPasswordLogWithId(id);
    }
    
    @Override
    public ErpOrganizasyon getorganizasyon(Long id) {
        return kullaniciRepository.getorganizasyon(id);

    }

    @Override
    public List<KullaniciDetay> getKullaniciDetay(String tckimlikno) {
        return kullaniciRepository.getKullaniciDetay(tckimlikno);
    }
    
    @Override
    public List<KullaniciDetay> getKullaniciDetayWithOrgId(String tckimlikno, String orgName) {
        return kullaniciRepository.getKullaniciDetayWithOrgId(tckimlikno,orgName);
    }

    @Override
    public List<Rolk> getAllrol(String loginuser, boolean pIsletmeAdmin) {
        return kullaniciRepository.getAllrol(loginuser,pIsletmeAdmin);
    }

    @Override
    public void create(Kullanici k) {
        kullaniciRepository.create(k);
    }

    @Override
    public void remove(Kullanici k) {
        kullaniciRepository.remove(k);
    }

    @Override
    public void edit(Kullanici k) {
        kullaniciRepository.edit(k);
    }

    @Override
    public void edit_notcommit(Kullanici k) {
        kullaniciRepository.edit_notcommit(k);
    }

    @Override
    public Kullanici getKullanici(int id) {
        return kullaniciRepository.getKullanici(id);
    }

    @Override
    public PersonelTip getPersonelTipTanim(BigDecimal id) {
        return kullaniciRepository.getPersonelTipTanim(id);
    }
    
    @Override
    public void saveImage(KisiResim kr) {
        kullaniciRepository.saveImage(kr);
    }
    
    @Override
    public byte[] getImage(BigDecimal kisiid,String p) {
        return kullaniciRepository.getImage(kisiid,p);
    }
    
    @Override
    public byte[] getImagePrev(BigDecimal kisiid) {
        return kullaniciRepository.getImagePrev(kisiid);
    }
    
    @Override
    public List<Kullanici> getPersonWithPersonId(BigDecimal kisiid){
         return kullaniciRepository.getPersonWithPersonId(kisiid);
    }
    
    @Override
    public String getUrl(String url) {
        return kullaniciRepository.getUrl(url);
    }
    
    @Override
    public List<Isletme> getAllIsletme(){
         return kullaniciRepository.getAllIsletme();
    }
    
    @Override
    public List<OrgCode> getOrgCodeList(){
        return kullaniciRepository.getOrgCodeList();
    }
    
    @Override
    public List<Isletme> getOrgIdWithOrgName(String orgName){
        return kullaniciRepository.getOrgIdWithOrgName(orgName);
    }
    
    @Override
    public String getOrgCodeWithOrgName(String orgName){
        return kullaniciRepository.getOrgCodeWithOrgName(orgName);
    }
    
    @Override
    public List<RolDuyuru> getDuyuruList(BigDecimal userId){
        return kullaniciRepository.getDuyuruList(userId);
    }

    @Override
    public String getOrgid(String kisiid) {
        return kullaniciRepository.getOrgid(kisiid);
    }
    
    @Override
     public IsletmeAdmin getIsletmeAdmin(BigDecimal kisiid) {
        return kullaniciRepository.getIsletmeAdmin(kisiid);
    }
}
