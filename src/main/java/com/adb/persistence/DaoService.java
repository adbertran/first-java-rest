package com.adb.persistence;

import com.adb.domain.Orders;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public enum DaoService {
    INSTANCE;

    private final SessionFactory sessionFactory;

    DaoService() {
        this.sessionFactory = HibernateSessionFactory.INSTANCE.buildSessionFactory();

    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;

    }

    public void merge(Object object) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            session.merge(object);

            session.flush();
            tx.commit();

        } catch (Exception e) {
            if (tx != null)
                tx.rollback();

            throw e;

        } finally {
            session.close();

        }

    }

    public Orders getOrder(Long orderId) {
        Orders record;
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            record = session.get(Orders.class, orderId);

            tx.commit();

        } catch (Exception e) {
            if (tx != null)
                tx.rollback();

            throw new RuntimeException(String.format("Error obtaining the Order (%d) from the DB.", orderId), e);

        } finally {
            session.close();

        }

        return record;

    }

    public void deleteOrder(Long orderId) {
        Orders order;
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            order = session.get(Orders.class, orderId);

            if (order != null)
                session.delete(order);

            tx.commit();

        } catch (Exception e) {
            if (tx != null)
                tx.rollback();

            throw new RuntimeException(String.format("Error deleting the Order (%d) from the DB.", orderId), e);

        } finally {
            session.close();

        }

    }

}