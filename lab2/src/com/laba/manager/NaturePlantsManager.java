package com.laba.manager;

import com.laba.enums.NaturePlantsType;
import com.laba.nature_plants.Tree;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class NaturePlantsManager {
    private final List<Tree> list;

    public NaturePlantsManager(List<Tree> list) {
        this.list = list;
    }



        public static class StaticComparator implements Comparator<Tree> {
        @Override
        public int compare(Tree tree1, Tree tree2) {
            return tree1.getName().compareTo(tree2.getName());
        }
    }

        private class InnerComparator implements Comparator<Tree> {
        @Override
        public int compare(Tree tree1, Tree tree2) {
            return (int) Math.signum(tree1.getConsumeWater() - tree2.getConsumeWater());
        }
    }

    public List<Tree> filterByType(NaturePlantsType type) {
        return list.stream().filter(tree -> tree.getNaturePlantsType() == type).collect(Collectors.toList());
    }

    public void sortByName(boolean isDescending) {
        list.sort(new StaticComparator());
        if (isDescending) {
            Collections.reverse(list);
        }
    }

    public void sortByNaturePlantsType(boolean isDescending) {
        list.sort(new Comparator<>() {
            @Override
            public int compare(Tree tree1, Tree tree2) {
                return tree1.getNaturePlantsType().toString().compareTo(tree1.getNaturePlantsType().toString());
            }
        });
        if (isDescending) {
            Collections.reverse(list);
        }
    }

    public void sortByconsumeWater(boolean isDescending) {
        list.sort(new InnerComparator());
        if (isDescending) {
            Collections.reverse(list);
        }
    }

    public void sortByCurentSeason(boolean isDescending) {
        list.sort((tree1, tree2) -> tree1.getCurentSeason().toString().compareTo(tree2.getCurentSeason().toString()));
        if (isDescending) {
            Collections.reverse(list);
        }
    }


    public void sortByLifeTime(boolean isDescending) {
        Comparator<Tree> comparator = Comparator.comparing(Tree::getLifeTimeType);
        comparator = comparator.thenComparing(tree -> tree.getLifeTimeType().toString());
        list.sort(comparator);
        if (isDescending) {
            Collections.reverse(list);
        }
    }

    public List<Tree> getList() {
        return list;
    }



}



