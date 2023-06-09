package com.ihsan.itgecommerce.configuration;


import com.ihsan.itgecommerce.entity.UserEntity;
import com.ihsan.itgecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JwtUserDetails implements UserDetailsService {
    @Autowired
    UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }


    public UserDetails getUserById(Long userid) {


        Optional<UserEntity> personal = userService.findById(userid);
        if (personal.isEmpty()) return null;

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (int i=0; i<personal.get().getRoles().size(); i++){
            authorities.add(new SimpleGrantedAuthority(personal.get().getRoles().stream().toList().get(i).toString()));
        }
        //authorities.add(new SimpleGrantedAuthority(personal.get().getUserType().toString()));

        return User.builder()
                .username(userid.toString())
                .password("")
                .accountLocked(false)
                .accountExpired(false)
                .authorities(authorities)
                .build();
    }
}
