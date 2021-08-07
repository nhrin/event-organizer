package org.o7planning.springbootsecurityjpa.dao;

import lombok.RequiredArgsConstructor;
import org.o7planning.springbootsecurityjpa.entity.AppEvent;
import org.o7planning.springbootsecurityjpa.entity.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class EventDAO {


    @Autowired
    private EntityManager entityManager;

    public void save(AppEvent event) {
        entityManager.persist(event);
    }

    public List<AppEvent> findAll() {
        List<AppEvent> events =
                entityManager.createNativeQuery("SELECT * FROM app_event", AppEvent.class)
                        .getResultList();
        return events;
    }

    public AppEvent findById(long id) {
        try {
            AppEvent event = entityManager.find(AppEvent.class, id);

            return event;
        } catch (NoResultException e) {
            return null;
        }
    }
    public void addVisitor (AppEvent event, AppUser user) {
        event.getGoes().add(user);
    }

}
