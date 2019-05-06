package com.koulgar.cryptocoindemo.Service;

import com.koulgar.cryptocoindemo.Entity.FormUser;
import com.koulgar.cryptocoindemo.Entity.UpdateUser;
import com.koulgar.cryptocoindemo.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByUsername(String username);

    void save(FormUser formUser);

    void update(UpdateUser updateUser);

    User findById(int id);

    void deleteUserById(Integer id);

    FormUser userToFormUser(User user);

    UpdateUser userToUpdateUser(User user);

    Page<User> findAll(Pageable pageable);

    Page<User> findBySearch(String search, Pageable pageable);

}
