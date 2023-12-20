package ru.naumen.taskManager.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.naumen.taskManager.models.User;
import ru.naumen.taskManager.services.UserService;
import ru.naumen.taskManager.services.UserServiceImpl;

@Controller
public class AdminController {


    @Autowired
    private UserServiceImpl userServiceImpl;


    @GetMapping("/admin")
    public String userList(Model model) {
        if(getCurrentUser().getRoles().stream().anyMatch(i -> i.getName().equals("admin"))){
            model.addAttribute("allUsers", userServiceImpl.allUsers());
            return "admin";
        }
        return "redirect:/dashboard";
    }

    @PostMapping("/admin")
    public String  deleteUser(@RequestParam(required = true, defaultValue = "" ) Long userId,
                              @RequestParam(required = true, defaultValue = "" ) String action,
                              Model model) {
        if (action.equals("delete")){
            userServiceImpl.deleteUser(userId);
        }
        return "redirect:/admin";
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userServiceImpl.getUserByName(currentPrincipalName);
    }
}
//@role