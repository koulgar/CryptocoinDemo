package com.koulgar.cryptocoindemo.Service;

import com.koulgar.cryptocoindemo.Entity.FormUser;
import com.koulgar.cryptocoindemo.Entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.Principal;

public interface UserService extends UserDetailsService {

    User findByUsername(String username);

    void save(FormUser formUser);

    User findById(int id);

    void deleteUserById(Integer id);

    FormUser userToFormUser(User user);

//    void update(FormUser formUser);
}
