package com.koulgar.cryptocoindemo.Dao;

import com.koulgar.cryptocoindemo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {

    User findByUsername(String username);

    User findById(int id);

    @Transactional
    @Modifying
    void deleteById(Integer id);
}
