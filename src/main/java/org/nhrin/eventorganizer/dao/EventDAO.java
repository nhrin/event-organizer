package org.nhrin.eventorganizer.dao;

import lombok.RequiredArgsConstructor;
import org.nhrin.eventorganizer.entity.EventEntity;
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

    public void save(EventEntity event) {
        entityManager.persist(event);
    }

    public List<EventEntity> findAll() {
        List<EventEntity> events =
                entityManager.createNativeQuery("SELECT * FROM app_event", EventEntity.class)
                        .getResultList();
        return events;
    }

    public EventEntity findById(long id) {
        try {
            EventEntity event = entityManager.find(EventEntity.class, id);
            return event;
        } catch (NoResultException e) {
            return null;
        }
    }

    public void deleteById(long id) {
        EventEntity event = entityManager.find(EventEntity.class, id);
        entityManager.remove(event);
    }

}
