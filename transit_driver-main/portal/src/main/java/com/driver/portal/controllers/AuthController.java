package com.driver.portal.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.driver.portal.dto.AuthResponse;
import com.driver.portal.dto.RegisterRequest;
import com.driver.portal.dto.RegisterResponse;
import com.driver.portal.services.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    
    private final AuthService authService;

    @PostMapping("/login")
    public String login(HttpSession session,HttpServletRequest request,@RequestParam String email, @RequestParam String password, Model model){
       try {
         AuthResponse authResponse=authService.login(email,password);

         if(authResponse.getSuccess()){
            model.addAttribute("email", email);
            request.getSession().setAttribute("email", email);
            request.getSession().setAttribute("role", authResponse.getRole().toString());
            request.getSession().setAttribute("fullname", authResponse.getFullname());                
            return "redirect:/dashboard";    
       
        }else{
            model.addAttribute("status","failed");
            return "redirect:/login";
         }

       } catch (Exception e) {
         e.printStackTrace();
          log.info("Authentication Failed");
          model.addAttribute("status","failed");
          return "login";
       }
       
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse register(@RequestBody RegisterRequest request){
        return authService.register(request);
    }

    @PostMapping("/verify")
    public String verify(HttpSession session,HttpServletRequest request,@RequestParam String email, @RequestParam String otp, Model model){
       try {
         AuthResponse authResponse=authService.verify(email,otp);

         if(authResponse.getSuccess()){
            request.getSession().setAttribute("email", email);
            request.getSession().setAttribute("role", authResponse.getRole().toString());
            request.getSession().setAttribute("fullname", authResponse.getFullname());                
            return "redirect:/dashboard";         
        }else{
            model.addAttribute("status","failed");
            return "login";
         }

       } catch (Exception e) {
          log.info("Authentication Failed");
          model.addAttribute("status","failed");
          return "login";
       }
       
    }

   

}
