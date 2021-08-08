package org.nhrin.eventorganizer.service;

import lombok.RequiredArgsConstructor;
import org.nhrin.eventorganizer.dao.EventDAO;
import org.nhrin.eventorganizer.entity.AppUser;
import org.nhrin.eventorganizer.dao.AppUserDAO;
import org.nhrin.eventorganizer.entity.EventEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EventService {


    private final EventDAO eventDAO;
    private final AppUserDAO appUserDAO;


    public List<EventEntity> findAllEvents() {
        return eventDAO.findAll();
    }
    public Set<EventEntity> findByUserId(Long userId) {
        return appUserDAO.findEvents(userId);
    }

    public EventEntity save(EventEntity event) {
        eventDAO.save(event);
        return event;
    }
    public void delete(Long id) {
        eventDAO.deleteById(id);
        }

    public EventEntity findById(long id) {
        EventEntity event = eventDAO.findById(id);
        return event;
    }



    public EventEntity acceptEvent(long eventId, long userId) {
        EventEntity event = eventDAO.findById(eventId);
        AppUser userAccount = appUserDAO.findUserAccount(userId);
        Set<AppUser> goes = event.getGoes();
        if (goes == null) {
            goes = new HashSet<>();
        }
        goes.add(userAccount);
        event.setGoes(goes);
        eventDAO.save(event);
        return event;
    }
}
