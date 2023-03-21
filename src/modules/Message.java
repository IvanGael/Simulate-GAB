package modules;

import java.util.concurrent.Semaphore;

public class Message {
    private CarteBancaire carteBancaire;
    private double montant;
    private String dateOperation;
    private String typeOperation;
    private String numeroGAB;
    private String signature;
    private String cle;

    private String messageString;

    private static Semaphore sem = new Semaphore(1);

    public Message(CarteBancaire carteBancaire, double montant, String dateOperation,
                   String typeOperation, String numeroGAB, String signature, String cle) {
        this.carteBancaire = carteBancaire;
        this.montant = montant;
        this.dateOperation = dateOperation;
        this.typeOperation = typeOperation;
        this.numeroGAB = numeroGAB;
        this.signature = signature;
        this.cle = cle;
    }

    public Message() {
    }

    public String getMessageString() {
        return messageString;
    }

    public void setMessageString() {
        this.messageString = getCarteBancaire().getNumeroCarte() + getDateOperation() + getTypeOperation() + getNumeroGAB();
    }

    public void setCarteBancaire(CarteBancaire carteBancaire) {
        this.carteBancaire = carteBancaire;
    }

    public void setDateOperation(String dateOperation) {
        this.dateOperation = dateOperation;
    }

    public void setTypeOperation(String typeOperation) {
        this.typeOperation = typeOperation;
    }

    public void setNumeroGAB(String numeroGAB) {
        this.numeroGAB = numeroGAB;
    }

    public CarteBancaire getCarteBancaire() {
        return carteBancaire;
    }

    public String getDateOperation() {
        return dateOperation;
    }

    public String getTypeOperation() {
        return typeOperation;
    }

    public String getNumeroGAB() {
        return numeroGAB;
    }

    public String getSignature() {
        try {
            sem.acquire();
            return signature;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            sem.release();
        }
    }

    public void setSignature(String signature) {
        try {
            sem.acquire();
            this.signature = signature;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            sem.release();
        }
    }

    public String getCle() {
        return cle;
    }

    public void setCle(String cle) {
        this.cle = cle;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }


    // Ajout d'un toString pour faciliter le débogage
    @Override
    public String toString() {
        return "Message{" +
                "numeroCarte='" + carteBancaire.getNumeroCarte() + '\'' +
                ", dateOperation='" + dateOperation + '\'' +
                ", typeOperation='" + typeOperation + '\'' +
                ", numeroGAB='" + numeroGAB + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }
}
