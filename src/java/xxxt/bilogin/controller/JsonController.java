/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xxxt.bilogin.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import xxxt.bilogin.db.entities.IsletmeDetay;
import xxxt.bilogin.db.entities.Kullanici;
import xxxt.bilogin.db.entities.RolDuyuru;
import xxxt.bilogin.db.entities.Rolk;
import xxxt.bilogin.db.entities.UserLog;
import xxxt.bilogin.util.KullaniciModel;
import xxxt.login.service.KullaniciService;

/**
 *
 * @author kutay.akyol
 */
@Controller
@EnableWebMvc
@RequestMapping(value = "/json")
public class JsonController {

    @Autowired
    private KullaniciService kullaniciService;

    /**
     *
     * @return
     */
    @RequestMapping(value = "/getOrgCode", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE,headers="Accept=*/*")
    @ResponseBody
    public IsletmeDetay getOrgCode(@RequestParam("orgName") String orgName) {
        String orgCode = "";
        //String orgName ="AFŞİN-ELBİSTAN LİNYİTLERİ İŞLETME MÜDÜRLÜĞÜ";

        System.out.println("AjaxTest....getJson....orgName: " + orgName.toString());

        try {
            orgCode = kullaniciService.getOrgCodeWithOrgName(orgName);
        } catch (Exception e) {
            orgCode = "";
        }
        
        System.out.println("AjaxTest....getJson....orgCode: " + orgCode.toString());

        return new IsletmeDetay(orgCode);
    }
    
    @RequestMapping(value = "/getPassHashVal", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE,headers="Accept=*/*")
    @ResponseBody
    public Kullanici getPassHashVal(@RequestParam("passVal") String passVal) {
        Kullanici k = new Kullanici();
        //String orgName ="AFŞİN-ELBİSTAN LİNYİTLERİ İŞLETME MÜDÜRLÜĞÜ";

        System.out.println("AjaxTest....getJson....pass: " + passVal.toString());

        try {
            KullaniciModel km =new KullaniciModel();
            k.setSifre(km.getHashPassword(passVal));
            System.out.println("AjaxTest....getJson....passHash: " + k.getSifre().toString());
        } catch (Exception e) {
            System.out.println("AjaxTest....getJson....passHash....HATA!");
            k=null;
        }
        
        return k;
    }
    /**
     *
     * @return
     */
    @RequestMapping(value = "/getCheckPassword", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE,headers="Accept=*/*")
    @ResponseBody
    public List<UserLog> getCheckPassword(@RequestParam("userId") String userId) {
        List<UserLog> userLogList = new ArrayList<UserLog>();
        //String orgName ="AFŞİN-ELBİSTAN LİNYİTLERİ İŞLETME MÜDÜRLÜĞÜ";
        System.out.println("AjaxTest....getJson....userId: " + userId.toString());

        try {
            userLogList = kullaniciService.getPasswordLogWithId(userId);
        } catch (Exception e) {
            userLogList = null;
        }
        
        try{
        System.out.println("AjaxTest....getJson....userLogList.get(0).getKullaniciid(): " + userLogList.get(0).getKullaniciid().toString());
        System.out.println("AjaxTest....getJson....userLogList.get(0).getKullaniciadi(): " + userLogList.get(0).getKullaniciadi().toString());
        
        System.out.println("AjaxTest....getJson....userLogList.get(0).getSifre: " + userLogList.get(0).getSifre().toString());
        System.out.println("AjaxTest....getJson....userLogList.get(1).getSifre: " + userLogList.get(1).getSifre().toString());
        System.out.println("AjaxTest....getJson....userLogList.get(2).getSifre: " + userLogList.get(2).getSifre().toString());
        }
        catch(Exception e){
            
        }

        return userLogList;
    }
    
    @RequestMapping(value = "/getDuyuruList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE,headers="Accept=*/*")
    @ResponseBody
    public List<RolDuyuru> getDuyuruList(@RequestParam("userId") String userId,@Context HttpServletResponse response) {
        List<RolDuyuru> duyuruList =new ArrayList<RolDuyuru>();
        
        System.out.println("AjaxTest....getJson....userId: " + userId.toString());
        
        
        String idStr = userId;
        int id;
            if (idStr.contains(".")) {
                String[] s = null;
                System.out.print("11...........kisiresimservlet............idstr: "+idStr);
                s = idStr.split("\\.");
                System.out.print("12...........kisiresimservlet............s[0] : "+s[0]+",  s[1]: "+s[1]);
                id = Integer.parseInt(s[0]);
            } else {
                System.out.println("21.........kisiresimservlet......idstr: "+idStr);
                id = Integer.parseInt(idStr);
            }
        
        java.math.BigDecimal idBig = new java.math.BigDecimal(String.valueOf(id));
        
        try {
            duyuruList = kullaniciService.getDuyuruList(idBig);
        } catch (Exception e) {
            duyuruList = null;
        }
        
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization, X-Auth-Token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", "*");

        return duyuruList;
    }
    
}
