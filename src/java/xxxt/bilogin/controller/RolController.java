package xxxt.bilogin.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import xxxt.bilogin.db.*;
import xxxt.bilogin.db.entities.Altmenu;
import xxxt.bilogin.db.entities.Kullanici;
import xxxt.bilogin.db.entities.KullaniciDetay;
import xxxt.bilogin.db.entities.Rol;
import xxxt.bilogin.db.entities.RolAltMenu;
import xxxt.bilogin.db.entities.RolAltMenuT;
import xxxt.bilogin.db.entities.RolErpResponsibility;
import xxxt.bilogin.objects.RolObj;
import xxxt.bilogin.util.KullaniciModel;
import xxxt.login.service.*;

/**
 *
 * @author kutay.akyol
 */
@Controller
public class RolController {

    @Autowired
    private RolService rolService;

    @RequestMapping(value = "getAllRol", method = RequestMethod.GET)
    public String getAllRolList(Model m, ModelMap modelMap, HttpSession httpsession) {

        SessionFactoryUtil.buildSessionFactory();
        Session session = null;
        Transaction tx = null;
        session = SessionFactoryUtil.getInstance().openSession();
        try {
            tx = session.beginTransaction();
            modelMap.put("listRol", rolService.getAllRolList(session));
        } catch (Exception e) {
            System.out.println("Hibernate hata!!!!!!!!!!");
        } finally {
            session.close();
            SessionFactoryUtil.close();
        }
        return "rolList";
    }

    @RequestMapping(value = "addRol", method = RequestMethod.GET)
    public String addRol(Model m, ModelMap modelMap) {

        Altmenu altMenu = new Altmenu();
        Rol rol = new Rol();


        SessionFactoryUtil.buildSessionFactory();
        Session session = null;
        Transaction tx = null;
        session = SessionFactoryUtil.getInstance().openSession();
        try {
            tx = session.beginTransaction();
            //altMenu = (Altmenu) rolService.getListAltMenu(session);
            m.addAttribute("rol", rol);
            modelMap.put("Rol", rol);
            m.addAttribute("leftList", rolService.getListAltMenu(session));
        } catch (Exception e) {
            System.out.println("Hibernate hata!!!!!!!!!!");
        } finally {
            session.close();
            SessionFactoryUtil.close();
        }


        //m.addAttribute("aktifmi", rol.getAktifmi());
        //m.addAttribute("rolAltMenu", rolAltMenu);
        //m.addAttribute("a", altMenu

        return "addRol";
    }

    @RequestMapping(value = "createRol", params = "save", method = RequestMethod.POST)
    public String create(@ModelAttribute(value = "Rol") RolObj rol, ModelMap modelMap, HttpSession session, BindingResult result) {
        Rol rl = new Rol();
        //modelMap.addAttribute("aktifmi", rol.getAktifmi());
        try {
            System.out.println("rolId" + rol.getId().toString());
            System.out.println("rol_ad : " + rol.getRoladi().toString());
            System.out.println("rol_aciklama : " + rol.getAciklama().toString());
        } catch (Exception e) {
            System.out.println("addRol create.... roladi yok");
        }
        try {
            rl.setId(rol.getId());
            rl.setRoladi(rol.getRoladi());
            rl.setAciklama(rol.getAciklama());

            System.out.println("addrol.... create...  rol.getAktifmi(): " + rol.getAktifmi().toString());
            rl.setAktifmi(rol.getAktifmi());


            rl.setEkleyenkisi(session.getAttribute("loginuser").toString());
            rl.setEklemetarihi(new Date());
            rl.setGuncelleyenkisi(session.getAttribute("loginuser").toString());
            rl.setGuncellemetarihi(new Date());

            rolService.create(rl);
            session.setAttribute("statumessage", "S");
        } catch (Exception e) {
            session.setAttribute("statumessage", e.toString());
        }

        RolAltMenuT rolAltT = new RolAltMenuT();

        System.out.println("addRol create.......... String.valueOf(rol.getId()):  " + String.valueOf(rl.getId()));
        java.math.BigDecimal rolid = new java.math.BigDecimal(String.valueOf(rl.getId()));

        //rolkul.setKullanici(ku.getId());

        rolAltT.setIzinVerilenRollerId(rolid);



        rolService.removeRolMenu(rolAltT);


        //java.math.BigDecimal aktif= new java.math.BigDecimal(String.valueOf(1));
        if (rol.getRoles() != null) {
            for (int i = 0; i < rol.getRoles().size(); i++) {
                java.math.BigDecimal rolAlt = new java.math.BigDecimal(rol.getRoles().get(i).toString());
                rolAltT.setIzinVerilenRollerId(rolid);
                rolAltT.setAltMenulerId(rolAlt);
                rolService.createRolMenu(rolAltT);

            }
        }

        return "redirect:getAllRol.html";
    }

