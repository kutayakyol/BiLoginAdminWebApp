/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xxxt.login.service;

import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xxxt.bilogin.dao.*;
import xxxt.bilogin.db.entities.*;

/**
 *
 * @author kutay.akyol
 */


@Service("RolService")
public class RolServiceImpl implements RolService {
    
    /// Member variables
    //@Autowired
    private RolRepository rolRepository;
    
    public RolServiceImpl(){
    
    }
    
    /// Constructor injection 
    public RolServiceImpl(RolRepository rolRepository){
        this.rolRepository=rolRepository;
    }
    
    
    /// Setter injection 
    @Autowired
    public void setRolRepository(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }
    
    
    
    @Override
    public List<Rol> getAllRolList(Session session){
        return rolRepository.getAllRolList(session);
                  }
    
    @Override
    public List<Rol> getAllRolListWithParam(Session session){
        return rolRepository.getAllRolListWithParam(session);
                  }
    
    @Override 
    public Rol getRolRow(Integer id)
    {
        return rolRepository.getRolRow(id);
    }
    
    
    @Override
    public List<Altmenu> getListAltMenu(Session session){
       return rolRepository.getListAltMenu(session);
    }
    
    /**
     *
     * @param session
     * @param id
     * @return
     */
    @Override
    public List<RolAltMenu> getListAllowAltMenu(Session session,BigDecimal id){
       return rolRepository.getListAllowAltMenu(session, id);
    }
    
    @Override
    public void createRolMenu(RolAltMenuT r){
      rolRepository.createRolMenu(r);
    }
    
    @Override
    public void removeRolMenu(RolAltMenuT r){
      rolRepository.removeRolMenu(r);
    }
    
    @Override
    public List<ErpResponsibility> getListErpResponsibility(Session session){
       return rolRepository.getListErpResponsibility(session);
    }
    
    /**
     *
     * @param session
     * @param id
     * @return
     */
    @Override
    public List<ErpResponsibility> getListAllowErpResponsibility(Session session,BigDecimal id){
       return rolRepository.getListAllowErpResponsibility(session, id);
    }
    
    @Override
    public void createRolErpResp(RolErpResponsibility rolErp){
      rolRepository.createRolErpResp(rolErp);
    }
    
    @Override
    public void removeRolErpResp(RolErpResponsibility rolErp){
      rolRepository.removeRolErpResp(rolErp);
    }
    
    @Override
    public void create(Rol r)
    {
      rolRepository.create(r);
    }
    
    @Override
    public void edit(Rol rol)
    {
      rolRepository.edit(rol);
    }
    
    @Override
    public void remove (Rol rol){
        rolRepository.remove(rol);
    }
    
}
