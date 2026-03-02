package restaurantVote.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class UIController {
    private static final Logger log = LoggerFactory.getLogger(UIController.class);

    @GetMapping("/profile")
    public String profilePage(@AuthenticationPrincipal OidcUser user, Model model) {

        if (user == null) {
            return "redirect:/oauth2/authorization/keycloak";
        }

        log.info("token = " + user.getIdToken());
        // Передаем все данные через Model
        model.addAttribute("username", user.getPreferredUsername());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("fullName", user.getFullName());
        model.addAttribute("firstName", user.getGivenName());
        model.addAttribute("lastName", user.getFamilyName());

        // email_verified как boolean
        Boolean emailVerified = user.getClaimAsBoolean("email_verified");
        model.addAttribute("emailVerified", emailVerified != null ? emailVerified : false);

        // Роли
        Map<String, Object> realmAccess = user.getClaimAsMap("realm_access");
        List<String> roles = new ArrayList<>();
        if (realmAccess != null && realmAccess.containsKey("roles")) {
            roles = (List<String>) realmAccess.get("roles");
        }
        model.addAttribute("roles", roles);

        return "profile";
    }
}