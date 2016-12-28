/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xxxt.login.service;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.nio.cs.ext.Big5;
import xxxt.bilogin.dao.LoginDAO;
import xxxt.bilogin.db.entities.User;

/**
 *
 * @author admin
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {
    
    @Autowired
    private LoginDAO loginDAO;
    
    
    
    public void setLoginDAO(LoginDAO loginDAO)
    {
        this.loginDAO=loginDAO;
    }
    
    @Override
    public boolean checkLogin(String userName, String userPassword)
    {
        return loginDAO.checkLogin(userName, userPassword);
    }
    
    @Override
    public User getAdminById(BigDecimal id){
        return loginDAO.getAdminById(id);
    }
    
    @Override
    public BigDecimal getKisiId(String userName){
        return loginDAO.getKisiId(userName);
    }
    
    @Override
    public List<User> getAdmin(String userName){
        return loginDAO.getAdmin(userName);
    }
    
    @Override
    public void edit(User u){
        loginDAO.edit(u);
    }
}
