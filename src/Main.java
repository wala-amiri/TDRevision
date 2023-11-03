public class Main {
    public static void main(String[] args) {
        Agence agence = new Agence("Agence de Location de Voitures");

        Voiture v1 = new Voiture(1, "isuzu", 100);
        Voiture v2 = new Voiture(2, "polo", 200);
        Voiture v3 = new Voiture(3, "Nissan", 400);

        Client client1 = new Client(101, "John", "Doe");
        Client client2 = new Client(102, "Alice", "Smith");

        try {
            agence.ajoutVoiture(v1);
            agence.ajoutVoiture(v2);
            agence.ajoutVoiture(v3);

            agence.loueClientVoiture(client1, v1);
            agence.loueClientVoiture(client1, v2);
            agence.loueClientVoiture(client2, v3);

            System.out.println("Liste des voitures disponibles :");
            agence.getVs().affiche();

            System.out.println("Liste des voitures louées par " + client1.getNom() + " :");
            agence.getClientVoitureLoue().get(client1).affiche();

            System.out.println("Liste des voitures louées par " + client2.getNom() + " :");
            agence.getClientVoitureLoue().get(client2).affiche();

            System.out.println("Clients et leurs voitures louées :");
            agence.afficheLesClientsEtLeursListesVoitures();

            System.out.println("Tri des clients par code croissant :");
            agence.triCodeCroissant().forEach((key, value) -> {
                System.out.println("Client : " + key);
                System.out.println("Voitures louées :");
                value.affiche();
            });

            System.out.println("Tri des clients par nom croissant :");
            agence.triNomCroissant().forEach((key, value) -> {
                System.out.println("Client : " + key);
                System.out.println("Voitures louées :");
                value.affiche();
            });

        } catch (VoitureException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }
}
