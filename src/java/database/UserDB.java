/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.List;
import java.util.logging.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Role;
import models.User;

/**
 *
 * @author 798794
 */
public class UserDB {
    
    public int insert(User user) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            Role role = em.find(Role.class, 2);  // 2 is for regular user
            user.setRole(role);
            
            trans.begin();
            em.persist(user);
            trans.commit();
            return 1;
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, "Cannot insert " + user.toString(), ex);
            throw new NotesDBException("Error inserting user");
        } finally {
            em.close();
        }
    }

    public int update(User user) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(user);
            trans.commit();
            return 1;
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, "Cannot update " + user.toString(), ex);
            throw new NotesDBException("Error updating user");
        } finally {
            em.close();
        }
    }

    public List<User> getAll() throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            List<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();
            return users;                
        } finally {
            em.close();
        }
    }

    public User getUser(String username) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            User user = em.find(User.class, username);
            return user;
        } finally {
            em.close();
        }
    }

    public int delete(User user) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.remove(em.merge(user));
            trans.commit();
            return 1;
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, "Cannot delete " + user.toString(), ex);
            throw new NotesDBException("Error deleting user");
        } finally {
            em.close();
        }
    }
    
}
