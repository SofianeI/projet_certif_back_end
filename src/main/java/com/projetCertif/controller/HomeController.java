package com.projetCertif.controller;

import com.projetCertif.dao.entity.User;
import com.projetCertif.service.ChannelService;
import com.projetCertif.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Collections;
import java.util.Optional;

@Controller
@SessionAttributes("auth")
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private ChannelService channelService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String home() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        // User authentication through UserService
        Optional<User> authenticatedUser = userService.authenticate(username, password);

        if (authenticatedUser.isPresent()) {
            // If authentication worked : add the object to the session and redirect to the dashboard
            // need improvement (session creation)
            model.addAttribute("auth", new UsernamePasswordAuthenticationToken(username, password));
            model.addAttribute("username", authenticatedUser.get().getFirstname());
            return "redirect:/dashboard";
        } else {
            // If authentication failed : give error message and redirect
            model.addAttribute("error", true);
            return "redirect:/";
        }
    }

    // After authentication -> dashboard
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        // Get username from the session
        Authentication authentication = (Authentication) session.getAttribute("auth");

        if (authentication != null) {
            // If authenticated :  give username information and redirect
            model.addAttribute("username", authentication.getName());
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("channels", channelService.getAllChannels());
            return "dashboard";
        } else {
            return "redirect:/login";
        }
    }
}
