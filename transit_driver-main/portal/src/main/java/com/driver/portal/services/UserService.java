package com.driver.portal.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.driver.portal.dto.UsersList;
import com.driver.portal.models.Role;
import com.driver.portal.models.User;
import com.driver.portal.repository.UserRepository;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
//@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public List<UsersList> allUsers() {
        List<User> users= userRepository.findAll();
        return users.stream().map(this::mapToUserList).toList();
    }

    private UsersList mapToUserList(User user){
        return UsersList.builder()
                        .id(user.getId())
                        .fullname(user.getFullname())
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .role(user.getRole())
                        .status(user.getStatus())
                        .phonenumber(user.getPhonenumber())
                        .createat(user.getCreateat())
                        .build();
    }

	public Map<String, Object> newUser(String fullname, String email, String role, String created_by, String phone) {
		Map<String, Object> map = new HashMap<>();
        var user = userRepository.findByEmail(created_by).orElseThrow();

        User newUser = new User();
        newUser.setFullname(fullname);
        newUser.setEmail(email);
        newUser.setPhonenumber(phone);
        newUser.setCreatedby(user.getId());
        if(!StringUtils.isBlank(role)){
            if(role.equals("ADMIN")){
                newUser.setRole(Role.ADMIN);
            }else{
                newUser.setRole(Role.USER);
            }
        }else{
            newUser.setRole(Role.USER);
        }
        newUser.setCreateat(new Date());  
        
        userRepository.save(newUser);
        map.put("success",true); 
        return map;
	}
    
}
