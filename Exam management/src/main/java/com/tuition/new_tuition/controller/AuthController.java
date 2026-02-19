package com.tuition.new_tuition.controller;

import com.tuition.new_tuition.entity.AppUser;
import com.tuition.new_tuition.entity.Role;
import com.tuition.new_tuition.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // OPEN LOGIN PAGE
    @GetMapping({"/", "/login"})
    public String loginPage(@RequestParam(value = "expired", required = false) String expired,
                            Model model) {
        if (expired != null) {
            model.addAttribute("error", "Session expired. Please login again.");
        }
        return "login"; // templates/login.html
    }

    // OPEN REGISTER PAGE
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new AppUser());
        return "register"; // templates/register.html
    }

    //  REGISTER SUBMIT
    @PostMapping("/register")
    public String doRegister(@ModelAttribute("user") AppUser user, Model model) {

        // default role as STUDENT (you can change later)
        if (user.getRole() == null) {
            user.setRole(Role.STUDENT);
        }

        // basic validation
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            model.addAttribute("error", "Email is required.");
            return "register";
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            model.addAttribute("error", "Password is required.");
            return "register";
        }

        try {
            userService.register(user);
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }

        return "redirect:/login?registered";
    }

    // LOGIN SUBMIT
    @PostMapping("/login")
    public String doLogin(@RequestParam String email,
                          @RequestParam String password,
                          HttpSession session,
                          Model model) {

        AppUser user = userService.findByEmail(email);

        if (user == null || !user.getPassword().equals(password)) {
            model.addAttribute("error", "Invalid email or password.");
            return "login";
        }

        // save session
        session.setAttribute("userEmail", user.getEmail());
        session.setAttribute("userRole", user.getRole().name());

        // redirect by role
        if (user.getRole() == Role.TEACHER) {
            return "redirect:/exams/list";
        } else {
            return "redirect:/student/exams";
        }
    }

    // LOGOUT
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
