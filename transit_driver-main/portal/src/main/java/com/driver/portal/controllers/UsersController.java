package com.driver.portal.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.driver.portal.dto.UsersList;
import com.driver.portal.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UserService userService;
    
    @PostMapping("all-users")
    public List<UsersList> usersController(@RequestParam Map<String,Object> requestObj){
       return userService.allUsers();
    }

    
    @PostMapping("new-user")
    public Map<String, Object> newUserController(HttpSession session, HttpServletRequest request,@RequestParam Map<String,Object> requestObj){
        String fullname =(String) requestObj.get("fullname");
        String email=(String) requestObj.get("email");
        String role=(String) requestObj.get("role");
        String created_by=(String) session.getAttribute("email");
        String phone=(String) requestObj.get("phone");

        return userService.newUser(fullname,email,role,created_by,phone);
   }

    // @PostMapping("update-user")
    // public String updateUserController(HttpSession session, HttpServletRequest request,@RequestParam Map<String,Object> requestObj) throws JSONException{
    // //     String fullname =(String) requestObj.get("fullname");
    // //     String email=(String) requestObj.get("email");
    // //     String role=(String) requestObj.get("role");
    // //     String modified_by=(String) session.getAttribute("fullname");
    // //     String phone=(String) requestObj.get("phone");
    // //     String id=(String) requestObj.get("id");
    // //     String branch=(String) requestObj.get("branch");
    // //     jsonObj.put("fullname",fullname);
    // //     jsonObj.put("email",email);
    // //     jsonObj.put("role",role);
    // //     jsonObj.put("id",id);
    // //     jsonObj.put("phone",phone);
    // //     jsonObj.put("modified_by",modified_by);
    // //     jsonObj.put("branch",branch);

    // //     String results=userService.updateUser(jsonObj.toString());

    // //    // Map<String,String> map= new HashMap<>();
    // //    // map.put("success", "true");
    // //     return results;
    // }
    
    //    @PostMapping("deactivate-user")
    // public Map<String,Boolean> deactivateUserController(HttpSession session, HttpServletRequest request,@RequestParam Map<String,Object> requestObj) throws JSONException{
        
    //     // Map<String,Boolean> map= new HashMap<>();
    //     // String id =(String) requestObj.get("id");
    //     // jsonObj.put("id",id);

    //     // userService.deactivateUser(jsonObj.toString());

    //     // map.put("success", true);
    //     // return map;
    // }
    
    // @PostMapping("activate-user")
    // public Map<String,Boolean> activateUserController(HttpSession session, HttpServletRequest request,@RequestParam Map<String,Object> requestObj) throws JSONException{
        
    //     // Map<String,Boolean> map= new HashMap<>();
    //     // String id =(String) requestObj.get("id");
    //     // jsonObj.put("id",id);

    //     // userService.activateUser(jsonObj.toString());

    //     // map.put("success", true);
    //     // return map;
    // }

    // @GetMapping("user_profile")
    // public String userProfileController(HttpSession session, HttpServletRequest request,@RequestParam Map<String,Object> requestObj) throws JSONException{
    //     //   return userService.userProfile();
    // }

    //  @PostMapping("password_change")
    // public String passwordChangeController(HttpSession session, HttpServletRequest request,@RequestParam Map<String,Object> requestObj) throws JSONException{
    //     // String password =(String) requestObj.get("password");
    //     // String newpassword=(String) requestObj.get("newpassword");
    //     // String email=(String) session.getAttribute("email");
    //     // jsonObj.put("password",password);
    //     // jsonObj.put("newpassword",newpassword);
    //     // jsonObj.put("email",email);
      
    //     // String results=userService.changePassword(jsonObj.toString());

    //     // return results;
    // }
}
