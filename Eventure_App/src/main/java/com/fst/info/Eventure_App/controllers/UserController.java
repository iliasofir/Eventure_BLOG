package com.fst.info.Eventure_App.controllers;

import java.util.Optional;
import java.util.stream.Collectors;
import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.http.ResponseEntity;

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

    @GetMapping("/users")
    public String showAllUsers(Model model) {
        model.addAttribute("users", service.getAllUsers().stream()
            .filter(user -> user.getRole() != 0)
            .collect(Collectors.toList()));
        return "Users";
    }

    @PostMapping("/users/update/{id}")
    public String updateUser(@RequestParam Long id, @RequestParam String name, @RequestParam String email) {
        Optional<User> userOptional = service.getUserById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(name);
            user.setEmail(email);
            service.saveUser(user);
        }
        return "redirect:/users";
    }

    @PostMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return "redirect:/users";
    }

    @PostMapping("/users/toggleRole")
    public String toggleUserRole(@RequestParam Long id, @RequestParam int role) {
        Optional<User> userOptional = service.getUserById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setRole(role);
            service.saveUser(user);
        }
        return "redirect:/users";
    }

    @GetMapping("/login")
    public String showAuthentification(){
        return "login";
    }

    @GetMapping("/AdminPage")
    public String showAdminPage(){
        return "AdminPage";
    }

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
            } else if(user.get().getRole() == 2){
                return "redirect:/organiser"; 
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
      if(session != null){
        session.invalidate();
      }
        return "redirect:/";
 }

    @GetMapping("/register")
    public String showRegistration(){
        return "register";
    }

    @PostMapping("/register")
    public User saveUser(User user) {
        return service.saveUser(user);
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("profileImage") MultipartFile file, HttpSession session) {
        if (!file.isEmpty()) {
            try {
                String uploadDir = new File("src/main/resources/static/images/").getAbsolutePath();
                File uploadDirFile = new File(uploadDir);
                if (!uploadDirFile.exists()) {
                    uploadDirFile.mkdirs();
                }
                String filePath = uploadDir + File.separator + file.getOriginalFilename();
                file.transferTo(new File(filePath));

                User user = (User) session.getAttribute("user");
                if (user != null) {
                    user.setImage("/images/" + file.getOriginalFilename());
                    service.saveUser(user);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/profile";
    }
}
