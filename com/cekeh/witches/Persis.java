package com.cekeh.witches;

import com.cekeh.eye.Eye;
import com.cekeh.eye.Pedestal;

import java.util.ArrayList;
import java.util.List;

/**
 * Persis
 * Thomas vanBommel
 * 11-04-2020
 */
public class Persis extends Eyeholder {

    private static final String NAME = "Persis";
    private final ArrayList<String> secrets = new ArrayList<>();

    /**
     * Create a new Persis
     */
    public Persis(){
        super(NAME);
    }

    /**
     * Do something with the eye, you have it!
     * @param eye The one and only eye!
     */
    @Override
    void doSomethingWithEye(Eye eye) {
        String secret;

        System.out.printf("%s: Tell me a secret!%n", NAME);

        while((secret = Pedestal.getSecret()).isEmpty()){
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignore) {}
        }

        if(!secrets.contains(secret)) secrets.add(secret);
        System.out.printf("\033[3m%s\033[0m%n", secret);

        Pedestal.putEyeBack(eye);
    }

    /**
     * Unable to grab the eye
     */
    @Override
    void failedToGrabEye() {}

    /**
     * Check known secrets
     * @return Known secrets from witches
     */
    public List<String> getKnownSecrets(){
        return secrets;
    }

    /**
     * Persis has won! Time for a chant.
     */
    public void victoryChant(){
        System.out.printf("%s: Medusa, here I come!%n", NAME);
    }
}
