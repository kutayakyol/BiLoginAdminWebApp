/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xxxt.bilogin.dao;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import xxxt.bilogin.db.SessionFactoryUtil;
import xxxt.bilogin.db.entities.ErpOrganizasyon;
import xxxt.bilogin.db.entities.Isletme;
import xxxt.bilogin.db.entities.IsletmeAdmin;
import xxxt.bilogin.db.entities.IsletmeDetay;
import xxxt.bilogin.db.entities.IsletmeTipi;
import xxxt.bilogin.db.entities.KisiResim;
import xxxt.bilogin.db.entities.KisiResimPromise;
import xxxt.bilogin.db.entities.Kullanici;
import xxxt.bilogin.db.entities.KullaniciDetay;
import xxxt.bilogin.db.entities.OrgCode;
import xxxt.bilogin.db.entities.Parametre;
import xxxt.bilogin.db.entities.ParametreT;
import xxxt.bilogin.db.entities.PersonelTip;
import xxxt.bilogin.db.entities.Rol;
import xxxt.bilogin.db.entities.RolDuyuru;
import xxxt.bilogin.db.entities.Rolk;
import xxxt.bilogin.db.entities.UserLog;
import xxxt.bilogin.objects.PersonObj;

/**
 *
 * @author admin
 */
@Repository("KullaniciRepository")
public class KullaniciRepositoryImpl implements KullaniciRepository {

    @Override
    public List<Kullanici> getAllwithParam(PersonObj person, String orgName) {
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

            /*Isletme admin yetkilendirme calisması*/
            if (orgName != null) {
                criteria.add(Restrictions.eq("isletmeadi", orgName));
            }
            /*Isletme admin yetkilendirme calisması*/
            
            
            lst = criteria.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            System.out.println("hata" + e.getMessage().toString());

        } finally {
            session.close();
            SessionFactoryUtil.close();
        }

