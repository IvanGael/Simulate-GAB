package modules;

import utils.SignatureUtils;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ModuleSecurite {
    private Lock lock;;
    private ConstructeurGAB constructeurGAB;

    public ModuleSecurite() {
        this.lock = new ReentrantLock();
        this.constructeurGAB = new ConstructeurGAB();
    }

    public boolean verifierCodeConfidentiel(String code) {
        lock.lock();
        try {
            for (String numeroCarte : constructeurGAB.getCarteBancaires().keySet()) {
                CarteBancaire carteBancaire = constructeurGAB.getCarteBancaires().get(numeroCarte);
                if (carteBancaire.getCodeConfidentiel().equals(code)) {
                    return true;
                }
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public boolean verifierSignature(Message message) {
        lock.lock();
        try {
            // Vérification de la signature
            return SignatureUtils.verifierSignature(message.getMessageString(), message.getCle(), message.getSignature());
        } finally {
            lock.unlock();
        }
    }
}

