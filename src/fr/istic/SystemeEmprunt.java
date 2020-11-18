package fr.istic;

import java.util.Random;

class SystemeEmprunt {

	/* Constantes */

	static final int NB_SITES = 5;
	static final int MAX_CLIENTS = 100;

	/* Attributs */

	private final Client[] clients = new Client[MAX_CLIENTS];
	private final Camion camion;

	/* Constructeur du systeme d'emprunt */
	SystemeEmprunt() {

		/* Instanciation des sites */
		Site[] sites = new Site[NB_SITES];
		for(int i = 0; i < NB_SITES; i++)
			sites[i] = new Site(i);

		Random r = new Random();
		/* Instanciation des clients */
		for(int i = 0; i < MAX_CLIENTS; i++) {

			int siteDepId = r.nextInt(NB_SITES);
			int siteArrId = r.nextInt(NB_SITES);
			clients[i] = new Client(sites[siteDepId], sites[siteArrId]);

		}

		/* Instanciation du camion */
		camion = new Camion(sites);
	}

	/* Point d'entree du programme */

	public static void main(String[] args) {

		var systemeEmprunt = new SystemeEmprunt();

		for (Client client: systemeEmprunt.clients) {
			client.start();
		}
		//Permet d'arreter le thread camion si tous les autres threads sont arrêtés
		systemeEmprunt.camion.setDaemon(true);
		systemeEmprunt.camion.start();
	}

}
