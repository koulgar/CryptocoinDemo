package com.koulgar.cryptocoindemo.Service;

import com.koulgar.cryptocoindemo.Dao.CryptocoinDao;
import com.koulgar.cryptocoindemo.Entity.Cryptocoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CryptocoinService {

    @Autowired
    private CryptocoinDao cryptocoinDao;

    public Page<Cryptocoin> findAll(Pageable pageable){
        return cryptocoinDao.findAll(pageable);
    }

    public Cryptocoin findByRank(int coinId) {
        return cryptocoinDao.findByRank(coinId);
    }

    public void save(Cryptocoin coin) {
        cryptocoinDao.save(coin);
    }

    public void saveAll(List<Cryptocoin> coins) {
        cryptocoinDao.saveAll(coins);
    }

    public void deleteByRank(int rank) {
        cryptocoinDao.deleteByRank(rank);
    }

    public void saveCryptocoins() {
        Cryptocoin[] cryptocoins = createCoinTemplate();
        saveAll(Arrays.asList(cryptocoins));
    }

    public Cryptocoin[] createCoinTemplate() {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate restTemplate = restTemplateBuilder.build();
        return restTemplate.getForObject("https://api.coinmarketcap.com/v1/ticker/",Cryptocoin[].class);
    }

    public Page<Cryptocoin> findBySearch(String search,Pageable pageable) {
        return cryptocoinDao.findBySearch(search,pageable);
    }
}
