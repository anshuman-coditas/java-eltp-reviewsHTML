package com.example.demo.service;

import com.example.demo.configuration.Encoder;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    private User user;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        User u=new User();
        u.setName(user.getName());
        u.setEmail(user.getEmail());
        u.setPassword(Encoder.passwordEncoder().encode(user.getPassword()));
        if(user.getEmail().split("@")[1].equals("ak.in"))
            u.setRoles(Arrays.asList(new Role("ADMIN"),new Role("USER")));
        else
            u.setRoles(Arrays.asList(new Role("USER")));

        return userRepository.save(u);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User u= userRepository.findByEmail(email);
        if(u==null)
            throw new UsernameNotFoundException("User not found");
        return new org.springframework.security.core.userdetails.User(u.getEmail(),
                u.getPassword(),
                mapRolesToAuthorities(u.getRoles()));
    }
    private Collection < ? extends GrantedAuthority> mapRolesToAuthorities(Collection< Role > roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());
    }

    public boolean hasRole(String roleName) {
        return this.user.hasRole(roleName);
    }

}
