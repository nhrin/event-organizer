package org.nhrin.eventorganizer.controller;

import lombok.RequiredArgsConstructor;
import org.nhrin.eventorganizer.entity.EventEntity;
import org.nhrin.eventorganizer.service.EventService;
import org.nhrin.eventorganizer.utils.WebUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    private final ObjectFactory<HttpSession> httpSessionFactory;

    @PostMapping(value = "/save-event")
    public String saveEvent(EventEntity eventEntity, Model model) {
        model.addAttribute("message", eventEntity.getEventName() + " saved");
        eventService.save(eventEntity);
        return "saveEvent";
    }

    @GetMapping(value = "/accept-event")
    public String acceptEvent(@RequestParam("eventId") Long eventId, Model model) {

        long userId = (long) httpSessionFactory.getObject().getAttribute("userId");
        eventService.acceptEvent(eventId, userId);

        return "acceptSuccessfully";
    }
    @GetMapping(value = "/delete-event")
    public String deleteEvent(@RequestParam("eventId") Long eventId, Model model) {
        eventService.delete(eventId);
        return "deleteSuccessfully";
    }

    @GetMapping(value = "/event-details")
    public String getEventDetails(@RequestParam("eventId") String eventId, Model model) {
        EventEntity event = eventService.findById(Integer.parseInt(eventId));
        model.addAttribute("name", event.getEventName());
        model.addAttribute("place", event.getEventPlace());
        model.addAttribute("date", event.getEventDate());
        model.addAttribute("description", event.getEventDescription());
        return "eventDetails";
    }


    @RequestMapping(value = "/new-event", method = RequestMethod.GET)
    public String createEvent(Model model) {
        model.addAttribute("event", new EventEntity());
        return "newEvent";
    }

    @RequestMapping(value = "/main-event-info", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {

        // After user login successfully.
        String userName = principal.getName();

        System.out.println("User Name: " + userName);

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        List<EventEntity> events = eventService.findAllEvents();
        model.addAttribute("events", events);
        return "mainEventInfoPage";
    }


    @GetMapping(value = "/who-goes")
    public String getVisitors(@RequestParam("eventId") String eventId, Model model) {
        EventEntity event = eventService.findById(Integer.parseInt(eventId));
        model.addAttribute("visitors", event.getGoes());
        return "visitors";
    }


}