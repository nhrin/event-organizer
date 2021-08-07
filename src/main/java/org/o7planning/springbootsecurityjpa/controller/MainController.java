package org.o7planning.springbootsecurityjpa.controller;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.o7planning.springbootsecurityjpa.entity.AppEvent;
import org.o7planning.springbootsecurityjpa.service.EventService;
import org.o7planning.springbootsecurityjpa.utils.WebUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final EventService eventService;

    @PostMapping(value = "/save-event")
    public String saveEvent(AppEvent appEvent, Model model) {
        model.addAttribute("message", appEvent.getEventName() + " saved");
        eventService.save(appEvent);
        return "saveEvent";
    }

    @GetMapping(value = "/event-details")
    public String getEventDetails(@RequestParam("eventId") String eventId, Model model) {
        AppEvent event = eventService.findById(Integer.parseInt(eventId));
        model.addAttribute("name", event.getEventName());
        model.addAttribute("place", event.getEventPlace());
        model.addAttribute("date", event.getEventDate());
        model.addAttribute("description", event.getEventDescription());
        return "eventDetails";
    }

    @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "adminPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {

        return "loginPage";
    }
    @RequestMapping(value = "/new-event", method = RequestMethod.GET)
    public String createEvent(Model model) {
        model.addAttribute("event", new AppEvent());
        return "newEvent";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {

        // After user login successfully.
        String userName = principal.getName();

        System.out.println("User Name: " + userName);

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        List<AppEvent> events = eventService.findAllEvents();
        model.addAttribute("events", events);
        return "userInfoPage";
    }


    @PostMapping(value = "/registration")
    public String addVisitor(Model model, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();

        return "registration";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }

        return "403Page";
    }

}