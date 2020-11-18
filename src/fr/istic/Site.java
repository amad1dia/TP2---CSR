package fr.istic;

class Site {
    /* Constantes associees au site */

    static final int STOCK_INIT = 5;
    static final int STOCK_MAX = 10;
    static final int BORNE_SUP = 8;
    static final int BORNE_INF = 2;
    private int nbreVelosDansLeSite;
    private final int numeroSite;

    public Site(int i) {
        numeroSite = i;
        nbreVelosDansLeSite = STOCK_INIT;
    }

    /**
     * Le client dépose le vélo
     */
    public synchronized void stocker() {
        afficher("Emprunt, avant");
        while (nbreVelosDansLeSite == STOCK_MAX) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        nbreVelosDansLeSite++;
        notifyAll();
        afficher("Emprunt, après");
    }

    /**
     * Le client prend un vélo
     */
    public synchronized void destocker() {
        afficher("Restitution, avant");
        while (nbreVelosDansLeSite == 0) {
            try {
                wait();
            } catch (InterruptedException ignored) {
            }
        }
        nbreVelosDansLeSite--;
        notifyAll();
        afficher("Restitution, après");
    }

    /**
     * Le camion équilibre le site
     * @param camion
     */
    public synchronized void equilibrer(Camion camion) {
        afficher("Equilibrage, avant");
        if (nbreVelosDansLeSite > BORNE_SUP) {
            int veloExcedentaires = nbreVelosDansLeSite - STOCK_INIT;
            camion.charger(veloExcedentaires);
            nbreVelosDansLeSite = STOCK_INIT;
        }
        if (nbreVelosDansLeSite < BORNE_INF) {
            int veloADecharger = STOCK_INIT - nbreVelosDansLeSite; //1 => 4
            camion.decharger(veloADecharger);
            nbreVelosDansLeSite += veloADecharger;
        }
        afficher("Equilibrage, après");
        notifyAll();
    }

    public int getNumeroSite() {
        return numeroSite;
    }

    private void afficher(String nomMethode) {
        System.out.println(nomMethode + " : " + Thread.currentThread().getName() + ", Site n° " + numeroSite +
                ", Nombre de vélos dispo : " + nbreVelosDansLeSite);
    }
}
