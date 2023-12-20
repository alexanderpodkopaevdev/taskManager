package ru.naumen.taskManager.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.naumen.taskManager.services.UserService;
import ru.naumen.taskManager.services.UserServiceImpl;

@Controller
public class AdminController {

    @Autowired
    private UserServiceImpl userServiceImpl;


    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userServiceImpl.allUsers());
        return "admin";
    }
/*
    @PostMapping("/admin")
    public String  deleteUser(@RequestParam(required = true, defaultValue = "" ) Long userId,
                              @RequestParam(required = true, defaultValue = "" ) String action,
                              Model model) {
        if (action.equals("delete")){
            userServiceImpl.deleteUser(userId);
        }
        return "redirect:/admin";
    }
*/
}
//@role