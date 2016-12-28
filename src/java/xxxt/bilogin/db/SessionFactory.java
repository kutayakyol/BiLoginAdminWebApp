/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xxxt.bilogin.db;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author kubilay.aygen
 */
public class SessionFactory {
    private static org.hibernate.SessionFactory sessionFactory;

    
    public static void buildSessionFactoryTest(String passwd, String tnsInfo) {
        Configuration configuration = new Configuration();
        configuration.addResource("hibernate.hbm.xml")
                .setProperty("hibernate.connection.password", passwd)
                .setProperty("hibernate.connection.url", tnsInfo).configure("test.hibernate.cfg.xml");
        sessionFactory = configuration.buildSessionFactory();
    }

    public static void buildSessionFactory(String datasourceName, String dialect, String trxManagerLookupClass) {
        Configuration configuration = new Configuration();
        configuration.addResource("hibernate.hbm.xml")
                .setProperty("hibernate.connection.datasource", datasourceName)
                .setProperty("hibernate.dialect", dialect)
                .setProperty("hibernate.transaction.manager_lookup_class", trxManagerLookupClass).configure("hibernate.cfg.xml");
        sessionFactory = configuration.buildSessionFactory();

    }

    public static org.hibernate.SessionFactory getInstance() {
        return sessionFactory;
    }

    /**
     * Opens a session and will not bind it to a session context
     *
     * @return the session
     */
    public Session openSession() {
        return sessionFactory.openSession();
    }

    /**
     * Returns a session from the session context. If there is no session in the context it opens a session, stores it in the context and
     * returns it. This factory is intended to be used with a hibernate.cfg.xml including the following property <property
     * name="current_session_context_class">thread</property> This would return the current open session or if this does not exist, will
     * create a new session
     *
     * @return the session
     */
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * closes the session factory
     */
    public static void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
        sessionFactory = null;

    }
    
}
