package fr.istic;

public class Camion extends Thread {
    private final Site[] sites;
    private int numeroSite = 0;
    private int nbVelos;

    public Camion(Site[] sites) {
        this.sites = sites;
        nbVelos = 10;
    }

    void seDeplacer() {
        try {
            sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        numeroSite++;
        if (numeroSite == sites.length) {
            numeroSite = 0;
        }
    }

    void charger(int nbVelos) {
        this.nbVelos += nbVelos;
    }

    void decharger(int nbVelos) {
        if (this.nbVelos < nbVelos)
            this.nbVelos = 0;
        else
            this.nbVelos -= nbVelos;
    }

    public int getNbVelos() {
        return this.nbVelos;
    }


    @Override
    public void run() {
        while (true) {
            sites[numeroSite].equilibrer(this);
            seDeplacer();
        }
    }
}
