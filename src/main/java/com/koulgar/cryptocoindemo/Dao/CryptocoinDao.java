package com.koulgar.cryptocoindemo.Dao;

import com.koulgar.cryptocoindemo.Entity.Cryptocoin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CryptocoinDao extends JpaRepository<Cryptocoin,Integer> {

    Cryptocoin findByRank(int rank);

    @Transactional
    void deleteByRank(int rank);

    Page<Cryptocoin> findAll(Pageable pageable);

    @Query("Select c from Cryptocoin c where c.name like %?1% or c.symbol like %?1%")
    Page<Cryptocoin> findBySearch(String search,Pageable pageable);
}
