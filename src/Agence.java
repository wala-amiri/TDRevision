import java.util.*;

public class Agence {
    private String nom;

    public ListVoitures getVs() {
        return vs;
    }

    public void setVs(ListVoitures vs) {
        this.vs = vs;
    }

    public Map<Client, ListVoitures> getClientVoitureLoue() {
        return ClientVoitureLoue;
    }

    public void setClientVoitureLoue(Map<Client, ListVoitures> clientVoitureLoue) {
        ClientVoitureLoue = clientVoitureLoue;
    }

    private ListVoitures vs;
    private Map<Client, ListVoitures> ClientVoitureLoue;

    public Agence(String nom) {
        this.nom = nom;
        this.vs = new ListVoitures();
        this.ClientVoitureLoue = new HashMap<>();
    }

    public void ajoutVoiture(Voiture v) throws VoitureException {
        vs.ajoutVoiture(v);
    }

    public void suppVoiture(Voiture v) throws VoitureException {
        vs.supprimeVoiture(v);
        for (ListVoitures lv : ClientVoitureLoue.values()) {
            lv.supprimeVoiture(v);
        }
    }

    public void loueClientVoiture(Client cl, Voiture v) throws VoitureException {
        if (ClientVoitureLoue.containsKey(cl)) {
            ListVoitures lv = ClientVoitureLoue.get(cl);
            lv.ajoutVoiture(v);
        } else {
            ListVoitures lv = new ListVoitures();
            lv.ajoutVoiture(v);
            ClientVoitureLoue.put(cl, lv);
        }
    }

    public void retourClientVoiture(Client cl, Voiture v) throws VoitureException {
        if (ClientVoitureLoue.containsKey(cl)) {
            ListVoitures lv = ClientVoitureLoue.get(cl);
            lv.supprimeVoiture(v);
        } else {
            throw new VoitureException("Le client n'a pas loué de voiture.");
        }
    }

    public List<Voiture> selectVoitureSelonCritere(Critere c) {
        List<Voiture> result = new ArrayList<>();
        for (Voiture v : vs.getVoitures()) {
            if (c.estSatisfaitPar(v)) {
                result.add(v);
            }
        }
        return result;
    }

    public Set<Client> ensembleClientsLoueurs() {
        return ClientVoitureLoue.keySet();
    }

    public Collection<ListVoitures> collectionVoituresLouees() {
        return ClientVoitureLoue.values();
    }

    public void afficheLesClientsEtLeursListesVoitures() {
        for (Map.Entry<Client, ListVoitures> entry : ClientVoitureLoue.entrySet()) {
            System.out.println("Client : " + entry.getKey());
            System.out.println("Voitures louées : ");
            entry.getValue().affiche();
        }
    }

    public Map<Client, ListVoitures> triCodeCroissant() {
        Map<Client, ListVoitures> triee = new TreeMap<>(new Comparator<Client>() {
            @Override
            public int compare(Client c1, Client c2) {
                return c1.getCode() - c2.getCode();
            }
        });
        triee.putAll(ClientVoitureLoue);
        return triee;
    }

    public Map<Client, ListVoitures> triNomCroissant() {
        Map<Client, ListVoitures> triee = new TreeMap<>(new Comparator<Client>() {
            @Override
            public int compare(Client c1, Client c2) {
                return c1.getNom().compareTo(c2.getNom());
            }
        });
        triee.putAll(ClientVoitureLoue);
        return triee;
    }
}