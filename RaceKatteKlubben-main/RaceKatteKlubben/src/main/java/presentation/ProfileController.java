package presentation;

import application.UserService;
import domain.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/RaceKatteKlubben/profile")
public class ProfileController {

private final UserService userService;

public ProfileController(UserService userService) {
    this.userService = userService;
}


    @GetMapping("/{id}")
    public String showUserProfile(@PathVariable int id, Model model, HttpSession session){

        // Get the current user from the session
        User user = (User) session.getAttribute("user");

        if (user == null || user.getId() != id) {
            return "redirect:/RaceKatteKlubben/login"; // Redirect to login/signup if session is missing or user is incorrect
        }

        model.addAttribute("user", user);
        return "profile"; // Returnerer profil.html under templates
    }

    @GetMapping("/{id}/edit")
    public String showEditUserProfile(@PathVariable int id, Model model, HttpSession session){
    User user = (User) session.getAttribute("user");

    if (user == null || user.getId() != id) {
        return "redirect:/RaceKatteKlubben/login";
    }
    model.addAttribute("user", user);
    return "edit-profile";
    }

    @PostMapping("/{id}/update")
    public String updateUserProfile(@PathVariable int id, @ModelAttribute User updatedUser, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user == null || user.getId() != id) {
            return "redirect:/RaceKatteKlubben/login";
        }

        userService.updateUser(id, updatedUser);

        session.setAttribute("user", updatedUser);
        return "redirect:/RaceKatteKlubben/profile/" + updatedUser.getId();


    }


}
