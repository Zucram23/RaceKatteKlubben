package presentation;

import domain.InvalidCredentialsException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(InvalidCredentialsException.class)
    public String handleInvalidCredentialsException(InvalidCredentialsException e, Model model){
        model.addAttribute("errorMessage", e.getMessage());
        return "login";
    }
//    @ModelAttribute
//    public void addErrorMessageToModel(Model model) {
//        if (!model.containsAttribute("errorMessage")) {
//            model.addAttribute("errorMessage", null); // Clear error message if no exception has occurred
//        }
//    }
}
