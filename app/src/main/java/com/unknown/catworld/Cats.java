package com.unknown.catworld;

public class Cats {

    String catName, isAt;
    Boolean isUnlocked;
    int[] idle, dig;

    public Cats() {
    }

    public Cats(String catName, String isAt, Boolean isUnlocked, int[] idle, int[] dig) {
        this.catName = catName;
        this.isAt = isAt;
        this.isUnlocked = isUnlocked;
        this.idle = idle;
        this.dig = dig;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getIsAt() {
        return isAt;
    }

    public void setIsAt(String isAt) {
        this.isAt = isAt;
    }

    public Boolean getUnlocked() {
        return isUnlocked;
    }

    public void setUnlocked(Boolean unlocked) {
        isUnlocked = unlocked;
    }

    public int[] getIdle() {
        return idle;
    }

    public void setIdle(int[] idle) {
        this.idle = idle;
    }

    public int[] getDig() {
        return dig;
    }

    public void setDig(int[] dig) {
        this.dig = dig;
    }

    //
//    static String Cat1At = "Room", Cat2At = "Room";
//
//    static class Cat1 {
//        public int[] idle = {R.drawable.cat_1_idle_1, R.drawable.cat_1_idle_2};
//        public int[] dig = {R.drawable.cat_1_dig_1, R.drawable.cat_1_dig_2};
//    }
//
//    static class Cat2 {
//        public int[] idle = {R.drawable.cat_2_idle_1, R.drawable.cat_2_idle_2};
//        public int[] dig = {R.drawable.cat_2_dig_1, R.drawable.cat_2_dig_2};
//    }
}
