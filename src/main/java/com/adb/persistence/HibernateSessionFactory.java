package com.adb.persistence;

import com.adb.config.Config;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public enum HibernateSessionFactory {
    INSTANCE;

    private final Configuration configuration;

    HibernateSessionFactory() {
        try {
            switch (Config.getEnvironment()) {
                case PRODUCTION:
                    this.configuration = setProductionConfiguration();
                    break;
                case TEST:
                case DEVELOPMENT:
                    this.configuration = setDefaultConfiguration();
                    break;
                default:
                    throw new UnsupportedOperationException("Unable to create a Hibernate Configuration for the Environment.");

            }
        } catch (Exception ex) {
            throw new ExceptionInInitializerError(ex);

        }

    }

    public SessionFactory buildSessionFactory() {
        return this.configuration.buildSessionFactory();

    }

    private static Configuration setProductionConfiguration() {
        return setDefaultConfiguration();

    }

    private static Configuration setDefaultConfiguration() {
        Configuration cfg = new Configuration();

        //Load default configuration from file.
        cfg.configure("hibernate.cfg.xml");

        return cfg;

    }

}
