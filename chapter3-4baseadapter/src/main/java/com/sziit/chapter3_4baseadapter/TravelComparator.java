package com.sziit.chapter3_4baseadapter;

import java.util.Comparator;

public class TravelComparator {
    public static Comparator<ItemBean> f1 = new Comparator<ItemBean>() {
        @Override
        public int compare(ItemBean o1, ItemBean o2) {
            int result = (int)((o2.getiContent() - o1.getiContent()));
            return result;
        }
    };
    public static Comparator<ItemBean> f2 = new Comparator<ItemBean>() {
        @Override
        public int compare(ItemBean o1, ItemBean o2) {
            int result = (int)((o1.getiContent() - o2.getiContent()));
            return result;
        }
    };
    public static Comparator<ItemBean> ScoreDeseending = new Comparator<ItemBean>() {
        @Override
        public int compare(ItemBean o1, ItemBean o2) {
            int result = (int)((o2.getiMoney() - o1.getiMoney()));
            return result;
        }
    };
    public static Comparator<ItemBean> AnDeseding = new Comparator<ItemBean>() {
        @Override
        public int compare(ItemBean o1, ItemBean o2) {
            int result = (int)((o1.getiMoney() - o2.getiMoney()));
            return result;
        }
    };
    public static Comparator<ItemBean> z1 = new Comparator<ItemBean>() {
        @Override
        public int compare(ItemBean o1, ItemBean o2) {
            int result = (int)((o2.getIifo() - o1.getIifo()));
            return result;
        }
    };
    public static Comparator<ItemBean> z2 = new Comparator<ItemBean>() {
        @Override
        public int compare(ItemBean o1, ItemBean o2) {
            int result = (int)((o1.getIifo() - o2.getIifo()));
            return result;
        }
    };
}
