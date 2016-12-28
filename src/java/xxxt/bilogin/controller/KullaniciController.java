/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xxxt.bilogin.controller;

import java.math.BigDecimal;
import java.sql.Blob;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import xxxt.bilogin.db.entities.ErpOrganizasyon;
import xxxt.bilogin.db.entities.IsletmeAdmin;
import xxxt.bilogin.db.entities.KisiResim;
import xxxt.bilogin.db.entities.Kullanici;
import xxxt.bilogin.db.entities.KullaniciDetay;
import xxxt.bilogin.db.entities.KullaniciOrganizasyon;
import xxxt.bilogin.db.entities.PersonelTip;
import xxxt.bilogin.db.entities.RolKullanici;
import xxxt.bilogin.db.entities.User;

import xxxt.bilogin.objects.KullaniciObj;
import xxxt.bilogin.objects.PersonObj;
import xxxt.bilogin.util.KullaniciModel;
import xxxt.login.service.KullaniciService;
import xxxt.login.service.LoginService;

/**
 *
 * @author admin
 */
@Controller
public class KullaniciController {

    @Autowired
    private KullaniciService kullaniciService;
    @Autowired
    private LoginService loginService;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "personadd";
    }

    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public String getAll(Model m, ModelMap modelMap, HttpSession session) {
        IsletmeAdmin isletmeAdmin = new IsletmeAdmin();
        String OrgName = null;
        try {
            java.math.BigDecimal kisiIdBig = new java.math.BigDecimal(session.getAttribute("loginkisiid").toString());
            isletmeAdmin = kullaniciService.getIsletmeAdmin(kisiIdBig);
            System.out.println("orgId personadd=" + isletmeAdmin.getOrgName());
            OrgName = isletmeAdmin.getOrgName();
        } catch (Exception e11) {
            OrgName = null;

        }

        modelMap.put("acm", new Kullanici());


        m.addAttribute("lst", kullaniciService.getAll(OrgName));

        System.out.println("sessionuser" + session.getAttribute("loginuser"));



        return "data";
    }

    @RequestMapping(value = "data.html", method = RequestMethod.POST)
    public String getAllParam(@ModelAttribute(value = "acm") Kullanici acm, ModelMap modelMap, HttpSession session, Model m) {
        IsletmeAdmin isletmeAdmin = new IsletmeAdmin();
        String OrgName = null;


        PersonObj person = new PersonObj();
        person.setKullaniciadi(acm.getKullaniciadi().toString());
        person.setAd(acm.getAd().toString());
        person.setSoyad(acm.getSoyad().toString());
        person.setTcno(acm.getTcno().toString());
        person.setIsletmeadi(acm.getIsletmeadi().toString());
        person.setOrgKod(acm.getOrgKod().toString());
        person.setSicilno(acm.getSicilno().toString());
        person.setKisiid(acm.getKisiid());

        try {
            java.math.BigDecimal kisiIdBig = new java.math.BigDecimal(session.getAttribute("loginkisiid").toString());
            isletmeAdmin = kullaniciService.getIsletmeAdmin(kisiIdBig);
            System.out.println("orgId personadd=" + isletmeAdmin.getOrgName());
            OrgName = isletmeAdmin.getOrgName();
        } catch (Exception e11) {
            OrgName = null;

        }

        m.addAttribute("lst", kullaniciService.getAllwithParam(person, OrgName));
        return "data";



    }

    @RequestMapping(value = "personadd", method = RequestMethod.GET)
    public String personadd(@RequestParam(value = "tcno") String tcno, Model m, ModelMap modelMap, HttpSession session) {



        String sentence = "Define, Measure, Analyze, Design and Verify";

        String replaced = sentence.replace("and", "");

        System.out.println("replacedhali>" + replaced);

        Kullanici k = new Kullanici();
        PersonelTip pt = new PersonelTip();
        List<KullaniciDetay> kd = new ArrayList<KullaniciDetay>();
        IsletmeAdmin isletmeAdmin = new IsletmeAdmin();
        String OrgName = null;
        boolean checkIsletmeAdmin = false;


        try {
            java.math.BigDecimal kisiIdBig = new java.math.BigDecimal(session.getAttribute("loginkisiid").toString());
            isletmeAdmin = kullaniciService.getIsletmeAdmin(kisiIdBig);
            System.out.println("orgId personadd=" + isletmeAdmin.getOrgId());
            OrgName = isletmeAdmin.getOrgName();
            checkIsletmeAdmin = true;

        } catch (Exception e11) {
            OrgName = null;
            checkIsletmeAdmin = false;

        }

        if (!tcno.equals("null")) {
            try {

                if (OrgName == null) {
                    kd = kullaniciService.getKullaniciDetay(tcno);
                    System.out.println("kisiidInt ORG ID null:" + OrgName);

                } else {
                    //kd = kullaniciService.getKullaniciDetay(tcno);
                    //int kisiidInt = Integer.parseInt(session.getAttribute("loginkisiid").toString());
                    java.math.BigDecimal kisiId = new java.math.BigDecimal(String.valueOf(session.getAttribute("loginkisiid").toString()));

                    System.out.println("kisiId: " + kisiId);
                    System.out.println("CheckAdmin :" + OrgName);
                    kd = kullaniciService.getKullaniciDetayWithOrgId(tcno, OrgName);


                    /*
                     if (kullaniciService.checkAdmin(kisiId)) {
                     kd = kullaniciService.getKullaniciDetayWithOrgId(tcno, orgId);
                     }
                     */
                }

                k.setTcno(tcno);

                k.setAd(kd.get(0).getAd());

                k.setSoyad(kd.get(0).getSoyad());

                k.setAdsoyad(kd.get(0).getAd() + " " + kd.get(0).getSoyad());

                k.setKullaniciadi(kd.get(0).getAd() + "." + kd.get(0).getSoyad());

                k.setSicilno(kd.get(0).getSicilno());

                k.setOrgKod(kd.get(0).getOrgkod());

                k.setOrganizasyonId(kd.get(0).getOrganizationId());

                k.setYerlesimkod(kd.get(0).getYerlesimkod());

                k.setIsletmetipiId(kullaniciService.getIsletmeTipi(kd.get(0).getTasramerkez()).get(0).getId());

                System.out.println("person add isletme tipi id " + kullaniciService.getIsletmeTipi(kd.get(0).getTasramerkez()).get(0).getId());

                k.setIsletmeadi(kd.get(0).getName());

                k.setKisiid(kd.get(0).getKisiId());

                k.setPersonelTip(kd.get(0).getPersoneltipId());

                k.setCalistigibirim(kd.get(0).getBirim());

                k.setIsletmetipi(kd.get(0).getTasramerkez());

                k.setUnvan(kd.get(0).getUnvani());

                k.setEposta(normalize(kd.get(0).getAd().toString()) + "." + normalize(kd.get(0).getSoyad().toString()) + "@euas.gov.tr");

//                try {
//                    String imagebase64=new String(Base64.encode(kullaniciService.getImage(kd.get(0).getKisiId())));
//                    m.addAttribute("kisiResim", imagebase64);
//                } catch (Exception e) {
//                }


                //k.setIsyazilim(Boolean.FALSE);
            } catch (Exception e2) {

                modelMap.addAttribute("messageedit", "Veri tabaninda veriler bulunamadi");
            }


        }


        if (session.getAttribute("statumessage") != null) {
            if (session.getAttribute("statumessage").equals("S")) {

                modelMap.addAttribute("messageedit", "Basari ile Kaydoldu");
                session.setAttribute("statumessage", null);
            } else if (session.getAttribute("statumessage").equals("AddImg")) {
                modelMap.addAttribute("messageedit", "Kişi Resmi Yüklendi...");
                session.setAttribute("statumessage", null);
            } else if (session.getAttribute("statumessage").equals("KulVar")) {
                modelMap.addAttribute("messageedit", "Bu kullanici adi sistemde mevcuttur!");
                session.setAttribute("statumessage", null);
            } else if (session.getAttribute("statumessage").equals("TcNoVar")) {
                modelMap.addAttribute("messageedit", "Bu TcNolu kişi sistemde mevcuttur!");
                session.setAttribute("statumessage", null);
            } else {
                modelMap.addAttribute("messageedit", session.getAttribute("statumessage").toString());
                session.setAttribute("statumessage", null);
            }
        }

        System.out.println("kullaniciController.....k.getPersonelTip()..." + k.getPersonelTip());
        try {
            if (k.getPersonelTip() != null) {
                pt = kullaniciService.getPersonelTipTanim(k.getPersonelTip());
            }
        } catch (Exception e) {
        }

        //System.out.println("222222......personadd..... personeltiptanim :"+pt.getTanim().toString());

        m.addAttribute("k", k);
        m.addAttribute("pt", pt);

        m.addAttribute("isletmetip", kullaniciService.getAllIsletmeTipi());

        modelMap.put("Kullanici", k);

        m.addAttribute("leftList", kullaniciService.getAllrol(session.getAttribute("loginkisiid").toString(), checkIsletmeAdmin));

        m.addAttribute("orgNameList", kullaniciService.getAllIsletme());

        m.addAttribute("orgCodeList", kullaniciService.getOrgCodeList());



        return "personadd";
    }

    @RequestMapping(value = "add", params = "kaydet", method = RequestMethod.POST)
    public String add(@ModelAttribute(value = "Kullanici") KullaniciObj ku, ModelMap modelMap, Model m, HttpSession session, BindingResult result) {



        Kullanici kc = new Kullanici();

        KullaniciModel km = new KullaniciModel();

        // kc = kullaniciService.getKullanici(ku.getId());




        boolean kullaniciExists = km.checkKullanici(ku.getKullaniciadi());
        if (!kullaniciExists) {

            if (ku.getIsyazilim() == null) {
                boolean tcnoExists = km.checktcno(ku.getTcno());

                if (!tcnoExists) {
                    try {
                        kc.setKullaniciadi(ku.getKullaniciadi());
                        kc.setAd(ku.getAd());

                        kc.setSoyad(ku.getSoyad());

                        kc.setAdsoyad(ku.getAd() + " " + ku.getSoyad());

                        kc.setSicilno(ku.getSicilno());

                        kc.setTcno(ku.getTcno());

                        kc.setIsletmeadi(ku.getIsletmeadi());

                        kc.setIsletmetipiId(kullaniciService.getIsletmeTipi(ku.getIsletmeadi()).get(0).getId());



                        kc.setAktif(Boolean.valueOf(true));



                        //System.out.println("isletmead>"+ku.getIsletmeadi().toString());

                        kc.setUnvan(ku.getUnvan());

                        kc.setPersonelTip(ku.getPersonelTip());

                        kc.setOrgKod(ku.getOrgKod());

                        kc.setCalistigibirim(ku.getCalistigibirim());

                        kc.setYerlesimkod(ku.getYerlesimkod());
                        kc.setIsyazilim(ku.getIsyazilim());

                        kc.setSifre(kullaniciService.getpassword());

                        kc.setKisiid(ku.getKisiid());

                        //kc.setOrganizasyonId(ku.getOrganizasyonId());

                        kc.setOrganizasyonId(kullaniciService.getOrgIdWithOrgName(ku.getIsletmeadi()).get(0).getIsletmeId().toString());


                        try {
                            kc.setEposta(ku.getEposta());

                            kc.setIsletmetipi(ku.getIsletmetipi());
                        } catch (Exception e) {
                            String durum = "alan kapali";
                        }


                        kc.setKaydiguncelleyenkullanici(session.getAttribute("loginuser").toString());

                        kc.setKayitguncellemetarihi(new Date());

                        kc.setKaydiyapankullanici(session.getAttribute("loginuser").toString());

                        kc.setKayiteklemetarihi(new Date());



                        kullaniciService.create(kc);
                        session.setAttribute("statumessage", "S");
                    } catch (Exception e) {
                        session.setAttribute("statumessage", e.getMessage().toString());
                    }


                    RolKullanici rolkul = new RolKullanici();

                    java.math.BigDecimal kullaniciid = new java.math.BigDecimal(String.valueOf(kc.getId()));

                    //rolkul.setKullanici(ku.getId());

                    rolkul.setKullanici(kullaniciid);



                    km.removerolkullanici(rolkul);


                    java.math.BigDecimal aktif = new java.math.BigDecimal(String.valueOf(1));
                    if (ku.getRoles() != null) {
                        for (int i = 0; i < ku.getRoles().size(); i++) {
                            java.math.BigDecimal rol = new java.math.BigDecimal(ku.getRoles().get(i).toString());
                            rolkul.setKullanici(kullaniciid);
                            rolkul.setEklemetarihi(new Date());
                            rolkul.setEkleyenkisi(session.getAttribute("loginuser").toString());
                            rolkul.setRol(rol);
                            rolkul.setAktifmi(aktif);
                            km.createrolkullanici(rolkul);

                        }
                    }





                } else {
                    session.setAttribute("statumessage", "TcNoVar");
                }

            } else {
                try {
                    kc.setKullaniciadi(ku.getKullaniciadi());
                    kc.setSifre(kullaniciService.getpassword());
                    kc.setAktif(Boolean.valueOf(true));
                    kc.setIsyazilim(ku.getIsyazilim());
                    kc.setKaydiguncelleyenkullanici(session.getAttribute("loginuser").toString());
                    kc.setKayitguncellemetarihi(new Date());
                    kc.setKaydiyapankullanici(session.getAttribute("loginuser").toString());
                    kc.setKayiteklemetarihi(new Date());

                    kc.setOrgKod(ku.getOrgKod());
                    kc.setOrganizasyonId(kullaniciService.getOrgIdWithOrgName(ku.getIsletmeadi()).get(0).getIsletmeId().toString());
                    kc.setCalistigibirim(ku.getCalistigibirim());
                    kc.setIsletmeadi(kc.getIsletmeadi());
                    kc.setUnvan(ku.getUnvan());
                    kc.setPersonelTip(ku.getPersonelTip());

                    /*
                     if (ku.getOrgKod().toString().equals("")) {
                     kc.setOrgKod("___");
                     System.out.println("add1....kc.getOrgKod....: " + kc.getOrgKod().toString());
                     } else {
                     kc.setOrgKod(ku.getOrgKod());
                     System.out.println("add2....ku.getOrgKod: " + ku.getOrgKod().toString());
                     }*/

                    kullaniciService.create(kc);

                    RolKullanici rolkul = new RolKullanici();

                    java.math.BigDecimal kullaniciid = new java.math.BigDecimal(String.valueOf(kc.getId()));

                    //rolkul.setKullanici(ku.getId());

                    rolkul.setKullanici(kullaniciid);



                    km.removerolkullanici(rolkul);


                    java.math.BigDecimal aktif = new java.math.BigDecimal(String.valueOf(1));
                    if (ku.getRoles() != null) {
                        for (int i = 0; i < ku.getRoles().size(); i++) {
                            java.math.BigDecimal rol = new java.math.BigDecimal(ku.getRoles().get(i).toString());
                            rolkul.setKullanici(kullaniciid);
                            rolkul.setEklemetarihi(new Date());
                            rolkul.setEkleyenkisi(session.getAttribute("loginuser").toString());
                            rolkul.setRol(rol);
                            rolkul.setAktifmi(aktif);
                            km.createrolkullanici(rolkul);

                        }
                    }
                    session.setAttribute("statumessage", "S");
                } catch (Exception e4) {
                    session.setAttribute("statumessage", e4.getMessage().toString());
                }
            }
        } else {
            session.setAttribute("statumessage", "KulVar");
        }





        return "redirect:personadd.html?tcno=null";




    }

    @RequestMapping(value = "add", params = "cancel", method = RequestMethod.POST)
    public String addCancel(@ModelAttribute(value = "Kullanici") KullaniciObj ku, ModelMap modelMap, Model m, HttpSession session, BindingResult result) {
        return "redirect:data.html";
    }

    @RequestMapping(value = "add", params = "doldur", method = RequestMethod.POST)
    public String dolduradd(@ModelAttribute(value = "Kullanici") Kullanici ku, ModelMap modelMap, HttpSession session) {

        Kullanici kc = new Kullanici();
        KullaniciModel km = new KullaniciModel();

        System.out.println("dolduradd........ku.getTcno().toString():" + ku.getTcno().toString());

        boolean kullaniciExists = km.checktcno(ku.getTcno());
        if (!kullaniciExists) {
            return "redirect:personadd.html?tcno=" + ku.getTcno();
        } else {
            session.setAttribute("statumessage", "TcNoVar");

            if (session.getAttribute("statumessage").equals("TcNoVar")) {
                modelMap.addAttribute("messageedit", "Bu TcNolu kişi sistemde mevcuttur!");
            }


            return "redirect:personadd.html?tcno=null";
        }

    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(@RequestParam(value = "id") int id, @RequestParam(value = "tcno") String tcno, Model m, ModelMap modelMap, HttpSession session) {


        //java.math.BigDecimal personid = new java.math.BigDecimal(String.valueOf(id));

        int personid = id;


        Kullanici k = new Kullanici();
        List<Kullanici> k1 = new ArrayList<Kullanici>();
        PersonelTip pt = new PersonelTip();
        IsletmeAdmin isletmeAdmin = new IsletmeAdmin();
        String orgId = null;
        boolean checkIsletmeAdmin = false;


        try {
            java.math.BigDecimal kisiIdBig = new java.math.BigDecimal(session.getAttribute("loginkisiid").toString());
            isletmeAdmin = kullaniciService.getIsletmeAdmin(kisiIdBig);
            System.out.println("orgId personadd=" + isletmeAdmin.getOrgId());
            orgId = isletmeAdmin.getOrgId();
            checkIsletmeAdmin = true;
        } catch (Exception e11) {
            orgId = null;
            checkIsletmeAdmin = false;
        }

        k = kullaniciService.getKullanici(personid);

        System.out.println("tcnom" + tcno);
        if (!tcno.equals("null")) {
            try {
                List<KullaniciDetay> kd = new ArrayList<KullaniciDetay>();

                if (orgId == null) {
                    kd = kullaniciService.getKullaniciDetay(tcno);
                    System.out.println("kisiidInt ORG ID null EDIT:" + orgId);

                } else {
                    //kd = kullaniciService.getKullaniciDetay(tcno);
                    //int kisiidInt = Integer.parseInt(session.getAttribute("loginkisiid").toString());
                    java.math.BigDecimal kisiId = new java.math.BigDecimal(String.valueOf(session.getAttribute("loginkisiid").toString()));

                    System.out.println("kisiId EDIT: " + kisiId);
                    System.out.println("CheckAdmin EDIT:" + orgId);
                    kd = kullaniciService.getKullaniciDetayWithOrgId(tcno, orgId);


                    /*
                     if (kullaniciService.checkAdmin(kisiId)) {
                     kd = kullaniciService.getKullaniciDetayWithOrgId(tcno, orgId);
                     }
                     */
                }
                //pt=kullaniciService.getPersonelTipTanim(kd.get(0).getPersoneltipId());


                //System.out.println("111111........edit..... personeltiptanim:");


                k.setAd(kd.get(0).getAd());

                k.setSoyad(kd.get(0).getSoyad());

                k.setAdsoyad(kd.get(0).getAd() + " " + kd.get(0).getSoyad());

                k.setKullaniciadi(kd.get(0).getAd() + "." + kd.get(0).getSoyad());

                k.setSicilno(kd.get(0).getSicilno());

                k.setOrgKod(kd.get(0).getOrgkod());

                k.setOrganizasyonId(kd.get(0).getOrganizationId());

                k.setYerlesimkod(kd.get(0).getYerlesimkod());

                k.setIsletmetipiId(kullaniciService.getIsletmeTipi(kd.get(0).getTasramerkez()).get(0).getId());

                k.setIsletmeadi(kd.get(0).getName());

                k.setKisiid(kd.get(0).getKisiId());

                k.setPersonelTip(kd.get(0).getPersoneltipId());

                k.setCalistigibirim(kd.get(0).getBirim());

                k.setIsletmetipi(kd.get(0).getTasramerkez());

                k.setUnvan(kd.get(0).getUnvani());

                k.setEposta(normalize(kd.get(0).getAd().toString()) + "." + normalize(kd.get(0).getSoyad().toString()) + "@euas.gov.tr");

                k.setIsyazilim(null);

                k.setTcno(kd.get(0).getTckimlikno());



//                try {
//                    String imagebase64;
//                    imagebase64 = Base64.encode(kullaniciService.getImage(kd.get(0).getKisiId()));
//                    m.addAttribute("kisiResim", imagebase64);
//                } catch (Exception e) {
//                }


                //k.setIsyazilim(Boolean.FALSE);
            } catch (Exception e2) {
                modelMap.addAttribute("messageedit", "Veri tabaninda veriler bulunamadi");
            }


        }
        if (session.getAttribute("statumessage") != null) {
            if (session.getAttribute("statumessage").equals("S")) {
                modelMap.addAttribute("messageedit", "Basari ile Guncellendi");
                session.setAttribute("statumessage", null);
            } else if (session.getAttribute("statumessage").equals("UpdImg")) {
                modelMap.addAttribute("messageedit", "Kişi Resmi Güncellendi...");
                session.setAttribute("statumessage", null);
            }
        }
        if (session.getAttribute("sifremessage") != null) {
            if (session.getAttribute("sifremessage").equals("S")) {

                modelMap.addAttribute("messageedit", "Basari ile sifre sifirlandi");
                session.setAttribute("sifremessage", null);
            }
        }





        try {
            pt = kullaniciService.getPersonelTipTanim(k.getPersonelTip());
        } catch (Exception e) {
        }
        //System.out.println("222222......edit..... personeltiptanim :"+pt.getTanim().toString());

        m.addAttribute("k", k);
        m.addAttribute("pt", pt);


        modelMap.put("Kullanici", k);

        m.addAttribute("isletmetip", kullaniciService.getAllIsletmeTipi());

        KullaniciModel km = new KullaniciModel();
        m.addAttribute("leftList", kullaniciService.getAllrol(session.getAttribute("loginkisiid").toString(), checkIsletmeAdmin));

        // m.addAttribute("leftList",km.getnotassignedrol(personid));

        java.math.BigDecimal personidassign = new java.math.BigDecimal(String.valueOf(id));

        m.addAttribute("rightList", km.getassignedrol(personidassign));

        m.addAttribute("orgNameList", kullaniciService.getAllIsletme());

        m.addAttribute("orgCodeList", kullaniciService.getOrgCodeList());


        //  m.addAttribute("rightList", kmodel.getAllrol());





        return "edit";
    }

    @RequestMapping(value = "update", params = "guncel", method = RequestMethod.POST)
    public String update(@ModelAttribute(value = "Kullanici") KullaniciObj ku, ModelMap modelMap, HttpSession session, BindingResult result) {



        Kullanici kc = new Kullanici();

        KullaniciModel km = new KullaniciModel();

        kc = kullaniciService.getKullanici(ku.getId());



        if (ku.getIsyazilim() == null) {

            try {
                kc.setKullaniciadi(ku.getKullaniciadi());
                kc.setAd(ku.getAd());

                kc.setSoyad(ku.getSoyad());

                kc.setAdsoyad(ku.getAd() + " " + ku.getSoyad());

                kc.setSicilno(ku.getSicilno());

                kc.setTcno(ku.getTcno());

                kc.setIsletmetipiId(kullaniciService.getIsletmeTipi(ku.getIsletmeadi()).get(0).getId());

                kc.setIsletmeadi(ku.getIsletmeadi());

                //System.out.println("isletmead>"+ku.getIsletmeadi().toString());

                kc.setUnvan(ku.getUnvan());

                kc.setAktif(ku.getAktif());

                kc.setPersonelTip(ku.getPersonelTip());

                kc.setCalistigibirim(ku.getCalistigibirim());

                kc.setKisiid(ku.getKisiid());

                //kc.setOrganizasyonId(ku.getOrganizasyonId());

                kc.setOrganizasyonId(kullaniciService.getOrgIdWithOrgName(ku.getIsletmeadi()).get(0).getIsletmeId().toString());

                kc.setOrgKod(ku.getOrgKod());

                kc.setYerlesimkod(ku.getYerlesimkod());

                kc.setKullaniciadi(ku.getKullaniciadi());

                kc.setEposta(ku.getEposta());

                kc.setIsyazilim(ku.getIsyazilim());

                kc.setIsletmetipi(ku.getIsletmetipi());

                kc.setKaydiguncelleyenkullanici(session.getAttribute("loginuser").toString());

                kc.setKayitguncellemetarihi(new Date());

                //kc.setKaydiyapankullanici(ku.getKaydiyapankullanici().toString());

                //kc.setKayiteklemetarihi(ku.getKayiteklemetarihi());

                System.out.println("isletmetipi" + ku.getIsletmetipi().toString());

                //System.out.println("pickdeger "+ku.getForm_languages().get(0).toString())  ;

                /* for(int i=0;i<ku.getLanguages().size();i++)
                 {
                 System.out.println("pickdeger "+ku.getLanguages().get(i).toString());
                 }*/




                kullaniciService.edit(kc);
                session.setAttribute("statumessage", "S");
            } catch (Exception e) {
                session.setAttribute("statumessage", e.toString());
            }

            java.math.BigDecimal kullaniciid = new java.math.BigDecimal(String.valueOf(ku.getId()));
            RolKullanici rolkul = new RolKullanici();

            //rolkul.setKullanici(ku.getId());

            rolkul.setKullanici(kullaniciid);


            km.removerolkullanici(rolkul);



            /* for(int i=0;i<ku.getLanguages().size();i++)
             {
             System.out.println("pickdeger "+ku.getLanguages().get(i).toString());
             }*/
            java.math.BigDecimal aktif = new java.math.BigDecimal(String.valueOf(1));
            if (ku.getRoles() != null) {
                for (int i = 0; i < ku.getRoles().size(); i++) {
                    java.math.BigDecimal rol = new java.math.BigDecimal(ku.getRoles().get(i).toString());
                    rolkul.setKullanici(kullaniciid);
                    rolkul.setEklemetarihi(new Date());
                    rolkul.setEkleyenkisi(session.getAttribute("loginuser").toString());
                    rolkul.setRol(rol);
                    rolkul.setAktifmi(aktif);
                    km.createrolkullanici(rolkul);

                }
            }
        } else {
            try {
                kc.setKullaniciadi(ku.getKullaniciadi());
                kc.setIsyazilim(ku.getIsyazilim());
                //kc.setKaydiyapankullanici(ku.getKaydiyapankullanici().toString());
                //kc.setKayiteklemetarihi(ku.getKayiteklemetarihi());
                kc.setKaydiguncelleyenkullanici(session.getAttribute("loginuser").toString());
                kc.setKayitguncellemetarihi(new Date());
                kc.setSifre(kullaniciService.getpassword());
                kc.setAktif(ku.getAktif());

                kc.setOrgKod(ku.getOrgKod());
                kc.setOrganizasyonId(kullaniciService.getOrgIdWithOrgName(ku.getIsletmeadi()).get(0).getIsletmeId().toString());
                kc.setCalistigibirim(ku.getCalistigibirim());
                kc.setIsletmeadi(ku.getIsletmeadi());
                kc.setUnvan(ku.getUnvan());
                kc.setPersonelTip(ku.getPersonelTip());

                kullaniciService.edit(kc);


                RolKullanici rolkul = new RolKullanici();

                java.math.BigDecimal kullaniciid = new java.math.BigDecimal(String.valueOf(kc.getId()));

                //rolkul.setKullanici(ku.getId());

                rolkul.setKullanici(kullaniciid);



                km.removerolkullanici(rolkul);


                java.math.BigDecimal aktif = new java.math.BigDecimal(String.valueOf(1));
                if (ku.getRoles() != null) {
                    for (int i = 0; i < ku.getRoles().size(); i++) {
                        java.math.BigDecimal rol = new java.math.BigDecimal(ku.getRoles().get(i).toString());
                        rolkul.setKullanici(kullaniciid);
                        rolkul.setEklemetarihi(new Date());
                        rolkul.setEkleyenkisi(session.getAttribute("loginuser").toString());
                        rolkul.setRol(rol);
                        rolkul.setAktifmi(aktif);
                        km.createrolkullanici(rolkul);

                    }
                }

                session.setAttribute("statumessage", "S");
            } catch (Exception e4) {
                session.setAttribute("statumessage", e4.getMessage().toString());
            }
        }

        return "redirect:edit.html?id=" + ku.getId() + "&tcno=null";



    }

    @RequestMapping(value = "update", params = "cancel", method = RequestMethod.POST)
    public String updateCancel(@ModelAttribute(value = "Kullanici") KullaniciObj ku, ModelMap modelMap, HttpSession session, BindingResult result) {
        return "redirect:data.html";
    }

    @RequestMapping(value = "update", params = "doldur", method = RequestMethod.POST)
    public String doldur(@ModelAttribute(value = "Kullanici") Kullanici ku) {

        Kullanici kc = new Kullanici();

        kc = kullaniciService.getKullanici(ku.getId());


        return "redirect:edit.html?id=" + ku.getId() + "&tcno=" + ku.getTcno();

    }

    @RequestMapping(value = "update", params = "passzero", method = RequestMethod.POST)
    public String passzero(@ModelAttribute(value = "Kullanici") Kullanici ku, HttpSession session) {



        Kullanici kc = new Kullanici();

        kc = kullaniciService.getKullanici(ku.getId());

        try {
            kc.setSifre(kullaniciService.getpassword());
            kc.setKayitguncellemetarihi(new Date());
            kc.setKaydiyapankullanici(session.getAttribute("loginuser").toString());

            System.out.println("kc.getSifre()..." + kc.getSifre().toString());




            kullaniciService.edit(kc);
            session.setAttribute("sifremessage", "S");
        } catch (Exception e2) {
            session.setAttribute("sifremessage", e2.getMessage());
        }


        return "redirect:edit.html?id=" + ku.getId() + "&tcno=" + null;

    }

    @RequestMapping(value = "organizasyonAta", method = RequestMethod.GET)
    public String organizasyonata(@RequestParam(value = "id") int id, Model m, HttpSession session, ModelMap modelMap) {

        //java.math.BigDecimal personid = new java.math.BigDecimal(String.valueOf(id));
        int personid = id;


        Kullanici k = new Kullanici();

        k = kullaniciService.getKullanici(personid);

        m.addAttribute("k", k);

        String deger = "";
        String isletmeadi = "";
        String orgKod = "";
        String yerlesimkod;

        try {
            yerlesimkod = k.getYerlesimkod().toString();
        } catch (Exception e2) {
            yerlesimkod = "";

        }
        try {
            deger = k.getIsletmeadi() + "-" + k.getOrgKod() + "-" + yerlesimkod;
        } catch (Exception e4) {
            deger = "";
        }

        m.addAttribute("deger", deger);
        m.addAttribute("orglist", kullaniciService.getAllorg(k.getIsletmeadi().toString()));

        if (session.getAttribute("orgmessage") != null) {
            if (session.getAttribute("orgmessage").equals("S")) {

                modelMap.addAttribute("orgmessage", "Basari ile organizasyon atandi");
                session.setAttribute("orgmessage", null);
            }
        }



        return "organizasyonAta";
    }

    @RequestMapping(value = "organizasyonataupdate", method = RequestMethod.POST)
    public String organizasyonataupdate(@ModelAttribute(value = "KullaniciOrganizasyon") KullaniciOrganizasyon ko, HttpSession session) {



        Kullanici kc = new Kullanici();

        kc = kullaniciService.getKullanici(ko.getId().intValue());

        ErpOrganizasyon eo = new ErpOrganizasyon();

        System.out.println("koid" + ko.getId().toString());

        eo = kullaniciService.getorganizasyon(ko.getOrgid());
        System.out.println("ko.getOrgid()" + ko.getOrgid().toString());
        try {
            if (!ko.getOrgid().toString().equals("1")) {
                System.out.println("girdim");
                kc.setIsletmeadi(eo.getName());
                // kc.setName(eo.getDescription());              
                kc.setOrgKod(eo.getOrganizationCode());
                kc.setYerlesimkod(eo.getGlYerlesim());
                kc.setKayitguncellemetarihi(new Date());
                kc.setKaydiyapankullanici(session.getAttribute("loginuser").toString());
                System.out.println("tamam");
            }
            kullaniciService.edit(kc);
            session.setAttribute("orgmessage", "S");
        } catch (Exception e) {
            session.setAttribute("orgmessage", e.toString());
            System.out.println("hatavar" + e.toString());
        }

        //return "update";
        //return "redirect:getAll.html";
        return "redirect:organizasyonAta.html?id=" + kc.getId();//"redirect:getAll.html";



    }

    @RequestMapping(value = "sifreDegistir", method = RequestMethod.GET)
    public String changepasswordzero(@RequestParam(value = "id") int id, Model m, HttpSession session, ModelMap modelMap) {

        //  java.math.BigDecimal personid = new java.math.BigDecimal(String.valueOf(id));

        int personid = id;



        Kullanici k = new Kullanici();

        k = kullaniciService.getKullanici(personid);

        m.addAttribute("k", k);


        if (session.getAttribute("sifremessage") != null) {
            if (session.getAttribute("sifremessage").equals("S")) {

                modelMap.addAttribute("messagepassword", "Basari ile sifre sifirlandi");
                session.setAttribute("sifremessage", null);
            }
        }



        return "sifreDegistir";
    }

    @RequestMapping(value = "passwordzeroupdate", method = RequestMethod.POST)
    public String changepasswordzeroupdate(@ModelAttribute(value = "Kullanici") Kullanici ku, HttpSession session) {



        Kullanici kc = new Kullanici();

        kc = kullaniciService.getKullanici(ku.getId());

        try {
            System.out.println("kmodelsifre" + kullaniciService.getpassword().toString());

            kc.setSifre(kullaniciService.getpassword());
            kc.setKayitguncellemetarihi(new Date());
            kc.setKaydiyapankullanici(session.getAttribute("loginuser").toString());
            // kc.setSifredegistir(Boolean.valueOf(true));


            System.out.println("kc.getSifre()..." + kc.getSifre().toString());




            kullaniciService.edit(kc);
            session.setAttribute("sifremessage", "S");
        } catch (Exception e2) {
            session.setAttribute("sifremessage", e2.getMessage());
        }

        //return "update";
        return "redirect:sifreDegistir.html?id=" + ku.getId();//"redirect:getAll.html";




    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String remove(@RequestParam(value = "id") int id, Model m) {
        // java.math.BigDecimal personid = new java.math.BigDecimal(String.valueOf(id));

        int personid = id;

        Kullanici k = new Kullanici();

        k = kullaniciService.getKullanici(personid);

        kullaniciService.remove(k);



        return "redirect:getAll.html";
    }

    public String normalize(String input) {
        String output = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

        return pattern.matcher(output).replaceAll("").toLowerCase();
    }

    @RequestMapping(value = "add", params = "uploadimage", method = RequestMethod.POST)
    public String addUploadImage(@ModelAttribute(value = "Kullanici") KullaniciObj ku, ModelMap modelMap, HttpServletRequest request, HttpSession session,
            @RequestParam CommonsMultipartFile[] fileUpload) {

        if (fileUpload != null && fileUpload.length > 0) {
            for (CommonsMultipartFile aFile : fileUpload) {

                System.out.println("uploadImage.......Saving file: " + aFile.getOriginalFilename());

                KisiResim kisiResim = new KisiResim();

                // Set Image
                Blob blob = Hibernate.createBlob(aFile.getBytes());
                kisiResim.setResim(blob);

                System.out.println("uploadImage.......Blob file created: " + blob.toString());

                System.out.println("uploadImage.......kisiid: " + ku.getKisiid().toString());
                kisiResim.setKisiid(ku.getKisiid());


                kullaniciService.saveImage(kisiResim);
            }
        }
        session.setAttribute("statumessage", "AddImg");



        return "redirect:personadd.html?tcno=" + ku.getTcno();
    }

    @RequestMapping(value = "update.html", params = "uploadimage", method = RequestMethod.POST)
    public String editUploadImage(@ModelAttribute(value = "Kullanici") KullaniciObj ku, ModelMap modelMap, HttpServletRequest request, HttpSession session,
            @RequestParam CommonsMultipartFile[] fileUpload) {

        if (fileUpload != null && fileUpload.length > 0) {
            for (CommonsMultipartFile aFile : fileUpload) {

                System.out.println("uploadImage.......Saving file: " + aFile.getOriginalFilename());

                KisiResim kisiResim = new KisiResim();

                // Set Image
                Blob blob = Hibernate.createBlob(aFile.getBytes());
                kisiResim.setResim(blob);

                System.out.println("uploadImage.......Blob file created: " + blob.toString());

                System.out.println("uploadImage.......kisiid: " + ku.getKisiid().toString());
                kisiResim.setKisiid(ku.getKisiid());


                kullaniciService.saveImage(kisiResim);
            }
        }

        System.out.print("update saveImage..ku.getId(): " + ku.getId());

        System.out.print("update saveImage..ku.getTcno(): " + ku.getTcno());

        session.setAttribute("statumessage", "UpdImg");

        return "redirect:edit.html?id=" + ku.getId() + "&tcno=" + ku.getTcno();
    }

    @RequestMapping(value = "sifreDegistirBI", method = RequestMethod.GET)
    public String editPersonPassword(@RequestParam(value = "kisiid") BigDecimal kisiid, Model m, ModelMap modelMap, HttpSession session) {

        //  java.math.BigDecimal personid = new java.math.BigDecimal(String.valueOf(id));

        Kullanici user = new Kullanici();

        user = kullaniciService.getKullanici(kisiid.intValueExact());

        Kullanici k = new Kullanici();

        System.out.println("editPersonPassword.......user.get(0).getId(): " + user.getId());
        k.setId(user.getId());
        k.setKullaniciadi(user.getKullaniciadi());
        k.setKisiid(user.getKisiid());
        k.setAktif(user.getAktif());
        k.setKaydiyapankullanici(user.getKaydiyapankullanici());





        //k = kullaniciService.getKullanici(personid);
        modelMap.put("Kullanici", k);
        m.addAttribute("k", k);


        if (session.getAttribute("sifremessage") != null) {
            if (session.getAttribute("sifremessage").equals("S")) {

                modelMap.addAttribute("messagepassword", "Basari ile sifre sifirlandi");
                session.setAttribute("sifremessage", null);
            }
        }



        return "sifreDegistirBI";
    }

    @RequestMapping(value = "updatepassword", params = "updatesifre", method = RequestMethod.POST)
    public RedirectView updatePersonPassword(@ModelAttribute(value = "Kullanici") Kullanici ku, ModelMap modelMap, HttpSession session, BindingResult result) {

        Kullanici kc = new Kullanici();
        KullaniciModel km = new KullaniciModel();
        System.out.println("updatePersonPassword.......ku.getId(): " + ku.getId());

        kc = kullaniciService.getKullanici(ku.getId());



        try {
            kc.setSifre(km.getHashPassword(ku.getSifre()));
            System.out.println("updatePersonPassword.......ku.getSifre(): " + ku.getSifre() + ".....km.getHashPassword(ku.getSifre()): " + km.getHashPassword(ku.getSifre()));
            kc.setAktif(ku.getAktif());
            System.out.println("updatePersonPassword.......ku.getAktif(): " + ku.getAktif().toString());
            kc.setKayitguncellemetarihi(new Date());
            kc.setKaydiguncelleyenkullanici(kc.getKullaniciadi().toString());
            System.out.println("updatePersonPassword.......kc.getKullaniciadi().toString(): " + kc.getKullaniciadi().toString());

            kc.setSifreDegistir(Boolean.valueOf(false));

            /*
             kc.setAd(user.get(0).getAd());

             kc.setSoyad(user.get(0).getSoyad());

             kc.setAdsoyad(user.get(0).getAd() + " " + user.get(0).getSoyad());

             kc.setKullaniciadi(user.get(0).getKullaniciadi());

             kc.setSicilno(user.get(0).getSicilno());

             kc.setOrgKod(user.get(0).getOrgKod());

             kc.setOrganizasyonId(user.get(0).getOrganizasyonId());

             kc.setYerlesimkod(user.get(0).getYerlesimkod());

             kc.setIsletmetipiId(user.get(0).getIsletmetipiId());

             kc.setIsletmeadi(user.get(0).getIsletmeadi());

             kc.setKisiid(user.get(0).getKisiid());

             kc.setPersonelTip(user.get(0).getPersonelTip());

             kc.setCalistigibirim(user.get(0).getCalistigibirim());

             kc.setIsletmetipi(user.get(0).getIsletmetipi());

             kc.setUnvan(user.get(0).getUnvan());

             kc.setEposta(normalize(user.get(0).getAd().toString()) + "." + normalize(user.get(0).getSoyad().toString()) + "@euas.gov.tr");

             //kc.setIsyazilim(null);

             kc.setTcno(user.get(0).getTcno());*/

            System.out.println("Controller.... updatepassword.....sifre: " + kc.getSifre().toString() + " .......kullanici_adi: " + kc.getKullaniciadi().toString());

            kullaniciService.edit(kc);
            session.setAttribute("sifremessage", "S");

        } catch (Exception e2) {
            System.out.println("Controller.... updatepassword.....hata");
            session.setAttribute("sifremessage", e2.getMessage());
        }

        String p = "base_url";
        RedirectView redirectView = new RedirectView();
        String url = kullaniciService.getUrl(p);
        redirectView.setUrl(url + "/analytics/saw.dll?BIEEHome&startPage=1");

        return redirectView;

    }

    @RequestMapping(value = "sifreDegistirBIReq", method = RequestMethod.GET)
    public String editPersonPasswordReq(@RequestParam(value = "kisiid") BigDecimal kisiid, Model m, ModelMap modelMap, HttpSession session) {

        //  java.math.BigDecimal personid = new java.math.BigDecimal(String.valueOf(id));

        Kullanici user = new Kullanici();

        user = kullaniciService.getKullanici(kisiid.intValueExact());

        Kullanici k = new Kullanici();

        System.out.println("editPersonPasswordReq.......user.get(0).getId(): " + user.getId());
        k.setId(user.getId());
        k.setKullaniciadi(user.getKullaniciadi());
        k.setKisiid(user.getKisiid());
        k.setAktif(user.getAktif());
        k.setKaydiyapankullanici(user.getKaydiyapankullanici());





        //k = kullaniciService.getKullanici(personid);
        modelMap.put("Kullanici", k);
        m.addAttribute("k", k);


        if (session.getAttribute("sifremessage") != null) {
            if (session.getAttribute("sifremessage").equals("S")) {

                modelMap.addAttribute("messagepassword", "Basari ile sifre sifirlandi");
                session.setAttribute("sifremessage", null);
            }
        }



        return "sifreDegistirBIReq";
    }

    @RequestMapping(value = "updatepasswordreq", params = "updatesifrereq", method = RequestMethod.POST)
    public String updatePersonPasswordReq(@ModelAttribute(value = "Kullanici") Kullanici ku, ModelMap modelMap, HttpSession session, BindingResult result) {

        System.out.println("updatePersonPasswordReq.....girdi");

        Kullanici kc = new Kullanici();
        KullaniciModel km = new KullaniciModel();
        System.out.println("updatePersonPasswordReq.......ku.getId(): " + ku.getId());

        kc = kullaniciService.getKullanici(ku.getId());



        try {
            kc.setSifre(km.getHashPassword(ku.getSifre()));
            System.out.println("updatePersonPasswordReq.......ku.getSifre(): " + ku.getSifre() + ".....km.getHashPassword(ku.getSifre()): " + km.getHashPassword(ku.getSifre()));
            kc.setAktif(ku.getAktif());
            System.out.println("updatePersonPasswordReq.......ku.getAktif(): " + ku.getAktif().toString());
            kc.setKayitguncellemetarihi(new Date());
            kc.setKaydiguncelleyenkullanici(kc.getKullaniciadi().toString());
            System.out.println("updatePersonPasswordReq.......kc.getKullaniciadi().toString(): " + kc.getKullaniciadi().toString());

            kc.setSifreDegistir(Boolean.valueOf(false));

            /*
             kc.setAd(user.get(0).getAd());

             kc.setSoyad(user.get(0).getSoyad());

             kc.setAdsoyad(user.get(0).getAd() + " " + user.get(0).getSoyad());

             kc.setKullaniciadi(user.get(0).getKullaniciadi());

             kc.setSicilno(user.get(0).getSicilno());

             kc.setOrgKod(user.get(0).getOrgKod());

             kc.setOrganizasyonId(user.get(0).getOrganizasyonId());

             kc.setYerlesimkod(user.get(0).getYerlesimkod());

             kc.setIsletmetipiId(user.get(0).getIsletmetipiId());

             kc.setIsletmeadi(user.get(0).getIsletmeadi());

             kc.setKisiid(user.get(0).getKisiid());

             kc.setPersonelTip(user.get(0).getPersonelTip());

             kc.setCalistigibirim(user.get(0).getCalistigibirim());

             kc.setIsletmetipi(user.get(0).getIsletmetipi());

             kc.setUnvan(user.get(0).getUnvan());

             kc.setEposta(normalize(user.get(0).getAd().toString()) + "." + normalize(user.get(0).getSoyad().toString()) + "@euas.gov.tr");

             //kc.setIsyazilim(null);

             kc.setTcno(user.get(0).getTcno());*/

            System.out.println("Controller.... updatepasswordreq.....sifre: " + kc.getSifre().toString() + " .......kullanici_adi: " + kc.getKullaniciadi().toString());

            kullaniciService.edit(kc);
            session.setAttribute("sifremessage", "S");

        } catch (Exception e2) {
            System.out.println("Controller.... updatepasswordreq.....hata");
            session.setAttribute("sifremessage", e2.getMessage());
        }

        return "redirect:sifreDegistirBIReq.html?kisiid=" + ku.getId();
    }

    @RequestMapping(value = "sifreDegistirBiLogin", method = RequestMethod.GET)
    public String editAdminPassword(Model m, ModelMap modelMap, HttpSession session) {

        List<User> biUser = new ArrayList<User>();
        User user = new User();

        String userName = session.getAttribute("loginuser").toString();
        biUser = loginService.getAdmin(userName);

        System.out.println("editAdminPassword.......user.get(0).getId(): " + biUser.get(0).getId());

        user.setId(biUser.get(0).getId());
        user.setKullaniciadi(biUser.get(0).getKullaniciadi());


        modelMap.put("biAdmin", user);
        m.addAttribute("admin", user);


        if (session.getAttribute("sifremessage") != null) {
            if (session.getAttribute("sifremessage").equals("S")) {

                modelMap.addAttribute("messagepassword", "Basari ile sifre değiştirildi.");
                session.setAttribute("sifremessage", null);
            }
        }


        return "sifreDegistirBiLogin";
    }

    @RequestMapping(value = "updateAdminPassword", params = "updateAdminSifre", method = RequestMethod.POST)
    public RedirectView updateAdminPassword(@ModelAttribute(value = "biAdmin") User u, ModelMap modelMap, HttpSession session, BindingResult result) {

        User user = new User();

        System.out.println("updateAdminPassword.......u.getId(): " + u.getId());

        user = loginService.getAdminById(u.getId());

        System.out.println("loginService.getAdminById...user.getId(): " + user.getId());
        System.out.println("loginService.getAdminById...user.getKullaniciadi(): " + user.getKullaniciadi());
        System.out.println("loginService.getAdminById...user.getSifre(): " + user.getSifre());

        try {
            user.setSifre(u.getSifre());
            System.out.println("loginService.user.getSifre(): " + user.getSifre());
            user.setKayitguncellemetarihi(new Date());
            System.out.println("loginService.user.getSifre(): " + user.getKayitguncellemetarihi());
            user.setKaydiguncelleyenkullanici(user.getKullaniciadi());

            System.out.println("Controller.... updateAdminPassword.....sifre: " + u.getSifre().toString() + " .......kullanici_adi: " + user.getKullaniciadi().toString());

            loginService.edit(user);
            session.setAttribute("sifremessage", "S");

        } catch (Exception e2) {
            System.out.println("Controller.... updateAdminPassword.....hata");
            session.setAttribute("sifremessage", e2.getMessage());
        }

        String p = "admin_url";
        RedirectView redirectView = new RedirectView();
        String url = kullaniciService.getUrl(p);
        redirectView.setUrl(url + "/BiLogin");

        return redirectView;

    }
}
