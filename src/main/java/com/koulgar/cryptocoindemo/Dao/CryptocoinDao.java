package com.koulgar.cryptocoindemo.Dao;

import com.koulgar.cryptocoindemo.Entity.Cryptocoin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CryptocoinDao extends JpaRepository<Cryptocoin,Integer> {

    Cryptocoin findByRank(int rank);

    @Transactional
    void deleteByRank(int rank);

    Page<Cryptocoin> findAll(Pageable pageable);

}
