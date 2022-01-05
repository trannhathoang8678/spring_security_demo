package security.demo.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import security.demo.repository.RoleRepository;
import security.demo.repository.UserRepository;
import security.demo.repository.entity.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findOneByUsername(username);
        // if user is not exists
        if (user == null) {
            log.warn("Load user by username: User " + username + " was not found");
            throw new UsernameNotFoundException("User " + username + " was not found");
        }
        List<String> roleNames = roleRepository.getRolesByUserId(user.getId());
        //get authorities
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        if (roleNames != null) {
            for (String role : roleNames) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantedAuthorityList.add(authority);
            }
        }
        //construct
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getEncryptedPassword(), grantedAuthorityList);
        return userDetails;
    }
}
