package com.koulgar.cryptocoindemo.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import com.koulgar.cryptocoindemo.Dao.UserDao;
import com.koulgar.cryptocoindemo.Entity.FormUser;
import com.koulgar.cryptocoindemo.Entity.Role;
import com.koulgar.cryptocoindemo.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public void save(FormUser formUser) {
        User user = new User();
        user.setFirstName(formUser.getFirstName());
        user.setLastName(formUser.getLastName());
        user.setEmail(formUser.getEmail());
        user.setPassword(passwordEncoder.encode(formUser.getPassword()));
        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
        userDao.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection <?extends GrantedAuthority >
    mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}