import modules.*;

import java.security.*;
import java.util.Base64;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TestGAB {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        BlockingQueue<Message> gabQueue = new ArrayBlockingQueue<>(10);
        ConstructeurGAB constructeurGAB = new ConstructeurGAB();
        ModuleSecurite moduleSecurite = new ModuleSecurite();
        ModuleCentral moduleCentral = new ModuleCentral();

        GAB gab = new GAB(gabQueue, constructeurGAB, moduleSecurite, moduleCentral);
        Thread gabThread = new Thread(gab);
        gabThread.start();


        // Créer une paire de clés RSA
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);
        KeyPair keyPair = keyPairGen.generateKeyPair();




        CarteBancaire carteBancaire = new CarteBancaire("1234567890123456",
                "0906", "John Doe",150000);
        Message message = new Message();
        message.setCarteBancaire(carteBancaire);
        message.setMontant(250000);
        message.setDateOperation("21/03/2023");
        message.setTypeOperation("DEPOT");
        message.setNumeroGAB("34562389677676");
        message.setMessageString();


        // Générer une signature pour un message
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(keyPair.getPrivate());
        sign.update(message.getMessageString().getBytes());
        byte[] signature = sign.sign();

        String clePublique = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());

        message.setSignature(Base64.getEncoder().encodeToString(signature));
        message.setCle(clePublique);

        try {
            gabQueue.put(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
