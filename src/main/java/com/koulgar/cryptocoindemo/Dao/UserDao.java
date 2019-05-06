package com.koulgar.cryptocoindemo.Dao;

import com.koulgar.cryptocoindemo.Entity.Cryptocoin;
import com.koulgar.cryptocoindemo.Entity.FormUser;
import com.koulgar.cryptocoindemo.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {

    User findByUsername(String username);

    User findById(int id);

    Page<User> findAll(Pageable pageable);

    @Transactional
    @Modifying
    void deleteById(Integer id);

    @Query("Select u from User u where u.firstName like %?1% or u.lastName like %?1% or u.username like %?1% or u.email like %?1%")
    Page<User> findBySearch(String search,Pageable pageable);

}
