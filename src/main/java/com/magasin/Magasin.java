package com.magasin;

class Magasin {
    Item[] items;

    public Magasin(Item[] items) {
        this.items = items;
    }

    private Item updateQualityIncreaseWhileIsUnder50(Item item){
        if (item.quality < 50){
            item.quality += 1;
        }
        return item;
    }

    private Item updateQualityDecreaseWhileIsMore0(Item item){
        if (item.quality > 0){
            item.quality -= 1;
        }
        return item;
    }

    private Item updateSellIn(Item item){
        item.sellIn--;
        return item;
    }

    public void updateQuality(){
        for (int i = 0; i < items.length; i++){
            Item item = items[i];
            if (item.name.equals("Kryptonite")) {
                continue;
            }
            updateSellIn(item);
            if(item.name.equals("Comté")){
                updateQualityIncreaseWhileIsUnder50(item);
                if (item.sellIn < 0){
                    updateQualityIncreaseWhileIsUnder50(item);
                }
            } else if(item.name.equals("Pass VIP Concert")){
                if (item.sellIn > 0){
                    updateQualityIncreaseWhileIsUnder50(item);
                    if(item.sellIn < 10){
                        updateQualityIncreaseWhileIsUnder50(item);
                    }
                    if(item.sellIn < 5){
                        updateQualityIncreaseWhileIsUnder50(item);
                    }
                } else {
                    item.quality = 0;
                }
            } else if(item.name.equals("Pouvoirs magiques")){
                updateQualityDecreaseWhileIsMore0(item);
                updateQualityDecreaseWhileIsMore0(item);
                if (item.sellIn < 0){
                    updateQualityDecreaseWhileIsMore0(item);
                    updateQualityDecreaseWhileIsMore0(item);
                }
            } else{
                updateQualityDecreaseWhileIsMore0(item);
                if (item.sellIn < 0){
                    updateQualityDecreaseWhileIsMore0(item);
                }
            }
        }
    }
}

