package com.magasin;

import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MagasinTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0)};
        Magasin app = new Magasin(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test// Item se dégrade 2 fois plus vite après date de péremption
    void testClassicItemPeremptionPast0(){
        Item[] items = new Item[] {new Item("Patate", 0, 10)};
        Magasin app = new Magasin(items);
        app.updateQuality();
        System.out.println(app.items[0]);
        assertEquals("Patate, -1, 8", app.items[0].toString());
    }

    @Test
    // Un produit ne peut jamais avoir une qualité négative (inférieur à 0)
    void testClassicItemQualityInferieur0(){
        Item[] items = new Item[] {new Item("Pizza", 10, 2)};
        Magasin app = new Magasin(items);
        for (int i = 0; i < 3; i++){
            app.updateQuality();
            System.out.println(app.items[0]);
        }
        assertEquals("Pizza, 7, 0", app.items[0].toString());
    }

    @Test
        // Un produit ne peut jamais avoir une qualité négative (inférieur à 0)
    void testKryptonite(){
        Item[] items = new Item[] {new Item("Kryptonite", 10, 200)};
        Magasin app = new Magasin(items);
        for (int i = 0; i < 3; i++){
            app.updateQuality();
            System.out.println(app.items[0]);
        }
        assertEquals("Kryptonite, 10, 200", app.items[0].toString());
    }

    @Test
        // Un produit ne peut pas avoir une qualité supérieur à 50
        // (ex : le Comté qui augmente sa qualité avec le temps ne peut dépasser 50 de qualité)
    void testItemComteQualitySup50(){
        Item[] items = new Item[] {new Item("Comté", 10, 48)};
        Magasin app = new Magasin(items);
        for (int i = 0; i < 3; i++){
            app.updateQuality();
            System.out.println(app.items[0]);
        }
        assertEquals("Comté, 7, 50", app.items[0].toString());
    }

    @Test
    // Le comté voit sa qualité augmenté même aprs sa date de péremption (SellIn -1)
    void testItemComteQualitySellInUnder0(){
        Item[] items = new Item[] {new Item("Comté", -1, 30)};
        Magasin app = new Magasin(items);
        for (int i = 0; i < 3; i++){
            app.updateQuality();
            System.out.println(app.items[0]);
        }
        assertEquals("Comté, -4, 36", app.items[0].toString());
    }

    //TESTS PASS VIP CONCERT
    @Test
    // 1) SpellIn entre 50 et 10 == Quality +1
    void testPassVIPSellInIs12QualityIs20() {
        Item[] items = new Item[] { new Item("Pass VIP Concert", 12, 20)};
        Magasin app = new Magasin(items);
        app.updateQuality(); // Output "Pass VIP Concert, 11, 21"
        System.out.println(app.items[0]);
        assertEquals("Pass VIP Concert, 11, 21", app.items[0].toString());
    }
    @Test
    // 2) SpellIn entre 10 et 6 == Quality +2
    void testPassVIPSellInIs10QualityIs20() {
        Item[] items = new Item[] { new Item("Pass VIP Concert", 10, 20)};
        Magasin app = new Magasin(items);
        app.updateQuality(); // Output "Pass VIP Concert, 9, 22"
        System.out.println(app.items[0]);
        assertEquals("Pass VIP Concert, 9, 22", app.items[0].toString());
    }

    @Test
    // 3) SpellIn entre 5 et 0 == Quality +3 -> ici 5
    void testPassVIPSellInIs5QualityIs20() {
        Item[] items = new Item[] { new Item("Pass VIP Concert", 5, 20)};
        Magasin app = new Magasin(items);
        app.updateQuality(); // Output "Pass VIP Concert, 4, 23"
        System.out.println(app.items[0]);
        assertEquals("Pass VIP Concert, 4, 23", app.items[0].toString());
    }

    @Test
    // 4) SpellIn après 0 (-1) == Quality = 0
    void testPassVIPSellInIs0QualityIs20() {
        Item[] items = new Item[] { new Item("Pass VIP Concert", 0, 20)};
        Magasin app = new Magasin(items);
        app.updateQuality(); // Output "Pass VIP Concert, -1, 0"
        System.out.println(app.items[0]);
        assertEquals("Pass VIP Concert, -1, 0", app.items[0].toString());
    }

    // TESTS NOUVELLE FONCTIONNALITE
    @Test
    // Les produits "Pouvoirs magiques" se dégradent 2 fois plus vite que les objets normaux
    // Ici Nom item : Pouvoirs magiques, SellIn : de 4 à 3, Quality : de 20 à 18 (-2)
    void testPouvoirsMagiquesSellInMore0() {
        Item[] items = new Item[] { new Item("Pouvoirs magiques", 4, 20)};
        Magasin app = new Magasin(items);
        app.updateQuality();
        System.out.println(app.items[0]);
        assertEquals("Pouvoirs magiques, 3, 18", app.items[0].toString());
    }

    @Test
    // Les produits "Pouvoirs magiques" se dégradent 2 fois plus vite que les objets normaux
    // Ici Nom item : Pouvoirs magiques, SellIn : de 0 à -1, Quality : de 20 à 16 (-4)
    void testPouvoirsMagiquesSellInUnder0() {
        Item[] items = new Item[] { new Item("Pouvoirs magiques", 0, 20)};
        Magasin app = new Magasin(items);
        app.updateQuality();
        System.out.println(app.items[0]);
        assertEquals("Pouvoirs magiques, -1, 16", app.items[0].toString());
    }
}
