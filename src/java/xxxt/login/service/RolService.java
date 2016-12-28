/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xxxt.login.service;

import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import xxxt.bilogin.db.entities.Altmenu;
import xxxt.bilogin.db.entities.ErpResponsibility;
import xxxt.bilogin.db.entities.Rol;
import xxxt.bilogin.db.entities.RolAltMenu;
import xxxt.bilogin.db.entities.RolAltMenuT;
import xxxt.bilogin.db.entities.RolErpResponsibility;

/**
 *
 * @author kutay.akyol
 */
public interface RolService {

    List<Rol> getAllRolList(Session session);

    List<Rol> getAllRolListWithParam(Session session);

    Rol getRolRow(Integer id);

    List<Altmenu> getListAltMenu(Session session);

    List<RolAltMenu> getListAllowAltMenu(Session session, BigDecimal id);

    void createRolMenu(RolAltMenuT r);

    void removeRolMenu(RolAltMenuT r);
    
    List<ErpResponsibility> getListErpResponsibility(Session session);

    List<ErpResponsibility> getListAllowErpResponsibility(Session session, BigDecimal id);

    void createRolErpResp(RolErpResponsibility rolErp);

    void removeRolErpResp(RolErpResponsibility rolErp);

    void create(Rol r);

    void edit(Rol rol);

    void remove(Rol rol);
}
