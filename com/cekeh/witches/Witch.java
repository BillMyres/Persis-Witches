package com.cekeh.witches;

import com.cekeh.eye.Eye;
import com.cekeh.eye.Pedestal;

/**
 * Mythical witch
 * Thomas vanBommel
 * 11-04-2020
 */
public class Witch extends Eyeholder {

    private final String secret;

    /**
     * Create a new witch
     * @param name Name of the witch
     */
    public Witch(String name, String secret){
        super(name);

        this.secret = secret;
    }

    /**
     * Do something with the eye, you have it!
     * @param eye The one and only eye!
     */
    @Override
    void doSomethingWithEye(Eye eye) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignore) {}

        Pedestal.putEyeBack(eye);
    }

    /**
     * Unable to grab the eye
     */
    @Override
    void failedToGrabEye() {
        if(Pedestal.whoHasEye().equals("Persis")){
            Pedestal.tellSecret(secret);
        }
    }
}
