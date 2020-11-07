package org.api.gateway.security;

import java.util.List;
import java.util.stream.Collectors;

import org.api.gateway.feign.AdminInterface;
import org.api.gateway.model.AdminResponseDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailService implements UserDetailsService {

	private AdminInterface adminInterface;

    public UserDetailService(AdminInterface customerInterface) {
        this.adminInterface = customerInterface;
    }
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		AdminResponseDTO custResponseDTO = adminInterface.getUser(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username:" + username + " not found"));

        List<GrantedAuthority> grantedAuthorities = custResponseDTO.getRoles()
                .stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new User(String.join("-", username, custResponseDTO.getEmailAddress()),
                custResponseDTO.getPassword(), true, true, true,
                true, grantedAuthorities);
	}

}
