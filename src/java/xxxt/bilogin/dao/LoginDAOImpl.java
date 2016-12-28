/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xxxt.bilogin.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import org.hibernate.Session;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import xxxt.bilogin.db.SessionFactoryUtil;
import xxxt.bilogin.db.entities.User;
import xxxt.bilogin.util.Consts;

/**
 *
 * @author admin
 */
@Repository("loginDAO")
public class LoginDAOImpl implements LoginDAO {

    Consts consts = new Consts();
    
    @Override
    public boolean checkLogin(String userName, String userPassword) {
        SessionFactoryUtil.buildSessionFactory();
        Session session = null;
        Transaction tx = null;
        session = SessionFactoryUtil.getInstance().openSession();

        boolean userFound = false;

        List<User> loginList = new ArrayList<User>();

        try {
            Criteria criteria = session.createCriteria(User.class, consts.login_hb);
            if ((userName != null) && (!userName.toString().equals(""))) {
                criteria.add(Restrictions.eq("kullaniciadi", userName));
            }
            if ((userPassword != null) && (!userPassword.toString().equals(""))) {
                criteria.add(Restrictions.eq("sifre", userPassword));
            }
            loginList = criteria.list();
          

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            SessionFactoryUtil.close();
        }



        System.out.println("loginDAO.....loginList.size(): " + loginList.size());

        for (int i = 0; i < loginList.size(); i++) {
            System.out.println("repository.....rolAltMenuList.get(" + i + ").getKullaniciadi(): " + loginList.get(i).getKullaniciadi().toString());
            System.out.println("repository.....rolAltMenuList.get(" + i + ").getSifre(): " + loginList.get(i).getSifre().toString());
        }

        if ((loginList != null) && (loginList.size() > 0)) {
            userFound = true;
        }

        return userFound;



    }
    
     @Override
    public User getAdminById(BigDecimal id) {
        User user = new User();

        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            user = (User) s.get(User.class, id);
            s.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        } finally {
            s.close();
            SessionFactoryUtil.close();
        }
        return user;

    }
     
    @Override
    public BigDecimal getKisiId(String Username) {
       SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();

        boolean userFound = false;

        List<User> loginList = new ArrayList<User>();

        try {
            Criteria criteria = s.createCriteria(User.class, consts.login_hb);
            if ((Username != null) && (!Username.toString().equals(""))) {
                criteria.add(Restrictions.eq("kullaniciadi", Username));
            }
            loginList = criteria.list();
          
        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        } finally {
            s.close();
            SessionFactoryUtil.close();
        }
        return loginList.get(0).getKisiid();

    }
    
    @Override
    public List<User> getAdmin(String userName){
        List<User> loginList = new ArrayList<User>();

        SessionFactoryUtil.buildSessionFactory();
        Session session = null;
        Transaction tx = null;
        session = SessionFactoryUtil.getInstance().openSession();
        try {
            Criteria criteria = session.createCriteria(User.class, consts.login_hb);
            if ((userName != null) && (!userName.toString().equals(""))) {
                criteria.add(Restrictions.eq("kullaniciadi", userName));
            }
            loginList = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            SessionFactoryUtil.close();
        }
        
        return loginList;
    }
    
    @Override
    public void edit(User u) {
        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            s.update(u);
            s.getTransaction().commit();
            System.out.println("LoginDAO...edit girdi");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("LoginDAO...edit hata");
            s.getTransaction().rollback();
        } finally {
            s.close();
            SessionFactoryUtil.close();
        }
    }

    
}
