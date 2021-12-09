package org.SpecikMan.Entity;

public class Shop {
    private String idItem;
    private String itemName;
    private String description;
    private Integer cost;
    private Integer maxLimit;
    private String imagePath;
    private Integer timeUsed;
    private String tips;
    private String effectsBy;

    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(Integer maxLimit) {
        this.maxLimit = maxLimit;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getTimeUsed() {
        return timeUsed;
    }

    public void setTimeUsed(Integer timeUsed) {
        this.timeUsed = timeUsed;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getEffectsBy() {
        return effectsBy;
    }

    public void setEffectsBy(String effectsBy) {
        this.effectsBy = effectsBy;
    }

    public Shop() {
    }

    public Shop(String idItem, String itemName, String description, Integer cost, Integer maxLimit,String imagePath,Integer timeUsed,String tips,String effectsBy) {
        this.idItem = idItem;
        this.itemName = itemName;
        this.description = description;
        this.cost = cost;
        this.maxLimit = maxLimit;
        this.imagePath = imagePath;
        this.timeUsed = timeUsed;
        this.tips = tips;
        this.effectsBy = effectsBy;
    }
    public Shop(String idItem){
        this.idItem = idItem;
    }
    public Shop(String itemName,String test){
        this.itemName = itemName;
    }
    public Shop(String idItem,String itemName,String test){
        this.idItem = idItem;
        this.itemName = itemName;
    }
    @Override
    public String toString() {
        return this.getItemName();
    }
}
