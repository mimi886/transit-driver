package com.driver.portal.services;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.driver.portal.dto.AuthResponse;
import com.driver.portal.dto.RegisterRequest;
import com.driver.portal.dto.RegisterResponse;
import com.driver.portal.models.Role;
import com.driver.portal.models.Status;
import com.driver.portal.models.User;
import com.driver.portal.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    
    
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    
    public RegisterResponse register(RegisterRequest request) {
        var user=User.builder()
                     .fullname(request.getFullname())
                     .email(request.getEmail().trim())
                     .password(passwordEncoder.encode(request.getPassword()))
                     .role(Role.USER)
                     .status(Status.ACTIVE)
                     .createat(new Date())
                     .phonenumber(request.getPhonenumber())
                     .build();

                     userRepository.save(user);

       return RegisterResponse.builder()
                                .success(true)
                                .build();
    }

    public AuthResponse login(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        
        var user=userRepository.findByEmail(email).orElseThrow();
        //Generate a random 6-digit OTP
        String otp_code =String.format("%06d", new Random().nextInt(900000) + 100000);
        log.info("OTP " + otp_code);

        //Save OTP and set expiry time (5 minutes from now)
        LocalDateTime expiryTime= LocalDateTime.now().plusMinutes(5);

        user.setOtp(otp_code);
        user.setOtpexpiry(expiryTime);
        userRepository.save(user);
        
        return AuthResponse.builder()
                        .success(true)
                        .role(user.getRole())
                        .fullname(user.getFullname())
                        .email(user.getEmail())
                        .build();
    }

    public AuthResponse verify(String email, String otp) {

         var user=userRepository.findByEmail(email).orElseThrow();

         // Check if OTP exists and is not expired
         if (otp != null && LocalDateTime.now().isBefore(user.getOtpexpiry())) {
            // Check if user-entered OTP matches stored OTP
            if(otp.equals(user.getOtp())){
                return AuthResponse.builder()
                        .success(true)
                        .role(user.getRole())
                        .fullname(user.getFullname())
                        .email(user.getEmail())
                        .build();
            }
        }
        return AuthResponse.builder()
                        .success(false)
                        .build();
    }
    
    
}
