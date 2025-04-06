package presentation;


import application.CatServiceImpl;
import application.UserServiceImpl;
import domain.Cat;
import domain.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/RaceKatteKlubben/profile")
public class ProfileController {

    private final UserServiceImpl userService;
    private final CatServiceImpl catServiceImpl;

    public ProfileController(UserServiceImpl userService, CatServiceImpl catServiceImpl) {
        this.userService = userService;
        this.catServiceImpl = catServiceImpl;
    }

    // Show user profile
    @GetMapping("/{id}")
    public String showUserProfile(@PathVariable int id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getId() != id) {
            return "redirect:/login";
        }

        // Retrieve the user's cats
        List<Cat> cats = catServiceImpl.findCatsByOwner(user.getId());
        user.setCats(cats);  // Assuming user has a 'setCats' method to add cats

        model.addAttribute("user", user);
        return "profile";
    }

    // Edit profile page
    @GetMapping("/{id}/edit")
    public String showEditUserProfile(@PathVariable int id, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");

        if (user == null || user.getId() != id) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "edit-profile";
    }

    // Update user profile
    @PostMapping("/{id}/update")
    public String updateUserProfile(@PathVariable int id, @ModelAttribute User updatedUser, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user == null || user.getId() != id) {
            return "redirect:/login";
        }

        userService.update(updatedUser);

        session.setAttribute("user", updatedUser);
        return "redirect:/RaceKatteKlubben/profile/" + updatedUser.getId();
    }


    @GetMapping("")
    public String redirectToProfile(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        return "redirect:/RaceKatteKlubben/profile/" + user.getId();
    }

    // Add a new cat
    @PostMapping("/{id}/add-cat")
    public String addCat(@PathVariable int id, @ModelAttribute Cat cat, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getId() != id) {
            return "redirect:/login";
        }

        cat.setOwner(user);
        catServiceImpl.save(cat);

        return "redirect:/RaceKatteKlubben/profile/" + user.getId();
    }
}