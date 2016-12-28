/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xxxt.bilogin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import org.springframework.beans.factory.annotation.Autowired;


import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpSession;


import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import xxxt.bilogin.db.entities.*;
import xxxt.login.service.*;


/**
 *
 * @author admin
 */

@Controller
//@RequestMapping(value="loginform.htm")
@RequestMapping(value="loginform.html")
public class LoginController {
    
    @Autowired
    public LoginService loginService;
    
    
   @RequestMapping(method = RequestMethod.GET)
    public String login(ModelMap modelMap){
        modelMap.put("acc", new User());
       // modelMap.addAttribute("message", "giris");
       return "loginform";
    }
    @RequestMapping(value="/loginform",method = RequestMethod.POST)
    public String login(@ModelAttribute(value="acc")User acc,ModelMap modelMap,HttpSession session){
       // modelMap.put("acc", new User());
        boolean userExists = loginService.checkLogin(acc.getKullaniciadi().toString(),acc.getSifre().toString());
        if(userExists){
             //return "redirect:getAll.html";
            System.out.println("Kullanıcı adı ="+acc.getKullaniciadi().toString());
             System.out.println("KISI ID ="+loginService.getKisiId(acc.getKullaniciadi().toString()));
             
            session.setAttribute("loginkisiid", loginService.getKisiId(acc.getKullaniciadi().toString()));
            session.setAttribute("loginuser", acc.getKullaniciadi().toString());

            //session.setAttribute("loginuserkisiid", acc.getKisiid().toString());  
            return "navmenu";
            //return "data";
        }
        else
        {
            modelMap.addAttribute("message", "Geçersiz Kullanıcı Adı ve Şifre");
            return "loginform";
            //return "redirect:loginform.htm";
        }
       
    }
}

