package com.woro.assignment.config;

import com.woro.assignment.entity.User;
import com.woro.assignment.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
       private User user;
       @Autowired
        private UserRepository userRepository;
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            var user=userRepository.findUserByUsername(username)
                    .orElseThrow(()->new UsernameNotFoundException("User does not exists"));
            return new CustomUserDetail(user);
        }
}
