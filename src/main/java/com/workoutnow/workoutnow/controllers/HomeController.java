package com.workoutnow.workoutnow.controllers;

import com.workoutnow.workoutnow.models.User;
import com.workoutnow.workoutnow.models.UserProfile;
import com.workoutnow.workoutnow.models.data.UserProfileRepository;
import com.workoutnow.workoutnow.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @RequestMapping("")
    public String index(Model model, HttpSession session) {

        int currentUserId = (Integer) session.getAttribute("user");
        User currentUser = userRepository.findById(currentUserId);


        UserProfile userProfileCurrent = userProfileRepository.findByUser(currentUser);
//        if(currentUserProfile.isPresent()) {
//            model.addAttribute("user", currentUserProfile.get());
//        } else {
//            model.addAttribute("user", "");
//        }
        model.addAttribute("user", userProfileCurrent);
        return "index";
    }

}
