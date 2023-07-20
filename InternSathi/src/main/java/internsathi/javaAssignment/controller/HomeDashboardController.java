package internsathi.javaAssignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/internsathi/user")
@Controller
public class HomeDashboardController {

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

}
