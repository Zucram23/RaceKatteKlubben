package presentation;


import application.UserServiceImpl;
import domain.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Controller
@RequestMapping("/RaceKatteKlubben")
public class LoginController {
    private final UserServiceImpl userService;

    public LoginController(UserServiceImpl userService){this.userService =  userService;}

    @GetMapping("/login")
    public String showUserForm(Model model){
        model.addAttribute("user", new User());
        return "login";
    }
    @PostMapping("/login")
    public String authenticateUser(@RequestParam String email, @RequestParam String password, Model model, HttpSession session) {
        Optional<User> optionalUser = userService.authenticateUser(email, password);

        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            session.setAttribute("user", user);
            return "redirect:/RaceKatteKlubben/profile/" + user.getId();
        }
        else{
            model.addAttribute("errorMessage", "Invalid email or password");
            return "login";
        }

    }

    @PostMapping("/register")
    public String saveUser(@Validated @ModelAttribute User user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "register";
        }
        if (userService.emailExist(user.getEmail())) {
            model.addAttribute("errorMessage", "Email already exists!");
            return "register";
        }
        userService.save(user);
        return "redirect:/RaceKatteKlubben/login";  // Redirect to login page after successful registration
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";  // returns register.html form for registration
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Fjerner sessionen
        return "redirect:/RaceKatteKlubben/login"; // Går til login siden
    }
}
