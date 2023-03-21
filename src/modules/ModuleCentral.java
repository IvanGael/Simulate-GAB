package modules;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ModuleCentral {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean hasNewMessage = false;

    private ConstructeurGAB constructeurGAB = new ConstructeurGAB();

    public void receiveMessage(Message message) {
        lock.lock();
        try {
            // Attendre s'il y a un message en cours de traitement
            while (hasNewMessage) {
                condition.await();
            }
            // Traiter le message
            hasNewMessage = true;
            processMessage(message);
            // Avertir le GAB que le traitement est terminé
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void processMessage(Message message) throws Exception {
        // Effectuer l'opération souhaitée par le client
        switch (message.getTypeOperation()){
            case "DEPOT" :
                constructeurGAB.depot(message);
                break;
            case "RETRAIT" :
                constructeurGAB.retrait(message);
                break;
            case "CONSULTATION" :
                constructeurGAB.consulter(message);
                break;
            default :
                System.err.println("Opération non autorisée!");
                break;
        }
        System.out.println("Message traité avec succès !");
    }
}
