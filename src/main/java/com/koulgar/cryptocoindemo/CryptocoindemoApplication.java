package com.koulgar.cryptocoindemo;

import com.koulgar.cryptocoindemo.Service.CryptocoinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class CryptocoindemoApplication {

    @Autowired
    private CryptocoinService cryptocoinService;

    Logger logger = LoggerFactory.getLogger(CryptocoindemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CryptocoindemoApplication.class, args);

    }

    @Scheduled(fixedRate = 60*1000)
    public void run(){
        logger.info("Save initialized.");
        cryptocoinService.saveCryptocoins();
        logger.info("Save completed!");
    }

}


//from JSON file
//            ObjectMapper mapper = new ObjectMapper();
//            TypeReference<List<Cryptocoin>> typeReference = new TypeReference<List<Cryptocoin>>(){};
//            InputStream inputStream = TypeReference.class.getResourceAsStream("/JSON/coins.json/");
//            try {
//                List<Cryptocoin> cryptocoins = mapper.readValue(inputStream,typeReference);
//                cryptocoinService.saveAll(cryptocoins);
//                System.out.println(cryptocoins);
//                System.out.println("Coins Saved!");
//            } catch (IOException exc){
//                System.out.println("Unable to save coins: "+exc.getMessage());
//            }

//https://api.coinmarketcap.com/v1/ticker/
