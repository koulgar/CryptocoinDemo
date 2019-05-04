package com.koulgar.cryptocoindemo.Service;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import com.koulgar.cryptocoindemo.Dao.RoleDao;
import com.koulgar.cryptocoindemo.Dao.UserDao;
import com.koulgar.cryptocoindemo.Entity.FormUser;
import com.koulgar.cryptocoindemo.Entity.Role;
import com.koulgar.cryptocoindemo.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public void save(FormUser formUser) {
        User user;
        if(userDao.findByUsername(formUser.getUsername())!=null) {
            user = userDao.findByUsername(formUser.getUsername());
        } else {
            user = new User();
        }
        user.setId(formUser.getId());
        user.setFirstName(formUser.getFirstName());
        user.setLastName(formUser.getLastName());
        user.setEmail(formUser.getEmail());
        user.setUsername(formUser.getUsername());
        user.setPassword(passwordEncoder.encode(formUser.getPassword()));
        if(user.getRoles()==null) {
            user.setRoles(Arrays.asList(roleDao.findByName("USER")));
        }
        userDao.save(user);
    }

    @Override
    public void update(FormUser formUser) {
        User user = userDao.findById(formUser.getId());
        user.setFirstName(formUser.getFirstName());
        user.setLastName(formUser.getLastName());
        user.setEmail(formUser.getEmail());
        if(user.getRoles()==null) {
            user.setRoles(Arrays.asList(roleDao.findByName("USER")));
        }
        userDao.save(user);
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public void deleteUserById(Integer id) {
        userDao.deleteById(id);
    }

    @Override
    public FormUser userToFormUser(User user) {
        FormUser formUser = new FormUser();
        formUser.setId(user.getId());
        formUser.setUsername(user.getUsername());
        formUser.setEmail(user.getEmail());
        formUser.setPassword(user.getPassword());
        formUser.setMatchingPassword(user.getPassword());
        formUser.setFirstName(user.getFirstName());
        formUser.setLastName(user.getLastName());
        return formUser;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userDao.findAll(pageable);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
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