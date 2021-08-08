package org.nhrin.eventorganizer.dao;

import org.nhrin.eventorganizer.entity.AppUser;
import org.nhrin.eventorganizer.entity.EventEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.Set;

@Repository
@Transactional
public class AppUserDAO {

    @Autowired
    private EntityManager entityManager;

    public AppUser findUserAccount(String userName) {
        try {
            String sql = "Select e from " + AppUser.class.getName() + " e " //
                    + " Where e.userName = :userName ";

            Query query = entityManager.createQuery(sql, AppUser.class);
            query.setParameter("userName", userName);

            return (AppUser) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public AppUser findUserAccount(Long userId) {
        try {
            String sql = "Select e from " + AppUser.class.getName() + " e " //
                    + " Where e.userId = :userId ";

            Query query = entityManager.createQuery(sql, AppUser.class);
            query.setParameter("userId", userId);

            return (AppUser) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    public Set<EventEntity> findEvents (Long userId) {
        try {
            AppUser user = findUserAccount(userId);
            Set<EventEntity> events = user.getVisitEvents();
            return events;
        } catch (NoResultException e) {
            return null;
        }
    }


}
