/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xxxt.bilogin.util;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author admin
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;
    
  /*  static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }*/
   /* public static HibernateUtil(){
       sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }*/
    
    public static SessionFactory getSessionFactory() {
      if (sessionFactory==null){
         sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); 
      }
      return sessionFactory;
    }

}
