package internsathi.javaAssignment.controller;

import internsathi.javaAssignment.dto.UserRegistrationDto;
import internsathi.javaAssignment.dto.UserRegistrationResponseDto;
import internsathi.javaAssignment.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/internsathi/admin/")
public class AdminRegistrationController {

    private final UserService userService;

    public AdminRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/registerAdmin")
    public String registerAdmin(Model model, @ModelAttribute UserRegistrationDto userRegistrationDetail) {
        try {
            userRegistrationDetail.setRole("ADMIN, USER");
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
