package com.adb.persistence;

import com.adb.domain.Orders;
import com.adb.domain.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

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

            if (order != null) {
                session.delete(order);

                session.flush();
                tx.commit();
            }


        } catch (Exception e) {
            if (tx != null)
                tx.rollback();

            throw new RuntimeException(String.format("Error deleting the Order (%d) from the DB.", orderId), e);

        } finally {
            session.close();

        }

    }

    public void deleteUser(Integer userId) {
        Users users;
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            users = session.get(Users.class, userId);

            if (users != null) {
                session.delete(users);

                session.flush();
                tx.commit();
            }


        } catch (Exception e) {
            if (tx != null)
                tx.rollback();

            throw new RuntimeException(String.format("Error deleting the User (%d) from the DB.", userId), e);

        } finally {
            session.close();

        }

    }

    public Users getUser(Integer userId) {
        Users users;
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            users = session.get(Users.class, userId);

        } catch (Exception e) {
            if (tx != null)
                tx.rollback();

            throw new RuntimeException(String.format("Error getting the User (%d) from the DB.", userId), e);

        } finally {
            session.close();

        }

        return users;

    }

    public List<Users> getUsersForId(Integer userId, String name) {
        List<Users> users;
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("SELECT u FROM Users u WHERE u.userID <> :userId AND u.firstName = :name");
            query.setParameter("userId", userId);
            query.setParameter("name", name);

            users = query.getResultList();

        } catch (Exception e) {
            if (tx != null)
                tx.rollback();

            throw new RuntimeException(String.format("Error getting the User List for User id (%d) from the DB.", userId), e);

        } finally {
            session.close();

        }

        return users;

    }

}