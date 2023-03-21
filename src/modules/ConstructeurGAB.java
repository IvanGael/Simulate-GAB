package modules;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class ConstructeurGAB {
    private Map<String, CarteBancaire> carteBancaires;

    private ReentrantLock lock;

    public ConstructeurGAB() {
        carteBancaires = new HashMap<>();
        // Initialisation des cartes bancaires disponibles
        carteBancaires.put("1234567890123456", new CarteBancaire("1234567890123456",
                "0906", "John Doe",150000));
        carteBancaires.put("9876543210987654", new CarteBancaire("9876543210987654",
                "9078", "Jane Smith", 250000));
        this.lock = new ReentrantLock();
    }


    public CarteBancaire getCarteBancaire(String numCarte) {
        return carteBancaires.get(numCarte);
    }

    public Map<String, CarteBancaire> getCarteBancaires() {
        return carteBancaires;
    }

    public void retrait(Message message) throws Exception {
        lock.lock();
        try {
            if(carteBancaires.containsKey(message.getCarteBancaire().getNumeroCarte())){
                if(message.getMontant() > 0 && message.getMontant() <= message.getCarteBancaire().getSolde()){
                    message.getCarteBancaire().setSolde(message.getCarteBancaire().getSolde() - message.getMontant());
                    carteBancaires.put(message.getCarteBancaire().getNumeroCarte(), message.getCarteBancaire());
                    Thread.sleep(4000);
                    System.out.println("Retrait effectué avec succès");
                }
                else
                    throw new Exception("Opération impossible. Solde insuffisant!");
            } else {
                throw new Exception("Numéro de carte invalide");
            }
        } finally {
            lock.unlock();
        }
    }

    public void depot(Message message) throws Exception {
        lock.lock();
        try {
            if(carteBancaires.containsKey(message.getCarteBancaire().getNumeroCarte())){
                if(message.getMontant() > 0){
                    message.getCarteBancaire().setSolde(message.getCarteBancaire().getSolde() + message.getMontant());
                    carteBancaires.put(message.getCarteBancaire().getNumeroCarte(), message.getCarteBancaire());
                    Thread.sleep(4000);
                    System.out.println("Dépôt effectué avec succès");
                }
                else
                    throw new Exception("Opération impossible. Montant invalide!");
            } else {
                throw new Exception("Numéro de carte invalide");
            }

        } finally {
            lock.unlock();
        }
    }


    public void consulter(Message message) throws Exception {
        lock.lock();
        try {
            if(carteBancaires.containsKey(message.getCarteBancaire().getNumeroCarte())){
                CarteBancaire c = getCarteBancaire(message.getCarteBancaire().getNumeroCarte());
                Thread.sleep(2000);
                System.out.println("Le solde de votre carte est de : " + c.getSolde() + "€");
            } else {
                throw new Exception("Numéro de carte invalide");
            }
        } finally {
            lock.unlock();
        }
    }

}
