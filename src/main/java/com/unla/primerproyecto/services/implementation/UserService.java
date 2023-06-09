package com.unla.primerproyecto.services.implementation;

import com.unla.primerproyecto.entities.User;
import com.unla.primerproyecto.entities.UserRole;
import com.unla.primerproyecto.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userService")
public class UserService implements UserDetailsService {
    @Autowired
    @Qualifier("userRepository")
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.unla.primerproyecto.entities.User user = userRepository.findByUsernameAndFetchUserRolesEagerly(username);
        return buildUser(user, buildGrantedAuthorities(user.getUserRoles()));
    }

    private User buildUser(com.unla.primerproyecto.entities.User user, List<GrantedAuthority> grantedAuthorities) {
        return new User(user.getUsername(), user.getPassword(), user.isEnabled(),
                true, true, true,
                grantedAuthorities);
    }

    private List<GrantedAuthority> buildGrantedAuthorities(Set<UserRole> userRoles) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        for (UserRole userRole: userRoles) {
            grantedAuthorities.add(new SimpleGrantedAuthority((userRole.getRole())));
        }
        return new ArrayList<GrantedAuthority>(grantedAuthorities);
    }
}
