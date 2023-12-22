package com.projetCertif.controller;

import com.projetCertif.dao.entity.User;
import com.projetCertif.service.ChannelService;
import com.projetCertif.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import java.util.Optional;

@Controller
@SessionAttributes("auth")
@CrossOrigin(origins = "http://localhost:4200")
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private ChannelService channelService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private void handleLoginError(Model model) {
        model.addAttribute("loginError", true);
        model.addAttribute("loginErrorMessage", "An error occurred during login");
    }

    private void handleRegistrationError(Model model, String errorMessage) {
        model.addAttribute("registrationError", true);
        model.addAttribute("registrationErrorMessage", errorMessage);
    }

    private String redirectToLoginWithError(Model model, String errorMessage) {
        model.addAttribute("error", true);
        model.addAttribute("errorMessage", errorMessage);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        // Clear previous error messages when displaying the login form
        model.addAttribute("error", false);
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        try {
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
                handleLoginError(model);
                return "login";
            }

        }catch (Exception e) {
            handleLoginError(model);
            return "login";
        }

    }

    @PostMapping("/register")
    public String registerNewUser(
            @RequestParam String newUsername, @RequestParam String newPassword,
            @RequestParam String confirmPassword, @RequestParam String newFirstname,
            @RequestParam String newLastname, Model model) {
        try {
            if (newPassword.equals(confirmPassword)) {
                String hashedPassword = passwordEncoder.encode(newPassword);
                userService.registerUser(newUsername, newFirstname, newLastname, hashedPassword);
                return "redirect:/login";
            } else {
                // Passwords do not match
                handleRegistrationError(model, "Passwords do not match.");
                return "login";
            }
        } catch (Exception e) {
            handleRegistrationError(model, "An error occurred during registration.");
            return "login";
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