    @RequestMapping(value = "createRol", params = "cancel", method = RequestMethod.POST)
    public String createCancel(@ModelAttribute(value = "Rol") RolObj rol, ModelMap modelMap, HttpSession session, BindingResult result) {

        return "redirect:getAllRol.html";
    }

    @RequestMapping(value = "editRol", method = RequestMethod.GET)
    public String edit(@RequestParam(value = "id") Integer id, Model m, ModelMap modelMap) {
        java.math.BigDecimal rolId = new java.math.BigDecimal(String.valueOf(id));

        RolAltMenu rolAltMenuT = new RolAltMenu();
        Rol rol = new Rol();

        //rol = rolService.getRolRow(id);

        SessionFactoryUtil.buildSessionFactory();
        Session session = null;
        Transaction tx = null;
        session = SessionFactoryUtil.getInstance().openSession();

        tx = session.beginTransaction();
        rol = rolService.getRolRow(id);

        m.addAttribute("r", rol);
        modelMap.put("Rol", rol);

        m.addAttribute("leftList", rolService.getListAltMenu(session));





        System.out.println("rolService.getListAllowAltMenu(session,rolId).size(): " + rolService.getListAllowAltMenu(session, rolId).size());

        for (int i = 0; i < rolService.getListAllowAltMenu(session, rolId).size(); i++) {
            System.out.println("rolService.getListAllowAltMenu(session,rolId).get(i).getAltMenuAdi(" + i + "): " + rolService.getListAllowAltMenu(session, rolId).get(i).getAltMenuAdi().toString());
        }

        System.out.println("editRol... edit....rolId : " + rolId.toString());
        m.addAttribute("rightList", rolService.getListAllowAltMenu(session, rolId));
//        } catch (Exception e) {
//            System.out.println("editRol RolController.edit.....Hibernate hata!!!!!!!!!!");
//        } finally {
//            session.close();
//            SessionFactoryUtil.close();
//        }


        return "editRol";
    }

    @RequestMapping(value = "updateRol", params = "save", method = RequestMethod.POST)
    public String update(@ModelAttribute(value = "Rol") RolObj rol, ModelMap modelMap, HttpSession session, BindingResult result) {
        Rol rl = new Rol();

        rl = rolService.getRolRow(rol.getId());

        try {
            System.out.println("rolId" + rol.getId().toString());
            System.out.println("rol_ad : " + rol.getRoladi().toString());
            System.out.println("rol_aciklama : " + rol.getAciklama().toString());
        } catch (Exception e) {
            System.out.println("editRol update ....  roladi yok");
        }

        try {

            rl.setRoladi(rol.getRoladi());
            rl.setAciklama(rol.getAciklama());
            rl.setAktifmi(rol.getAktifmi());

            /*if (chk) {
             rl.setAktifmi(bd1);
             } else {
             rl.setAktifmi(bd0);
             }*/

            rl.setGuncelleyenkisi(session.getAttribute("loginuser").toString());
            rl.setGuncellemetarihi(new Date());


            rolService.edit(rl);

            session.setAttribute("statumessage", "S");
        } catch (Exception e) {
            session.setAttribute("statumessage", e.toString());
        }


        RolAltMenuT rolAltT = new RolAltMenuT();

        System.out.println("editRol update .......... String.valueOf(rol.getId()):  " + String.valueOf(rl.getId()));
        java.math.BigDecimal rolid = new java.math.BigDecimal(String.valueOf(rl.getId()));

        //rolkul.setKullanici(ku.getId());

        rolAltT.setIzinVerilenRollerId(rolid);



        rolService.removeRolMenu(rolAltT);


        //java.math.BigDecimal aktif= new java.math.BigDecimal(String.valueOf(1));
        if (rol.getRoles() != null) {
            for (int i = 0; i < rol.getRoles().size(); i++) {
                java.math.BigDecimal rolAlt = new java.math.BigDecimal(rol.getRoles().get(i).toString());
                rolAltT.setIzinVerilenRollerId(rolid);
                rolAltT.setAltMenulerId(rolAlt);
                rolService.createRolMenu(rolAltT);

            }
        }

        //return "update";
        return "redirect:getAllRol.html";
    }

