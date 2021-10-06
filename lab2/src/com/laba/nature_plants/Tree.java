package com.laba.nature_plants;
import com.laba.enums.LifeTimeType;
import com.laba.enums.NaturePlantsType;
import com.laba.enums.SeasonType;
public class Tree {
    private String name;
    private NaturePlantsType naturePlantsType;
    private double consumeWater;
    private SeasonType seasonType;
    private LifeTimeType lifeTimeType;

        public Tree(String name, NaturePlantsType naturePlantsType, double consumeWater, SeasonType seasonType,LifeTimeType lifeTimeType ) {
        this.name = name;
        this.naturePlantsType = naturePlantsType;
        this.consumeWater = consumeWater;
        this.seasonType = seasonType;
        this.lifeTimeType = lifeTimeType;
    }

        public String getName() {
        return name;
    }

    public NaturePlantsType getNaturePlantsType() {
        return naturePlantsType;
    }

        public double getConsumeWater() {
        return consumeWater;
    }

    public SeasonType getCurentSeason() {
        return seasonType;
    }

    public LifeTimeType getLifeTimeType() {
        return lifeTimeType;
    }

    public void setNaturePlantsType(NaturePlantsType type) {
        this.naturePlantsType = type;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "name='" + name + '\'' +
                ", naturePlantsType=" + naturePlantsType +
                ", consumeWater=" + consumeWater +
                ", seasonType=" + seasonType +
                ", lifeTimeType=" + lifeTimeType +
                "}\n";
    }
}
