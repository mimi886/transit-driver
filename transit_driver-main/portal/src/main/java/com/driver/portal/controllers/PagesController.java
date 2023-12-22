package com.driver.portal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;




import jakarta.servlet.http.HttpSession;

@Controller
public class PagesController {

    @Autowired
    HttpSession session;

     

    @GetMapping("/")
    public String login(){
        return "login";
    }

    @GetMapping("/login")
    public String loginpage(){
        return "login";
    }


    @GetMapping("/dashboard")
    public String dashboard(Model model){
        if(!isUserVerified()){
            return "login";
        }
        model.addAttribute("pagename", "dashboard");
        return "dashboard";
    }

    @GetMapping("/requests")
    public String customers(Model model){
        if(!isUserVerified()){
            return "login";
        }
         model.addAttribute("pagename", "requests");
        return "requests";
    }

    @GetMapping("/users")
    public String users(Model model){
        if(!isUserVerified()){
            return "login";
        }
        model.addAttribute("pagename", "users");
        return "users";
    }

     
    private Boolean isUserVerified(){
        Boolean status=false;
        try{

            String email=(String) session.getAttribute("email");
          
            if(!(email == null || email.length() == 0)){
                status=true;
            }else{
                status=false;
            }
        }catch(Exception e){
           
            return false;
        }
        return status;
    }

   
    
}
