package presentation;

import application.UserService;
import domain.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/RaceKatteKlubben")
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService){this.userService =  userService;}

    @GetMapping("/login")
    public String showUserForm(Model model){
        model.addAttribute("user", new User());
        return "login";
    }
    @PostMapping("/login")
    public String authenticateUser(@RequestParam String email, @RequestParam String password, Model model, HttpSession session) {
        User user = userService.authenticateUser(email, password); // Validate credentials

        if (user == null) {
            model.addAttribute("errorMessage", "Invalid email or password!"); // Error message if login fails
            return "login"; // Stay on the same page if authentication fails
        }

        // Store the logged-in user in session
        session.setAttribute("user", user);

        return "redirect:/profile/" + user.getId();
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user){
        userService.createUser(user);
        return "redirect:/user/" + user.getId();
    }

    @GetMapping("/{id}")
    public String showUserProfile(@PathVariable int id, Model model, HttpSession session){


        int userId;
        try {
            userId = id;
        } catch (NumberFormatException e) {
            // If invalid ID, redirect to login page
            return "redirect:/RaceKatteKlubben/login";
        }

        // Get the current user from the session
        User user = (User) session.getAttribute("user");

        if (user == null || user.getId() != userId) {
            return "redirect:/RaceKatteKlubben/login"; // Redirect to login/signup if session is missing or user is incorrect
        }

        model.addAttribute("user", user);
        return "profile"; // Returnerer profil.html under templates


//        User user = userService.getUserById(id);
//        model.addAttribute("user", user);
//        return "profile";
    }







}
