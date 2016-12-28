/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xxxt.bilogin.dao;

import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import xxxt.bilogin.db.entities.*;

/**
 *
 * @author kutay.akyol
 */
public interface RolRepository {

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

    void remove(Rol r);
}
