package internsathi.javaAssignment.controller;

import internsathi.javaAssignment.dto.UserRegistrationDto;
import internsathi.javaAssignment.dto.UserRegistrationResponseDto;
import internsathi.javaAssignment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    /*@PostMapping("/registerUser")
    public ResponseEntity<UserRegistrationResponseDto> registerUser(@RequestBody UserRegistrationDto userRegistrationDetail) {
        try {
            return new ResponseEntity<>(userService.registerUser(userRegistrationDetail), HttpStatus.CREATED);
        } catch (Exception e) {
            UserRegistrationResponseDto userRegistrationFailedResponse = UserRegistrationResponseDto.builder()
                    .message("User registration Failed")
                    .status(HttpStatus.BAD_REQUEST)
                    .username(userRegistrationDetail.getUsername())
                    .build();
            return new ResponseEntity<>(userRegistrationFailedResponse, HttpStatus.BAD_REQUEST);
        }
    }*/
}