    @RequestMapping(value = "updateRol", params = "cancel", method = RequestMethod.POST)
    public String updateCancel(@ModelAttribute(value = "Rol") RolObj rol, ModelMap modelMap, HttpSession session, BindingResult result) {

        return "redirect:getAllRol.html";
    }

    @RequestMapping(value = "rolEsle", method = RequestMethod.GET)
    public String rolErpEsle(@RequestParam(value = "id") Integer id, Model m, ModelMap modelMap) {
        java.math.BigDecimal rolId = new java.math.BigDecimal(String.valueOf(id));

        RolErpResponsibility rolErp = new RolErpResponsibility();
        Rol rol = new Rol();

        //rol = rolService.getRolRow(id);

        SessionFactoryUtil.buildSessionFactory();
        Session session = null;
        Transaction tx = null;
        session = SessionFactoryUtil.getInstance().openSession();

        tx = session.beginTransaction();
        rol = rolService.getRolRow(id);

        m.addAttribute("r", rol);
        modelMap.put("Rol", rol);

        m.addAttribute("leftList", rolService.getListErpResponsibility(session));





        System.out.println("rolService.getListAllowErpResponsibility(session,rolId).size(): " + rolService.getListAllowErpResponsibility(session, rolId).size());

        for (int i = 0; i < rolService.getListAllowErpResponsibility(session, rolId).size(); i++) {
            System.out.println("rolService.getListAllowErpResponsibility(session,rolId).get(i).getAltMenuAdi(" + i + "): " + rolService.getListAllowErpResponsibility(session, rolId).get(i).getResponsibilityname().toString());
        }

        System.out.println("erpRolEsle... edit....rolId : " + rolId.toString());
        m.addAttribute("rightList", rolService.getListAllowErpResponsibility(session, rolId));
//        } catch (Exception e) {
//            System.out.println("editRol RolController.edit.....Hibernate hata!!!!!!!!!!");
//        } finally {
//            session.close();
//            SessionFactoryUtil.close();
//        }


        return "rolEsle";
    }

    @RequestMapping(value = "updateRolErp", params = "save", method = RequestMethod.POST)
    public String updateRolErp(@ModelAttribute(value = "RolErp") RolObj rol, ModelMap modelMap, HttpSession session, BindingResult result) {
        Rol rl = new Rol();

        rl = rolService.getRolRow(rol.getId());

        try {
            System.out.println("rolId" + rol.getId().toString());
            System.out.println("rol_ad : " + rol.getRoladi().toString());
            System.out.println("rol_aciklama : " + rol.getAciklama().toString());
        } catch (Exception e) {
            System.out.println("editRol update ....  roladi yok");
        }
        RolErpResponsibility rolErp = new RolErpResponsibility();

        System.out.println("editRol update .......... String.valueOf(rol.getId()):  " + String.valueOf(rl.getId()));
        java.math.BigDecimal rolid = new java.math.BigDecimal(String.valueOf(rl.getId()));

        //rolkul.setKullanici(ku.getId());

        rolErp.setRolid(rolid);

        rolService.removeRolErpResp(rolErp);

        System.out.println("editRol......... updateRolErp.......rol.getRoles().size(): "+rol.getRoles().size());
        
        
        //java.math.BigDecimal aktif= new java.math.BigDecimal(String.valueOf(1));
        if (rol.getRoles() != null) {
            for (int i = 0; i < rol.getRoles().size(); i++) {
                java.math.BigDecimal rolAlt = new java.math.BigDecimal(rol.getRoles().get(i).toString());
                rolErp.setRolid(rolid);
                rolErp.setErpsorumlulularid(rolAlt);
                rolService.createRolErpResp(rolErp);

            }
        }

        //return "update";
        return "redirect:getAllRol.html";
    }
    
    @RequestMapping(value = "updateRolErp", params = "cancel", method = RequestMethod.POST)
    public String updateCancelErp(@ModelAttribute(value = "RolErp") RolObj rol, ModelMap modelMap, HttpSession session, BindingResult result) {

        return "redirect:getAllRol.html";
    }
    
    

    @RequestMapping(value = "removeRol", method = RequestMethod.GET)
    public String remove(@RequestParam(value = "id") Integer id, Model m) {
        java.math.BigDecimal rolId = new java.math.BigDecimal(String.valueOf(id));

        Rol rl = new Rol();

        rl = rolService.getRolRow(id);

        rolService.remove(rl);



        return "redirect:getAllRol.html";
    }
}