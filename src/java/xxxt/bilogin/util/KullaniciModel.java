/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xxxt.bilogin.util;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import xxxt.bilogin.db.SessionFactoryUtil;
import xxxt.bilogin.db.entities.ErpOrganizasyon;
import xxxt.bilogin.db.entities.IsletmeTipi;
import xxxt.bilogin.db.entities.Kullanici;
import xxxt.bilogin.db.entities.KullaniciDetay;
import xxxt.bilogin.db.entities.Parametre;
import xxxt.bilogin.db.entities.Rol;
import xxxt.bilogin.db.entities.RolKullanici;
import xxxt.bilogin.db.entities.Rolk;
import xxxt.bilogin.objects.PersonObj;

/**
 *
 * @author admin
 */
public class KullaniciModel {

    public List<Kullanici> getAllwithParam(PersonObj person) {
        List<Kullanici> lst = new ArrayList<Kullanici>();



        System.out.println("deneme" + person.getAd().toString());

        String ad = person.getAd().toString();
        String KullaniciAd = person.getKullaniciadi().toString();
        System.out.println("deneme" + KullaniciAd);

        SessionFactoryUtil.buildSessionFactory();
        Session session = null;
        Transaction tx = null;
        session = SessionFactoryUtil.getInstance().openSession();
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Kullanici.class, "person1");

            if ((person.getKullaniciadi() != null) && (!person.getKullaniciadi().equals(""))) {
                //criteria.add(Restrictions.eq("kullaniciadi", kullaniciadi.toUpperCase()).ignoreCase());
                criteria.add(Restrictions.ilike("kullaniciadi", person.getKullaniciadi(), MatchMode.ANYWHERE));
                System.out.println("girdi");
            }
            if ((person.getAd().toString() != null) && (!person.getAd().toString().equals(""))) {

                criteria.add(Restrictions.ilike("ad", person.getAd().toString(), MatchMode.ANYWHERE));
            }
            if ((person.getSoyad() != null) && (!person.getSoyad().equals(""))) {

                criteria.add(Restrictions.ilike("soyad", person.getSoyad(), MatchMode.ANYWHERE));
            }
            if ((person.getTcno() != null) && (!person.getTcno().equals(""))) {
                criteria.add(Restrictions.ilike("tcno", person.getTcno(), MatchMode.ANYWHERE));
            }
            if ((person.getIsletmeadi() != null) && (!person.getIsletmeadi().equals(""))) {

                criteria.add(Restrictions.ilike("isletmeadi", person.getIsletmeadi(), MatchMode.ANYWHERE));
            }

            if ((person.getOrgKod() != null) && (!person.getOrgKod().equals(""))) {

                criteria.add(Restrictions.ilike("orgKod", person.getOrgKod(), MatchMode.ANYWHERE));
            }
            if ((person.getSicilno() != null) && (!person.getSicilno().equals(""))) {

                criteria.add(Restrictions.ilike("sicilno", person.getSicilno(), MatchMode.ANYWHERE));
            }
            if ((person.getKisiid() != null) && (!person.getKisiid().toString().equals(""))) {
                criteria.add(Restrictions.ilike("kisiid".toString(), person.getKisiid().toString(), MatchMode.ANYWHERE));
            }

            //Kullanici kullan=(Kullanici)criteria.uniqueResult();

