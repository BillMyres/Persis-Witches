package com.cekeh.witches;

import com.cekeh.eye.Eye;
import com.cekeh.eye.Pedestal;
import com.cekeh.main.Main;

/**
 * All characters that can hold the eye
 * Thomas vanBommel
 * 11-04-2020
 */
public abstract class Eyeholder extends Thread {

    private final String name;

    /**
     * Create a new Eyeholder character
     * @param name Name of the character
     */
    public Eyeholder(String name){
        this.name = name;
    }

    /**
     * Try to take the eye from the pedestal
     */
    @Override
    public void run() {
        System.out.println(name + " has started sharing...");

        while(Main.running){
            if(!Pedestal.whoHadEyeLast().equals(name)) {
                Eye eye = Pedestal.takeEye(name);

                if (eye != null) {
                    System.out.printf("%s: I have the eye!%n", name);

                    doSomethingWithEye(eye);
                } else {
                    failedToGrabEye();
                }
            }
        }
    }

    /**
     * Do something with the eye, you have it!
     * @param eye The one and only eye!
     */
    abstract void doSomethingWithEye(Eye eye);

    /**
     * Unable to grab the eye
     */
    abstract void failedToGrabEye();
}
