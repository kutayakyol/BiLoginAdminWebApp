/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xxxt.bilogin.dao;

import java.math.BigDecimal;
import java.util.List;
import xxxt.bilogin.db.entities.User;

/**
 *
 * @author admin
 */
public interface LoginDAO {
    
    boolean checkLogin(String username,String password);
    User getAdminById(BigDecimal id);
    List<User> getAdmin(String userName);
    void edit(User u);
    BigDecimal getKisiId(String userName);
    
}