            lst = criteria.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            System.out.println("hata" + e.getMessage().toString());

        }

        return lst;



    }

    public List<Kullanici> getAll() {
        List<Kullanici> lst = new ArrayList<Kullanici>();

        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            lst = s.createCriteria(Kullanici.class).list();

            s.getTransaction().commit();



        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        } finally {
            s.close();
            SessionFactoryUtil.close();
        }



        return lst;

    }

    public List<IsletmeTipi> getAllIsletmeTipi() {
        List<IsletmeTipi> lst = new ArrayList<IsletmeTipi>();

        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            lst = s.createCriteria(IsletmeTipi.class).list();

            s.getTransaction().commit();



        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        } finally {
            s.close();
            SessionFactoryUtil.close();
        }



        return lst;

    }

    public List<ErpOrganizasyon> getAllorg(String isletmeadi) {
        List<ErpOrganizasyon> lst = new ArrayList<ErpOrganizasyon>();

        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            Criteria criteria = s.createCriteria(ErpOrganizasyon.class, "rol1");

            if ((isletmeadi != null) && (!isletmeadi.equals(""))) {
                //criteria.add(Restrictions.eq("kullaniciadi", kullaniciadi.toUpperCase()).ignoreCase());
                criteria.add(Restrictions.ne("description", isletmeadi));
                System.out.println("girdi");
            }

            lst = criteria.list();
            //lst=s.createQuery().list();
            //lst=s.createCriteria(ErpOrganizasyon.class).list();

            s.getTransaction().commit();



        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        } finally {
            s.close();
            SessionFactoryUtil.close();
        }


        return lst;

    }

    public String getpassword() {
        List<Parametre> lst = new ArrayList<Parametre>();

        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            Criteria criteria = s.createCriteria(Parametre.class, "rol1");


            //criteria.add(Restrictions.eq("kullaniciadi", kullaniciadi.toUpperCase()).ignoreCase());
            criteria.add(Restrictions.eq("pmetre", "default_sifre"));
            System.out.println("girdi");


            lst = criteria.list();
            //lst=s.createQuery().list();
            //lst=s.createCriteria(ErpOrganizasyon.class).list();

            s.getTransaction().commit();



        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        } finally {
            s.close();
            SessionFactoryUtil.close();
        }

        System.out.println("sifre" + lst.get(0).getDeger().toString());
        return lst.get(0).getDeger().toString();

    }

    public ErpOrganizasyon getorganizasyon(Long id) {

        ErpOrganizasyon organizasyon = new ErpOrganizasyon();
        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            organizasyon = (ErpOrganizasyon) s.get(ErpOrganizasyon.class, id);
            s.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        } finally {
            s.close();
            SessionFactoryUtil.close();
        }
        return organizasyon;

    }

    public List<KullaniciDetay> getKullaniciDetay(String tckimlikno) {
        List<KullaniciDetay> lst = new ArrayList<KullaniciDetay>();

        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            Criteria criteria = s.createCriteria(KullaniciDetay.class, "kullanicidetay");


            //criteria.add(Restrictions.eq("kullaniciadi", kullaniciadi.toUpperCase()).ignoreCase());
            criteria.add(Restrictions.eq("tckimlikno", tckimlikno));
            System.out.println("girdi");


            lst = criteria.list();
            //lst=s.createQuery().list();
            //lst=s.createCriteria(ErpOrganizasyon.class).list();

            s.getTransaction().commit();



        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        } finally {
            s.close();
            SessionFactoryUtil.close();
        }


        return lst;

    }

    public List<Rolk> getAllrol() {
        List<Rolk> lst = new ArrayList<Rolk>();

        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            //lst=s.createQuery().list();
            lst = s.createCriteria(Rolk.class).list();

            s.getTransaction().commit();



        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        } finally {
            s.close();
            SessionFactoryUtil.close();
        }


        return lst;

    }

    public List<Rol> getnotassignedrol(BigDecimal kullanici) {
        List<Rol> lst = new ArrayList<Rol>();
        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            String SQL_QUERY = " from xxxt.bilogin.db.entities.Rol as a where a.id not in(select rk.rol from xxxt.bilogin.db.entities.RolKullanici rk where rk.kullanici=?)";
            Query query = s.createQuery(SQL_QUERY);//.setString("kullaniciadi", userName);
            query.setParameter(0, kullanici);

            lst = query.list();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            s.close();
            SessionFactoryUtil.close();
        }
        return lst;
    }

    public List<Rol> getassignedrol(BigDecimal kullanici) {
        List<Rol> lst = new ArrayList<Rol>();
        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            String SQL_QUERY = " from xxxt.bilogin.db.entities.Rol as a where a.id  in(select rk.rol from xxxt.bilogin.db.entities.RolKullanici rk where rk.kullanici=?)";
            Query query = s.createQuery(SQL_QUERY);//.setString("kullaniciadi", userName);
            query.setParameter(0, kullanici);

            lst = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            s.close();
            SessionFactoryUtil.close();
        }
        return lst;
    }

    public void create(Kullanici k) {
        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            s.save(k);
            s.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        } finally {
            s.close();
            SessionFactoryUtil.close();
        }
    }

    public void remove(Kullanici k) {
        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            s.delete(k);
            s.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        }
    }

    public void removerolkullanici(RolKullanici k) {



        List<RolKullanici> lst = new ArrayList<RolKullanici>();



        RolKullanici rolkul = new RolKullanici();


        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            Criteria criteria = s.createCriteria(RolKullanici.class, "rolkullanici");


            //criteria.add(Restrictions.eq("kullaniciadi", kullaniciadi.toUpperCase()).ignoreCase());
            criteria.add(Restrictions.eq("kullanici", k.getKullanici()));
            System.out.println("girdi");

            lst = criteria.list();

            for (int i = 0; i < lst.size(); i++) {
                rolkul = (RolKullanici) s.get(RolKullanici.class, lst.get(i).getId());
                s.delete(rolkul);
            }




            s.getTransaction().commit();



        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        } finally {
            s.close();
            SessionFactoryUtil.close();
        }


    }

    public void createrolkullanici(RolKullanici k) {
        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            s.save(k);
            s.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        } finally {
            s.close();
            SessionFactoryUtil.close();
        }
    }

    public void edit(Kullanici k) {
        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            s.update(k);
            s.getTransaction().commit();
            System.out.println("edit girdi");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("edit hata");
            s.getTransaction().rollback();
        } finally {
            s.close();
            SessionFactoryUtil.close();
        }
    }

    public void edit_notcommit(Kullanici k) {
        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            s.update(k);

            System.out.println("edit girdi");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("edit hata");
            s.getTransaction().rollback();
        } finally {
            s.close();
            SessionFactoryUtil.close();
        }
    }

    public Kullanici getKullanici(int id) {

        Kullanici user = new Kullanici();
        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            user = (Kullanici) s.get(Kullanici.class, id);
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

    public boolean checkKullanici(String kullaniciad) {



        List<Kullanici> lst = new ArrayList<Kullanici>();
        boolean kullaniciFound = false;


        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            Criteria criteria = s.createCriteria(Kullanici.class, "kullanici");


            //criteria.add(Restrictions.eq("kullaniciadi", kullaniciadi.toUpperCase()).ignoreCase());
            criteria.add(Restrictions.eq("kullaniciadi", kullaniciad));



            lst = criteria.list();
            //lst=s.createQuery().list();
            //lst=s.createCriteria(ErpOrganizasyon.class).list();

            s.getTransaction().commit();



        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        } finally {
            s.close();
            SessionFactoryUtil.close();
        }



        if ((lst != null) && (lst.size() > 0)) {
            kullaniciFound = true;
        }


        return kullaniciFound;



    }

    public boolean checktcno(String tcno) {

        List<Kullanici> lst = new ArrayList<Kullanici>();
        boolean kullaniciFound = false;


        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            Criteria criteria = s.createCriteria(Kullanici.class, "kullanici");


            //criteria.add(Restrictions.eq("kullaniciadi", kullaniciadi.toUpperCase()).ignoreCase());
            criteria.add(Restrictions.eq("tcno", tcno));



            lst = criteria.list();
            //lst=s.createQuery().list();
            //lst=s.createCriteria(ErpOrganizasyon.class).list();

            s.getTransaction().commit();



        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        } finally {
            s.close();
            SessionFactoryUtil.close();
        }



        if ((lst != null) && (lst.size() > 0)) {
            kullaniciFound = true;
        }


        return kullaniciFound;



    }

    public String getHashPassword(String password) throws Exception {
        List<String> lst = new ArrayList<String>();
        String hashPassword = null;

        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        CallableStatement cs = null;
        Connection conn = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            tx = s.beginTransaction();
            conn = s.connection();
            if (cs == null) {

                cs = conn.prepareCall("{?=call BI_LOGIN.GET_HASHVAL(?)}");
                cs.clearParameters();
                cs.registerOutParameter(1, OracleTypes.VARCHAR);
                cs.setString(2, password);
                cs.execute();
                hashPassword = cs.getString(1);
                System.out.println("getHashPassword........ ORA_HASH(" + password + ") from dual): "+hashPassword);
                tx.commit();
                /*String SQL_QUERY = "Select ORA_HASH(?) from dual";
                 Query query = s.createQuery(SQL_QUERY);//.setString("kullaniciadi", userName);
                 query.setParameter(0, password);
                 lst = query.list();*/

            }
        } catch (Exception ex) {
            System.out.println("getHashPassword........ ORA_HASH(" + password + ") from dual):...........HATA.......");
            ex.printStackTrace();
            if (tx != null && tx.isActive()) {
                try {
                    // Second try catch as the rollback could fail as well
                    tx.rollback();
                } catch (HibernateException e1) {
                    System.out.println("Error rolling back transaction");
                }
                // throw again the first exception
                throw ex;
            }
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                    cs = null;
                }
                if (conn != null) {
                    conn.close();
                }

                s.close();
                SessionFactoryUtil.close();
            } catch (Exception ex) {;
            }
        }





        return hashPassword;
    }
}
