package com.koulgar.cryptocoindemo.Dao;

import com.koulgar.cryptocoindemo.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role,Integer> {

    Role findByName(String role);
}
