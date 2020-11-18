package fr.istic;

public class Client extends Thread{
    private final Site siteDepart;
    private final Site siteArrivee;
    public Client(Site siteDepart, Site siteArrivee) {
        this.siteDepart = siteDepart;
        this.siteArrivee = siteArrivee;
    }

    /**
     * @param siteDepart le site de départ
     */
    void emprunterVelo(Site siteDepart) {
        siteDepart.destocker();
    }

    /**
     * @param siteArrivee le site d'arrivée
     */
    void restituerVelo(Site siteArrivee) {
        siteArrivee.stocker();
    }

    @Override
    public void run() {
        super.run();
        emprunterVelo(siteDepart);
        try {
            int distance = Math.abs(siteDepart.getNumeroSite() - siteArrivee.getNumeroSite());
            sleep(200 * distance);
        } catch (InterruptedException ignored) {}
        restituerVelo(siteArrivee);
    }
}
