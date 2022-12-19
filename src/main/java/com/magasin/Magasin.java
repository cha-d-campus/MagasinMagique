package com.magasin;

class Magasin {
    Item[] items;

    public Magasin(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            // Si le nom de l'item est différent de "Comté" et de "Passe VIP Concert" -> donc égal à Kryptonite ou autre
            if (!items[i].name.equals("Comté")
                    && !items[i].name.equals("Pass VIP Concert")) {
                // Si la qualité de l'item est supérieur à 0
                if (items[i].quality > 0) {
                    //Si le nom de l'item n'est pas Kryptonite -> donc est égal à autre
                    if (!items[i].name.equals("Kryptonite")) {
                        items[i].quality = items[i].quality - 1; // La qualité de l'item est égale à la qualité de l'item - 1 (perte de qualité à un jour passé)
                    }
                }
            // Sinon
            } else {
                // Si la qualité de l'item est inférieur à 50
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1; // La qualité de l'item est égale à la qualité de l'item + 1
                    // Si le nom de l'item est égal à "Passe VIP Concert"
                    if (items[i].name.equals("Pass VIP Concert")) {
                        // Si le nombre de jours restants de l'item est inférieur à 11
                        if (items[i].sellIn < 11) {
                            // Si la qualité de l'item est inférieur à 50
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1; // La qualité de l'item est égale à la qualité de l'item + 1
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                    }
                }
            }

            if (!items[i].name.equals("Kryptonite")) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0) {
                if (!items[i].name.equals("Comté")) {
                    if (!items[i].name.equals("Pass VIP Concert")) {
                        if (items[i].quality > 0) {
                            if (!items[i].name.equals("Kryptonite")) {
                                items[i].quality = items[i].quality - 1;
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality;
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
        }
    }
}
