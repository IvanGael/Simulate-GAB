package modules;

import java.util.concurrent.BlockingQueue;

public class GAB implements Runnable {
    private final BlockingQueue<Message> gabQueue;
    private final ConstructeurGAB constructeurGAB;
    private final ModuleSecurite moduleSecurite;
    private final ModuleCentral moduleCentral;

    public GAB(BlockingQueue<Message> gabQueue, ConstructeurGAB constructeurGAB, ModuleSecurite moduleSecurite, ModuleCentral moduleCentral) {
        this.gabQueue = gabQueue;
        this.constructeurGAB = constructeurGAB;
        this.moduleSecurite = moduleSecurite;
        this.moduleCentral = moduleCentral;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message message = gabQueue.take();
                System.out.println("GAB reçoit le message " + message);
                if (moduleSecurite.verifierSignature(message)) {
                    if (moduleSecurite.verifierCodeConfidentiel(message.getCarteBancaire().getCodeConfidentiel())) {
                        moduleCentral.receiveMessage(message);
                    } else {
                        throw new RuntimeException("Le code confidentiel est incorrect");
                    }
                } else {
                    throw new RuntimeException("La signature du message est incorrecte");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
