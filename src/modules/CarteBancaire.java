package modules;

import java.util.concurrent.locks.ReentrantLock;

public class CarteBancaire {
    private String numeroCarte;
    private String codeConfidentiel;

    private String nomProprietaire;
    private double solde;
    private ReentrantLock lock;

    public CarteBancaire(String numeroCarte, String codeConfidentiel, String nomProprietaire, double solde) {
        this.numeroCarte = numeroCarte;
        this.codeConfidentiel = codeConfidentiel;
        this.nomProprietaire = nomProprietaire;
        this.solde = solde;
        this.lock = new ReentrantLock();
    }

    public String getNumeroCarte() {
        return numeroCarte;
    }

    public void setNumeroCarte(String numeroCarte) {
        this.numeroCarte = numeroCarte;
    }

    public String getCodeConfidentiel() {
        return codeConfidentiel;
    }

    public void setCodeConfidentiel(String codeConfidentiel) {
        this.codeConfidentiel = codeConfidentiel;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public String getNomProprietaire() {
        return nomProprietaire;
    }

    public void setNomProprietaire(String nomProprietaire) {
        this.nomProprietaire = nomProprietaire;
    }

}

