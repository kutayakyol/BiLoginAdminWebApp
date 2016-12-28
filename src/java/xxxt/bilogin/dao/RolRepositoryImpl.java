/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xxxt.bilogin.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import xxxt.bilogin.db.SessionFactoryUtil;
import xxxt.bilogin.db.entities.*;
import xxxt.bilogin.util.Consts;
import xxxt.bilogin.util.HibernateUtil;

/**
 *
 * @author kutay.akyol
 */
@Repository("RolRepository")
public class RolRepositoryImpl implements RolRepository {

    Consts consts = new Consts();

    @Override
    public List<Rol> getAllRolList(Session session) {
        List<Rol> rolList = new ArrayList<Rol>();

        Criteria criteria = session.createCriteria(Rol.class, consts.rol_hb);

        rolList = criteria.list();


        return rolList;
    }

    @Override
    public List<Rol> getAllRolListWithParam(Session session) {
        List<Rol> rolList = new ArrayList<Rol>();
        Criteria criteria = session.createCriteria(Rol.class, consts.rol_hb);

        /*if ((einvNumber != null) && (!einvNumber.equals(""))) {
         if ((tcIdNumber != null) && (!tcIdNumber.equals(""))) {
         criteria.add(Restrictions.and(Restrictions.eq("tcIdNumber", tcIdNumber), Restrictions.eq("einvNumber", einvNumber)));
         } else if ((taxRegNumber != null) && (!taxRegNumber.equals(""))) {
         criteria.add(Restrictions.and(Restrictions.eq("taxRegNumber", taxRegNumber), Restrictions.eq("einvNumber", einvNumber)));
         } else {
         criteria.add(Restrictions.eq("einvNumber", einvNumber));
         }
         } else if ((tcIdNumber != null) && (!tcIdNumber.equals(""))) {
         criteria.add(Restrictions.eq("tcIdNumber", tcIdNumber));
         } else if ((taxRegNumber != null) && (!taxRegNumber.equals(""))) {
         criteria.add(Restrictions.eq("taxRegNumber", taxRegNumber));
         } else {
         criteria.setMaxResults(0);
         }
         criteria.add(Restrictions.eq("statusId", statusId));
         */

        rolList = criteria.list();

        return rolList;
    }

