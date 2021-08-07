package org.o7planning.springbootsecurityjpa.service;

import lombok.RequiredArgsConstructor;
import org.o7planning.springbootsecurityjpa.dao.EventDAO;
import org.o7planning.springbootsecurityjpa.entity.AppEvent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {


    private final EventDAO eventDAO;

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
}
