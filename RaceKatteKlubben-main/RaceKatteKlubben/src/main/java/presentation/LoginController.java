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

        return "redirect:/RaceKatteKlubben/profile/" + user.getId();
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user, Model model) {

        if (userService.doesEmailExist(user.getEmail())) {
            model.addAttribute("errorMessage", "Email already exists!");
            return "register";
        }


        userService.createUser(user);
        return "redirect:/RaceKatteKlubben/login";  // Redirect to login page after successful registration
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";  // returns register.html form for registration
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Destroys the session
        return "redirect:/RaceKatteKlubben/login";
    }









}
