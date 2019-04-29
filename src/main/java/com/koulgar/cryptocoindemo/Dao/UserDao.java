package com.koulgar.cryptocoindemo.Dao;

import com.koulgar.cryptocoindemo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {

    User findByUsername(String username);
}
