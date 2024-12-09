package com.vyshnav.product.service;

import com.vyshnav.product.entity.User;
import com.vyshnav.product.repository.UserRepository;
import com.vyshnav.product.security.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));//encode the password and store
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);//verify whether username is correct or not
                if(user.isEmpty()) throw new UsernameNotFoundException("User not found");// if username is not correct it will throw an exception
                // if username is same then only then it will save in principle and role will be assigned
                return new UserPrinciple(user.get());
    }
}
