package presentation;


import application.EventServiceImpl;
import domain.Event;
import domain.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/RaceKatteKlubben/Events")
public class EventController {

    private final EventServiceImpl eventService;
    public EventController(EventServiceImpl eventService) {this.eventService = eventService; }


    @GetMapping
    public String showEvents(Model model, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        if(user == null){
            return "redirect:/RaceKatteKlubben/login";
        }


        List<Event> events = eventService.findAll();
        System.out.println(events);
        model.addAttribute("events", events);
        model.addAttribute("user", user);
        return "events";
    }

    @GetMapping("/{id}")
    public String showEventDetails(@PathVariable int id, Model model, HttpSession httpSession){

        User user = (User) httpSession.getAttribute("user");
        if(user == null){
            return "redirect:/RaceKatteKlubben/login";
        }
        Event event = eventService.getEventById(id);
        if(event == null){
            return "redirect:/RaceKatteKlubben/Events";
        }
        model.addAttribute("event", event);
        return "event-details";

    }

    @GetMapping("/create")
    public String showCreateEventForm(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/RaceKatteKlubben/login"; // Redirect if not logged in
        }

        model.addAttribute("event", new Event()); // Empty event object for the form
        return "create-event"; // Loads create-event.html
    }

    @PostMapping("/create")
    public String createEvent(Event event, HttpSession httpSession) {

        User user = (User) httpSession.getAttribute("user");
        if (user == null) {
            return "redirect:/RaceKatteKlubben/login";
        }
        event.setAdmin_id(user.getId());
        eventService.save(event);

        return "redirect:/RaceKatteKlubben/Events";
    }









}
