package internsathi.javaAssignment.controller;

import internsathi.javaAssignment.dto.UserRegistrationDto;
import internsathi.javaAssignment.dto.UserRegistrationResponseDto;
import internsathi.javaAssignment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/internsathi/user")
@RequiredArgsConstructor
public class RegisterUserController {

    private final UserService userService;

    @GetMapping("/registerUser")
    public String registerPage(Model model,
                               @RequestParam(value = "error", defaultValue = "false") boolean isRegistrationError,
                               @RequestParam(value = "isRegistrationSuccessful", defaultValue = "false") boolean isRegistrationSuccessful){
        model.addAttribute("registerUser", new UserRegistrationDto());
        if (isRegistrationError) {
            model.addAttribute("isRegistrationError", true);
        }
        if (isRegistrationSuccessful) {
            model.addAttribute("isRegistrationSuccessful", true);
        }
        return "register";
    }

    @PostMapping(value = "/registerUser")
    public String registerUser(Model model, @ModelAttribute UserRegistrationDto userRegistrationDetail) {
        try {
            userRegistrationDetail.setRole("USER");
            UserRegistrationResponseDto userRegistrationResponseDto = userService.registerUser(userRegistrationDetail);
            if (userRegistrationResponseDto.getStatus().equals(HttpStatus.CREATED)) {
                model.addAttribute("isRegistrationSuccessful", true);
                return "redirect:/internsathi/user/registerUser?isRegistrationSuccessful=true";
            }
            return "redirect:/internsathi/user/registerUser?isRegistrationError=true";
        } catch (Exception e) {
            return "redirect:/internsathi/user/registerUser?isRegistrationError=true";
        }
    }
}
