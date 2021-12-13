package com.getir.readingisgood.entity;

import com.getir.readingisgood.enums.StatusType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Date;

/***
 Created on 2021

 @author emre.gozukucuk
 **/

@Getter
@Setter
@Document(collection = "Customer")
public class CustomerEntity implements UserDetails {

    @Id
    private String customerId;
    private String name;
    private String surname;
    @NotBlank
    @Indexed(unique = true)
    private String email;
    @NotBlank
    private String password;
    private Boolean enabled;
    private StatusType status;
    private Date createDate;
    private Date lastUpdateDate;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
