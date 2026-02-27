package restaurantVote.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class UIController {
    private static final Logger log = LoggerFactory.getLogger(UIController.class);
//    @GetMapping("/ui/dashboard")
//    public String dashboard(Model model, @AuthenticationPrincipal OAuth2User principal) {
//        if (principal != null) {
//            model.addAttribute("name", principal.getAttribute("name"));
//            model.addAttribute("email", principal.getAttribute("email"));
//            model.addAttribute("username", principal.getAttribute("preferred_username"));
//        }
//        return "dashboard";  // Ищет dashboard.html в /templates
//    }

    @GetMapping("/ui/dashboard")
    public String dashboard(@AuthenticationPrincipal OAuth2User user) {
        log.info("=== МЕТОД dashboard ВЫЗВАН ===");
        return "OK";
    }

    @GetMapping("/ui")
    public String ui(@AuthenticationPrincipal OAuth2User user) {
        log.info("=== МЕТОД ui ВЫЗВАН ===");
        return "OK";
    }

    @GetMapping("/my-special-dashboard")
    public String specialDashboard() {
        log.info("=== МЕТОД specialDashboard ВЫЗВАН ===");
        return "SPECIAL DASHBOARD WORKS!";
    }
//
//    @GetMapping("/ui")
//    public String uiRedirect() {
//        return "redirect:/ui/dashboard";  // Редирект с /ui на /ui/dashboard
//    }
//
//    @GetMapping("/")
//    public String home(@AuthenticationPrincipal OAuth2User principal) {
//        if (principal != null) {
//            return "redirect:/ui/dashboard";
//        }
//        return "redirect:/oauth2/authorization/keycloak";
//    }
}