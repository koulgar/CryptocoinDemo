package com.koulgar.cryptocoindemo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name="Cryptocoin")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cryptocoin {

    @Column(name = "id")
    @JsonProperty("id")
    private String id;

    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Column(name = "symbol")
    @JsonProperty("symbol")
    private String symbol;

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coin_rank")
    @JsonProperty("rank")
    private int rank;

    @Column(name = "price_usd")
    @JsonProperty("price_usd")
    private Double priceUsd;

    @Column(name = "price_btc")
    @JsonProperty("price_btc")
    private Double priceBtc;

    @Column(name = "24h_volume_usd")
    @JsonProperty("24h_volume_usd")
    private Double dailyVolumeUsd;

    @Column(name = "market_cap_usd")
    @JsonProperty("market_cap_usd")
    private Double marketCapUsd;

    @Column(name = "available_supply")
    @JsonProperty("available_supply")
    private Double availableSupply;

    @Column(name = "total_supply")
    @JsonProperty("total_supply")
    private Double totalSupply;

    @Column(name = "max_supply")
    @JsonProperty("max_supply")
    private Double maxSupply;

    @Column(name = "percent_change_1h")
    @JsonProperty("percent_change_1h")
    private Double percentChangeHourly;

    @Column(name = "percent_change_24h")
    @JsonProperty("percent_change_24h")
    private Double percentChangeDaily;

    @Column(name = "percent_change_7d")
    @JsonProperty("percent_change_7d")
    private Double percentChangeWeekly;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Double getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(Double priceUsd) {
        this.priceUsd = priceUsd;
    }

    public Double getPriceBtc() {
        return priceBtc;
    }

    public void setPriceBtc(Double priceBtc) {
        this.priceBtc = priceBtc;
    }

    public Double getDailyVolumeUsd() {
        return dailyVolumeUsd;
    }

    public void setDailyVolumeUsd(Double dailyVolumeUsd) {
        this.dailyVolumeUsd = dailyVolumeUsd;
    }

    public Double getMarketCapUsd() {
        return marketCapUsd;
    }

    public void setMarketCapUsd(Double marketCapUsd) {
        this.marketCapUsd = marketCapUsd;
    }

    public Double getAvailableSupply() {
        return availableSupply;
    }

    public void setAvailableSupply(Double availableSupply) {
        this.availableSupply = availableSupply;
    }

    public Double getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(Double totalSupply) {
        this.totalSupply = totalSupply;
    }

    public Double getMaxSupply() {
        return maxSupply;
    }

    public void setMaxSupply(Double maxSupply) {
        this.maxSupply = maxSupply;
    }

    public Double getPercentChangeHourly() {
        return percentChangeHourly;
    }

    public void setPercentChangeHourly(Double percentChangeHourly) {
        this.percentChangeHourly = percentChangeHourly;
    }

    public Double getPercentChangeDaily() {
        return percentChangeDaily;
    }

    public void setPercentChangeDaily(Double percentChangeDaily) {
        this.percentChangeDaily = percentChangeDaily;
    }

    public Double getPercentChangeWeekly() {
        return percentChangeWeekly;
    }

    public void setPercentChangeWeekly(Double percentChangeWeekly) {
        this.percentChangeWeekly = percentChangeWeekly;
    }

    public Cryptocoin() {
    }

    public Cryptocoin(String id, String name, String symbol, int rank) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Cryptocoin{" +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", rank=" + rank +
//                ", priceUsd='" + priceUsd + '\'' +
//                ", priceBtc='" + priceBtc + '\'' +
//                ", dailyVolumeUsd='" + dailyVolumeUsd + '\'' +
//                ", marketCapUsd='" + marketCapUsd + '\'' +
//                ", availableSupply='" + availableSupply + '\'' +
//                ", totalSupply='" + totalSupply + '\'' +
//                ", maxSupply='" + maxSupply + '\'' +
//                ", percentChangeHourly='" + percentChangeHourly + '\'' +
//                ", percentChangeDaily='" + percentChangeDaily + '\'' +
//                ", percentChangeWeekly='" + percentChangeWeekly + '\'' +
                '}';
    }
}

