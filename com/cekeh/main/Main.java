package com.cekeh.main;

import com.cekeh.witches.Persis;
import com.cekeh.witches.Witch;

/**
 * Thomas vanBommel
 * 11-04-2020
 */
public class Main {

    public static volatile boolean running = true;

    /**
     * Simulation:
     * Three witches share one eye. Their names were Deino (or Dino), Enyo, and Pemphredo (or Pephredo). (https://en.wikipedia.org/wiki/Graeae)
     * Persis wants to steal the eye and make the witches tell the 3 secrets. Secret A, B, and C … read the whole myth if you want to know what those are.
     * Each witch is a thread, and Persis is a thread.
     * Consider the eye as a data object that only one them can have at a time (between the witches and Persis).
     * The 3 witches have their pattern of sharing the eye….someone takes the eye, keeps it for a second, then puts it down, allowing someone else to take the eye. Persis interferes by taking the eye. When Persis has the eye. The witches must tell one of the secrets. Then Persis puts down the eye, for a witch to take, and the sequence continues until all 3 secrets are told to Persis.
     * @param args command line arguments
     * @throws InterruptedException Unable to sleep this thread
     */
    public static void main(String[] args) throws InterruptedException {
        Persis persis = new Persis();
        Witch[] witches = {
            new Witch("Deino",      "Secret A"),
            new Witch("Enyo",       "Secret B"),
            new Witch("Pemphredo",  "Secret C")
        };

        for(Witch w : witches) w.start();

        persis.start();

        while(running){
            if(persis.getKnownSecrets().size() == witches.length){
                persis.victoryChant();
                running = false;
            }

            Thread.sleep(1000);
        }

        for(Witch w : witches) w.join();
        persis.join();
    }
}
