package com.driver.portal.models;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User implements UserDetails{

    @Id
    @GeneratedValue
    private Integer id;
    private String fullname;
    @Column(unique = true)
    private String email;
    private String password;
    private Role role;
    private Status status;
    private String phonenumber;
    private Integer createdby;
    private String otp;
    private LocalDateTime otpexpiry;
    private String resetotp;
    private LocalDateTime resetoptexpiry;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable=false)
    private Date createat;


    public User(String fullname, String email, String password, Role role, Status status, String phonenumber,
            Integer createdby, String otp, LocalDateTime otpexpiry, String resetotp, LocalDateTime resetoptexpiry,
            Date createat) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
        this.phonenumber = phonenumber;
        this.createdby = createdby;
        this.otp = otp;
        this.otpexpiry = otpexpiry;
        this.resetotp = resetotp;
        this.resetoptexpiry = resetoptexpiry;
        this.createat = createat;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return email;
    }
    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }


    
    
}
