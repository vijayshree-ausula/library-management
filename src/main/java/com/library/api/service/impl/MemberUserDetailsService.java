package com.library.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.library.api.dao.User;
import com.library.api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor
public class MemberUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUname(username);
		
		if (user == null) {
            throw new UsernameNotFoundException("Username " + username + " not found");
        }
		
		return new MembersUserDetails(user);
//	    return userRepository.findByUname(username)
//	        .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
	  }
	
	   public User createUser(UserDetails user) { 
		      User userResponse = userRepository.save((User) user);
		      return userResponse;
		   }
}
