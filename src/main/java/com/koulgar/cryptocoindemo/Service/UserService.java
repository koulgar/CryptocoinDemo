package com.koulgar.cryptocoindemo.Service;

import com.koulgar.cryptocoindemo.Entity.FormUser;
import com.koulgar.cryptocoindemo.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.Principal;

public interface UserService extends UserDetailsService {

    User findByUsername(String username);

    void save(FormUser formUser);

    User findById(int id);

    void deleteUserById(Integer id);

    FormUser userToFormUser(User user);

    Page<User> findAll(Pageable pageable);

    void update(FormUser formUser);

//    void update(FormUser formUser);
}
