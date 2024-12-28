package com.fst.info.Eventure_App.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



import jakarta.servlet.http.HttpSession;
import com.fst.info.Eventure_App.models.User;
import com.fst.info.Eventure_App.services.UserService;



//!!!!!!!!! This is the controller for the user page !!!!!!!!!!
@Controller
public class UserController {
    
    @Autowired
    private UserService service;

@GetMapping("/")
public String homepage() {
    return "index";
}

//------------------------------------------------------------------//

@GetMapping("/users")
public String showAllUsers(Model model) {
    model.addAttribute("users", service.getAllUsers());
    return "Users";
}

@PostMapping("/users/delete")
public String deleteUser(@RequestParam Long id) {
    service.deleteUser(id);
    return "redirect:/users";
}



//------------------------------------------------------------------//
//------LOGIN--------------------------------------------------------//


@GetMapping("/login")
public String showAuthentification(){
    return "login";
}


@GetMapping("/AdminPage")
public String showAdminPage(){
    return "AdminPage";
}

// Gestion de la connexion
@PostMapping("/login")
public String loginUser(
    @RequestParam String email,
    @RequestParam String password,
    HttpSession session,
    Model model) {
    Optional<User> user = service.authenticate(email, password);
    if (user.isPresent()) {
    session.setAttribute("user", user.get());
    if (user.get().getRole() == 0) {
        return "redirect:/AdminPage"; 
    }
    return "redirect:/profile"; 
    } else {
    model.addAttribute("error", "Invalid email or password");
    return "index";
    }
}

@GetMapping("/profile")
public String showProfilePage(HttpSession session, Model model) {
    User user = (User) session.getAttribute("user");
    if (user == null) {
    return "redirect:/"; 
    }
    model.addAttribute("user", user);
    return "profile"; 
}
    

    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
        session.invalidate();
        return "redirect:/"; 
    }
//------------------------------------------------------------------//
//----------------------------register----------------------------------//
@GetMapping("/register")
public String showRegistration(){
    return "register";
}


    @PostMapping("/register")
    public User saveUser(User user) {
        return service.saveUser(user);
    }

}
