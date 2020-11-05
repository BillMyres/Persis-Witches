package com.cekeh.eye;

/**
 * Mono-state eye pedestal
 * Thomas vanBommel
 * 11-04-2020
 */
public class Pedestal {

    private static volatile Eye eye = new Eye();
    private static volatile String hasEye = "";
    private static volatile String lastHadEye = "";
    private static volatile String secret = "";

    /**
     * Take eye from the pedestal
     * @param name Name of the character thats doing the taking
     * @return null or the eye
     */
    synchronized public static Eye takeEye(String name){
        Eye result = eye;

        if(result != null){
            hasEye = name;
            eye = null;
        }

        return result;
    }

    /**
     * Put the eye back for someone else
     * @param i Eye object
     */
    public static void putEyeBack(Eye i){
        if(i != null) {
            lastHadEye = hasEye;
            eye = i;
        }
    }

    /**
     * Find out who has taken the eye!
     * @return The character that has taken the eye!
     */
    public static String whoHasEye(){
        return hasEye;
    }

    /**
     * Tell the pedestal a secret to pass on to Persis
     * @param message Message / secret to pass on to Persis
     */
    synchronized public static void tellSecret(String message){
        if(secret.equals("")){
            secret = message;
        }
    }

    /**
     * Get the secret offered by the witches for the eye
     * @return secret or "" (empty string)
     */
    public static String getSecret(){
        String result = secret;

        secret = "";

        return result;
    }

    /**
     * Find out who had the eye last (dont take it twice in a row!)
     * @return character name who last had the eye
     */
    public static String whoHadEyeLast(){
        return lastHadEye;
    }
}