    @Override
    public Rol getRolRow(Integer id) {
        
        Rol rol = new Rol();
        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            rol = (Rol) s.get(Rol.class, id);
            s.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        }finally {
            s.close();
            SessionFactoryUtil.close();
        }
        return rol;
    }

    @Override
    public List<Altmenu> getListAltMenu(Session session) {

        List<Altmenu> altMenuList = new ArrayList<Altmenu>();
        Criteria criteria = session.createCriteria(Altmenu.class, consts.rolAltMenu_hb);
        altMenuList = criteria.list();
        return altMenuList;
    }

    /**
     *
     * @param session
     * @param id
     * @return
     */
    @Override
    public List<RolAltMenu> getListAllowAltMenu(Session session, BigDecimal id) {


        List<RolAltMenu> rolAltMenuList = new ArrayList<RolAltMenu>();
        Criteria criteria = session.createCriteria(RolAltMenu.class, consts.rolAltMenu_hb);
        if ((id != null) && (!id.toString().equals(""))) {
            criteria.add(Restrictions.eq("izinVerilenRollerId", id));
        }
        rolAltMenuList = criteria.list();

        System.out.println("repository.....rolAltMenuList.size(): " + rolAltMenuList.size());

        for (int i = 0; i < rolAltMenuList.size(); i++) {
            System.out.println("repository.....rolAltMenuList.get(" + i + ").getAltMenuAdi(): " + rolAltMenuList.get(i).getAltMenuAdi().toString());
        }


        return rolAltMenuList;
    }

    @Override
    public void createRolMenu(RolAltMenuT r) {
        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            s.save(r);
            s.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        }finally {
            s.close();
            SessionFactoryUtil.close();
        }
    }

    @Override
    public void removeRolMenu(RolAltMenuT r) {



        List<RolAltMenuT> lst = new ArrayList<RolAltMenuT>();

        

        RolAltMenuT rolkul = new RolAltMenuT();

SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            Criteria criteria = s.createCriteria(RolAltMenuT.class, "rolaltmenu");


            //criteria.add(Restrictions.eq("kullaniciadi", kullaniciadi.toUpperCase()).ignoreCase());
            criteria.add(Restrictions.eq("izinVerilenRollerId", r.getIzinVerilenRollerId()));
            System.out.println("girdi");

            lst = criteria.list();

            for (int i = 0; i < lst.size(); i++) {
                System.out.println("repository removeRolMenu lst(" + i + "): " + lst.get(i).getId());
            }

            for (int i = 0; i < lst.size(); i++) {
                rolkul = (RolAltMenuT) s.get(RolAltMenuT.class, lst.get(i).getId());
                s.delete(rolkul);
            }




            s.getTransaction().commit();



        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        }finally {
            s.close();
            SessionFactoryUtil.close();
        }


    }

    @Override
    public List<ErpResponsibility> getListErpResponsibility(Session session) {

        List<ErpResponsibility> erpResponsibilityList = new ArrayList<ErpResponsibility>();
        Criteria criteria = session.createCriteria(ErpResponsibility.class, consts.erpResponsibility_hb);
        erpResponsibilityList = criteria.list();
        return erpResponsibilityList;
    }

    @Override
    public List<ErpResponsibility> getListAllowErpResponsibility(Session session, BigDecimal id) {


        List<ErpResponsibility> erpResponsibilityList = new ArrayList<ErpResponsibility>();

        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
        String SQL_QUERY = " from xxxt.bilogin.db.entities.ErpResponsibility as a where a.id  in (select rep.erpsorumlulularid from xxxt.bilogin.db.entities.RolErpResponsibility rep where rep.rolid=?)";
        Query query = s.createQuery(SQL_QUERY);//.setString("kullaniciadi", userName);
        query.setParameter(0, id);

        erpResponsibilityList = query.list();
        
        System.out.println();
        
        
        for(int i=0;i<erpResponsibilityList.size();i++)
            System.err.println("repository.........erpResponsibilityList.get("+i+").getResponsibilityname(): : "+ erpResponsibilityList.get(i).getResponsibilityname().toString());
         } catch (Exception e) {
            e.printStackTrace();
            
        }finally {
            s.close();
            SessionFactoryUtil.close();
        }

        

        return erpResponsibilityList;
    }

    @Override
    public void createRolErpResp(RolErpResponsibility rolErp) {
        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            System.out.println("createRolErpResp...girdi");
            s.save(rolErp);
            s.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("createRolErpResp...hata");
            e.printStackTrace();
            s.getTransaction().rollback();
        }finally {
            s.close();
            SessionFactoryUtil.close();
        }
    }

    @Override
    public void removeRolErpResp(RolErpResponsibility rolErp) {
        List<RolErpResponsibility> lst = new ArrayList<RolErpResponsibility>();


        RolErpResponsibility rolErpDel = new RolErpResponsibility();

SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            Criteria criteria = s.createCriteria(RolErpResponsibility.class, consts.erpResponsibility_hb);


            //criteria.add(Restrictions.eq("kullaniciadi", kullaniciadi.toUpperCase()).ignoreCase());
            criteria.add(Restrictions.eq("rolid", rolErp.getRolid()));
            System.out.println("removeRolErpResp...girdi");

            lst = criteria.list();

            for (int i = 0; i < lst.size(); i++) {
                System.out.println("repository removeRolErpResp lst(" + i + "): " + lst.get(i).getId());
            }

            for (int i = 0; i < lst.size(); i++) {
                rolErpDel = (RolErpResponsibility) s.get(RolErpResponsibility.class, lst.get(i).getId());
                s.delete(rolErpDel);
            }




            s.getTransaction().commit();



        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        }finally {
            s.close();
            SessionFactoryUtil.close();
        }

    }

    @Override
    public void create(Rol r) {
        SessionFactoryUtil.buildSessionFactory();
        Session session = null;
        Transaction tx = null;
        session = SessionFactoryUtil.getInstance().openSession();
        try {
            tx = session.beginTransaction();
            session.save(r);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
            SessionFactoryUtil.close();
        }
    }

    @Override
    public void edit(Rol rol) {
        SessionFactoryUtil.buildSessionFactory();
        Session session = null;
        Transaction tx = null;
        session = SessionFactoryUtil.getInstance().openSession();
        try {
            tx = session.beginTransaction();
            session.update(rol);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
            SessionFactoryUtil.close();
        }

    }

    @Override
    public void remove(Rol r) {
        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            s.delete(r);
            s.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        }finally {
            s.close();
            SessionFactoryUtil.close();
        }
    }
}