        return lst;



    }

    @Override
    public List<Kullanici> getAll(String orgName) {
        List<Kullanici> lst = new ArrayList<Kullanici>();

        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            Criteria criteria = s.createCriteria(Kullanici.class);
            //Criteria criteria = session.createCriteria(Kullanici.class, "person1");

            /*Isletme admin yetkilendirme calisması*/
            if (orgName != null) {
                criteria.add(Restrictions.eq("isletmeadi", orgName));
            }
            /*Isletme admin yetkilendirme calisması*/
            
            lst = criteria.list();
            System.out.println("KullaniciRepository...getAll...girdi");


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

    @Override
    public List<String> getAllIsletmeTipi() {
        List<IsletmeTipi> lst = new ArrayList<IsletmeTipi>();

        List<String> listeisletme = new ArrayList<String>();

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


        for (int i = 0; i < lst.size(); i++) {
            listeisletme.add(lst.get(i).getIsletmetipi());
        }

        return listeisletme;

    }

    @Override
    public List<IsletmeTipi> getIsletmeTipi(String isletmetipi) {
        List<IsletmeTipi> lst = new ArrayList<IsletmeTipi>();



        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            Criteria criteria = s.createCriteria(IsletmeTipi.class, "rol1");

            if ((isletmetipi != null) && (!isletmetipi.equals(""))) {
                //criteria.add(Restrictions.eq("kullaniciadi", kullaniciadi.toUpperCase()).ignoreCase());
                criteria.add(Restrictions.ne("isletmetipi", isletmetipi));

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

    @Override
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

    @Override
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

    @Override
    public List<UserLog> getPasswordLogWithId(String id) {
        List<UserLog> userLogList = new ArrayList<UserLog>();

        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            String SQL_QUERY = " from  xxxt.bilogin.db.entities.UserLog as l where l.statu='UPDATED' and l.sifre<>'3477096604' and l.kullaniciid=? order by l.kayitguncellemetarihi desc";
            Query query = s.createQuery(SQL_QUERY);//.setString("kullaniciadi", userName);
            query.setParameter(0, id);
            userLogList = query.list();
        } catch (Exception e) {
            e.printStackTrace();
            //s.getTransaction().rollback();
        } finally {
            s.close();
            SessionFactoryUtil.close();
        }

        return userLogList;
    }

    @Override
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

    @Override
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
            System.out.println("getKullaniciDetay");


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

    @Override
    public List<KullaniciDetay> getKullaniciDetayWithOrgId(String tckimlikno, String orgName) {
        List<KullaniciDetay> lst = new ArrayList<KullaniciDetay>();


        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            Criteria criteria = s.createCriteria(KullaniciDetay.class);


            //criteria.add(Restrictions.eq("kullaniciadi", kullaniciadi.toUpperCase()).ignoreCase());
            criteria.add(Restrictions.eq("tckimlikno", tckimlikno));
            criteria.add(Restrictions.eq("isletmeadi", orgName));
            System.out.println("getKullaniciDetayWithOrgId");


            lst = criteria.list();
            System.out.println("List" + lst);
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

    @Override
    public List<Rolk> getAllrol(String loginuser, boolean pIsletmeAdmin) {
        List<Rolk> lst = new ArrayList<Rolk>();

        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            //lst=s.createQuery().list();
            if ("2914".equals(loginuser) || "39099".equals(loginuser)) {
                String SQL_QUERY = " from xxxt.bilogin.db.entities.Rolk as a where a.id in (9847,881,902,883)";
                Query query = s.createQuery(SQL_QUERY);//.setString("kullaniciadi", userName);
                lst = query.list();
            } else if (pIsletmeAdmin) {
                //lst = s.createCriteria(Rolk.class).list();
                String SQL_QUERY = " from xxxt.bilogin.db.entities.Rolk as a where a.id in (9847,145,881,882,902,883)";
                Query query = s.createQuery(SQL_QUERY);//.setString("kullaniciadi", userName);
                lst = query.list();
            } else {
                lst = s.createCriteria(Rolk.class).list();
            }

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

    @Override
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

    @Override
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
        } finally {
            s.close();
            SessionFactoryUtil.close();
        }

    }

    @Override
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

    @Override
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

        } finally {
            s.close();
            SessionFactoryUtil.close();
        }
    }

    @Override
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

    @Override
    public PersonelTip getPersonelTipTanim(BigDecimal id) {

        PersonelTip tanim = new PersonelTip();
        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            tanim = (PersonelTip) s.get(PersonelTip.class, id);
            s.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        }
        return tanim;

    }

    /**
     *
     * @param kr
     */
    @Override
    public void saveImage(KisiResim kr) {
        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            s.save(kr);
            s.getTransaction().commit();
            try {
                System.out.println("repository....saveImage.......File saved! kisiid: " + kr.getKisiid().toString());
            } catch (Exception e) {
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("repository....saveImage.......hata!");
            s.getTransaction().rollback();
        } finally {
            s.close();
            SessionFactoryUtil.close();
        }
    }

    /**
     *
     * @param kisiid
     * @return
     */
    @Override
    public byte[] getImage(BigDecimal kisiid, String p) {


        List<KisiResim> lst = new ArrayList<KisiResim>();
        List<KisiResimPromise> lstPromise = new ArrayList<KisiResimPromise>();

        List<CommonsMultipartFile> img = new ArrayList<CommonsMultipartFile>();

        Blob resimBlob = null;
        byte[] blobAsByte = null;

        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {

            s.beginTransaction();
            String SQL_QUERY = null;

            if ("I".equals(p)) {

                System.out.println("getImage...insert");

                SQL_QUERY = " from xxxt.bilogin.db.entities.KisiResimPromise as a where a.id =(select MAX(kr.id) from xxxt.bilogin.db.entities.KisiResimPromise kr where kr.kisiid=?)";
                Query query = s.createQuery(SQL_QUERY);//.setString("kullaniciadi", userName);
                query.setParameter(0, kisiid);

                lstPromise = query.list();
                try {
                    resimBlob = lstPromise.get(0).getResim();
                    System.out.println("repository.....getImage....lstPromise.get(0).getId()...: " + lstPromise.get(0).getId().toString());
                } catch (Exception e) {
                    resimBlob = null;
                }

                /**
                 * *Promise den bi_login e resim aktarimi**
                 */
                if (resimBlob != null) {
                    try {
                        KisiResim kisiResim = new KisiResim();

                        // Set Image
                        //Blob blob = Hibernate.createBlob(aFile.getBytes());
                        kisiResim.setResim(resimBlob);

                        System.out.println("uploadImage.......Blob file created: " + resimBlob.toString());

                        System.out.println("uploadImage.......kisiid: " + kisiid.toString());
                        kisiResim.setKisiid(kisiid);


                        s.save(kisiResim);
                        s.getTransaction().commit();
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("repository....promise den aktarım....saveImage.......hata!");
                        s.getTransaction().rollback();
                    }
                    /**
                     * *Promise den bi_login e resim aktarimi**
                     */
                }
            } else if ("U".equals(p)) {

                System.out.println("getImage...update");

                SQL_QUERY = " from xxxt.bilogin.db.entities.KisiResim as a where a.id =(select MAX(kr.id) from xxxt.bilogin.db.entities.KisiResim kr where kr.kisiid=?)";
                Query query = s.createQuery(SQL_QUERY);//.setString("kullaniciadi", userName);
                query.setParameter(0, kisiid);

                lst = query.list();

                resimBlob = lst.get(0).getResim();
                System.out.println("repository.....getImage....lst.get(0).getId()...: " + lst.get(0).getId().toString());
            }

            int blobLenght = (int) resimBlob.length();
            System.out.println("repository.....getImage....bloblenght...: " + blobLenght);
            blobAsByte = resimBlob.getBytes(1, blobLenght);
            /*kr  = (KisiResim) s.get(KisiResim.class, kisiid);
             resimBlob=kr.getResim();
             s.getTransaction().commit();*/


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("repository.....getimage....HATA");
        } finally {
            s.close();
            SessionFactoryUtil.close();
        }

        return blobAsByte;
    }

    /**
     *
     * @param kisiid
     * @return
     */
    @Override
    public byte[] getImagePrev(BigDecimal kisiid) {


        List<KisiResim> lst = new ArrayList<KisiResim>();

        List<CommonsMultipartFile> img = new ArrayList<CommonsMultipartFile>();

        Blob resimBlob = null;
        byte[] blobAsByte = null;

        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {

            s.beginTransaction();
            String SQL_QUERY = " from xxxt.bilogin.db.entities.KisiResim as a where a.kisiid=? order by a.id desc";
            Query query = s.createQuery(SQL_QUERY);//.setString("kullaniciadi", userName);
            query.setParameter(0, kisiid);

            lst = query.list();

            try {
                resimBlob = lst.get(1).getResim();
                System.out.println("repository.....getImagePrev....lst.get(0).getId()...: " + lst.get(1).getId().toString());
            } catch (Exception e) {
                resimBlob = lst.get(0).getResim();
                System.out.println("repository.....getImagePrev....lst.get(1).getId()...: " + lst.get(0).getId().toString());
            }

            int blobLenght = (int) resimBlob.length();
            System.out.println("repository.....getImagePrev....bloblenght...: " + blobLenght);
            blobAsByte = resimBlob.getBytes(1, blobLenght);
            /*kr  = (KisiResim) s.get(KisiResim.class, kisiid);
             resimBlob=kr.getResim();
             s.getTransaction().commit();*/


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("repository.....getimage....HATA");
        } finally {
            s.close();
            SessionFactoryUtil.close();
        }

        return blobAsByte;
    }

    @Override
    public List<Kullanici> getPersonWithPersonId(BigDecimal kisiid) {


        List<Kullanici> lst = new ArrayList<Kullanici>();

        Kullanici k = new Kullanici();

        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            Criteria criteria = s.createCriteria(Kullanici.class, "personpass");

            if ((kisiid != null) && (!kisiid.toString().equals(""))) {
                //criteria.add(Restrictions.eq("kullaniciadi", kullaniciadi.toUpperCase()).ignoreCase());
                criteria.add(Restrictions.eq("kisiid", kisiid));
                System.out.println("getPersonWithPersonId girdi");
            }

            /*String SQL_QUERY = " from xxxt.bilogin.db.entities.Kullanici as a where a.kisiid =?)";
             Query query = s.createQuery(SQL_QUERY);//.setString("kullaniciadi", userName);
             query.setParameter(0, kisiid);

             lst = query.list();*/
            lst = criteria.list();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            s.close();
            SessionFactoryUtil.close();
        }

        return lst;
    }

    @Override
    public String getUrl(String url) {
        List<ParametreT> lst = new ArrayList<ParametreT>();

        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            if (url == "admin_url") {
                s.beginTransaction();
                Criteria criteria = s.createCriteria(ParametreT.class, "rol1");
                //criteria.add(Restrictions.eq("kullaniciadi", kullaniciadi.toUpperCase()).ignoreCase());
                criteria.add(Restrictions.eq("pmetre", url));
                lst = criteria.list();
                System.out.println("getUrl....girdi...");
                //lst=s.createQuery().list();
                //lst=s.createCriteria(ErpOrganizasyon.class).list();
                s.getTransaction().commit();
            }
            if (url == "base_url") {
                s.beginTransaction();
                Criteria criteria = s.createCriteria(ParametreT.class, "rol1");
                //criteria.add(Restrictions.eq("kullaniciadi", kullaniciadi.toUpperCase()).ignoreCase());
                criteria.add(Restrictions.eq("pmetre", url));
                lst = criteria.list();
                System.out.println("getUrl....girdi...");
                //lst=s.createQuery().list();
                //lst=s.createCriteria(ErpOrganizasyon.class).list();
                s.getTransaction().commit();
            }


        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        } finally {
            s.close();
            SessionFactoryUtil.close();
        }

        System.out.println("getutl.....url: " + lst.get(0).getDeger().toString());
        return lst.get(0).getDeger().toString();

    }

    @Override
    public List<Isletme> getAllIsletme() {
        List<Isletme> lst = new ArrayList<Isletme>();


        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            Criteria criteria = s.createCriteria(Isletme.class, "isletme");


            //criteria.add(Restrictions.eq("kullaniciadi", kullaniciadi.toUpperCase()).ignoreCase());

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

    @Override
    public List<Isletme> getOrgIdWithOrgName(String orgName) {
        List<Isletme> lst = new ArrayList<Isletme>();

        System.out.println("getOrgIdWithOrgName......orgName: " + orgName);

        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            Criteria criteria = s.createCriteria(Isletme.class, "isletme");
            criteria.add(Restrictions.eq("isletmeAdi", orgName));

            //criteria.add(Restrictions.eq("kullaniciadi", kullaniciadi.toUpperCase()).ignoreCase());

            lst = criteria.list();

            System.out.println("getOrgIdWithOrgName......lst.get(0).getIsletmeId: " + lst.get(0).getIsletmeId().toString());
            //lst=s.createQuery().list();
            //lst=s.createCriteria(ErpOrganizasyon.class).list();

            s.getTransaction().commit();



        } catch (Exception e) {

            System.out.println("getOrgIdWithOrgName......HATA!!!");
            e.printStackTrace();
            s.getTransaction().rollback();
        } finally {
            s.close();
            SessionFactoryUtil.close();
        }


        return lst;
    }

    @Override
    public List<OrgCode> getOrgCodeList() {
        List<OrgCode> lst = new ArrayList<OrgCode>();


        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            Criteria criteria = s.createCriteria(OrgCode.class, "orgCode");
//            ProjectionList projList = Projections.projectionList();
//            projList.add(Projections.property("orgkod"));
//            criteria.setProjection(Projections.distinct(projList));


            //criteria.add(Restrictions.eq("kullaniciadi", kullaniciadi.toUpperCase()).ignoreCase());

            lst = criteria.list();
            System.out.println("getOrgCodeList........ OK!");
            //lst=s.createQuery().list();
            //lst=s.createCriteria(ErpOrganizasyon.class).list();





        } catch (Exception e) {

            System.out.println("getOrgCodeList........ HATA!!!!!");


            e.printStackTrace();

        } finally {
            s.close();
            SessionFactoryUtil.close();
        }

        return lst;




    }

    @Override
    public String getOrgCodeWithOrgName(String orgName) {
        List<IsletmeDetay> lst = new ArrayList<IsletmeDetay>();
        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            String SQL_QUERY = " from xxxt.bilogin.db.entities.IsletmeDetay as a where a.isletmeAdi =? and rownum=1";
            Query query = s.createQuery(SQL_QUERY);//.setString("kullaniciadi", userName);
            query.setParameter(0, orgName);

            lst = query.list();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            s.close();
            SessionFactoryUtil.close();
        }
        return lst.get(0).getOrgKod().toString();
    }

    @Override
    public List<RolDuyuru> getDuyuruList(BigDecimal userId) {
        List<RolDuyuru> rolDuyuruList = new ArrayList<RolDuyuru>();

        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            String SQL_QUERY = " from xxxt.bilogin.db.entities.RolDuyuru as a where a.id  in(select rk.rol from xxxt.bilogin.db.entities.RolKullanici rk where rk.kullanici=?)";
            Query query = s.createQuery(SQL_QUERY);//.setString("kullaniciadi", userName);
            query.setParameter(0, userId);

            rolDuyuruList = query.list();
            System.out.println("getDuyuruList........ OK!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            s.close();
            SessionFactoryUtil.close();
        }

        return rolDuyuruList;
    }

    @Override
    public String getOrgid(String kisiid) {
        List<KullaniciDetay> lst = new ArrayList<KullaniciDetay>();
        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            Criteria criteria = s.createCriteria(KullaniciDetay.class);
            if ((kisiid != null) && (!kisiid.equals(""))) {
                //criteria.add(Restrictions.eq("kullaniciadi", kullaniciadi.toUpperCase()).ignoreCase());
                System.out.println("kisiid str:" + kisiid);
                java.math.BigDecimal kisiIdBig = new java.math.BigDecimal(kisiid);
                System.out.println("kisiid BD:" + kisiIdBig);
                //int kisiidint = Integer.parseInt(kisiid);
                criteria.add(Restrictions.eq("kisiId", kisiIdBig));
                System.out.println("getOrgid girdi");
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
        System.out.println(lst);
        System.out.println(lst.get(0).getOrgkod());
        return lst.get(0).getOrgkod();

    }

    @Override
    public IsletmeAdmin getIsletmeAdmin(BigDecimal kisiid) {
        List<IsletmeAdmin> lst = new ArrayList<IsletmeAdmin>();
        IsletmeAdmin isletmeAdmin = new IsletmeAdmin();

        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        boolean userFound = false;

        try {
            Criteria criteria = s.createCriteria(IsletmeAdmin.class);
            if ((kisiid != null)) {
                criteria.add(Restrictions.eq("kisiid", kisiid));
            }

            lst = criteria.list();


        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        } finally {
            s.close();
            SessionFactoryUtil.close();
        }
        if ((lst != null) && (lst.size() > 0)) {
            userFound = true;
        }

        isletmeAdmin = lst.get(0);


        return isletmeAdmin;

    }
}
