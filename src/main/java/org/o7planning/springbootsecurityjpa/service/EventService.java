package org.o7planning.springbootsecurityjpa.service;

import lombok.RequiredArgsConstructor;
import org.o7planning.springbootsecurityjpa.dao.AppUserDAO;
import org.o7planning.springbootsecurityjpa.dao.EventDAO;
import org.o7planning.springbootsecurityjpa.entity.AppEvent;
import org.o7planning.springbootsecurityjpa.entity.AppUser;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EventService {


    private final EventDAO eventDAO;
    private final AppUserDAO appUserDAO;


    public List<AppEvent> findAllEvents() {
        return eventDAO.findAll();
    }

    public AppEvent save(AppEvent event) {
        eventDAO.save(event);
        return event;
    }

    public AppEvent findById(int id) {
        AppEvent event = eventDAO.findById(id);
        return event;
    }

    public AppEvent acceptEvent(long eventId, long userId) {
        AppEvent event = eventDAO.findById(eventId);
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
