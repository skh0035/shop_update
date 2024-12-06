package com.example.shop.Controller;

import com.example.shop.Configuration.UserDetailsConfiguration;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class GlobalController {
    @ModelAttribute
    public void addUserIdToModel(@AuthenticationPrincipal UserDetailsConfiguration userDetails, Model model) {
        if (userDetails != null) {
            model.addAttribute("userId", userDetails.getUserId());
        } else {
            model.addAttribute("userId", "guest"); // Or handle it in another way
        }
    }
}
