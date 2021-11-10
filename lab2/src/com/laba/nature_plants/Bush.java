package com.laba.nature_plants;
import com.laba.enums.LifeTimeType;
import com.laba.enums.NaturePlantsType;
import com.laba.enums.SeasonType;

public class Bush extends Tree{

        private double height;

        public Bush(String name, double consumeWater,SeasonType seasonType,LifeTimeType lifeTimeType, double height) {
            super(name, NaturePlantsType.BUSH, consumeWater, seasonType, lifeTimeType);
            this.height = height;
        }
    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Bush{" +
                super.toString() +
                "height=" + height +
                "}\n";
    }
}

