package services;

import model.Product;
import model.Produs;
import repositories.ProdusRepository;

import java.util.List;

public class ProdusService {

    private ProdusRepository produseRepository;

    public ProdusService() {
        produseRepository = new ProdusRepository();
    }

    public void clearDatabase() {produseRepository.clearDatabase();}

    public void setDatabase(Database d) {
        produseRepository.setDatabase(d);
    }

    public void adaugaProdus(Product p) {
        produseRepository.adaugaProdus(p);
    }

    /*public List<Produs> getProduse() {
        return produseRepository.findProduse();
    }*/

    public void delete(Produs p) {
        produseRepository.stergeProdus(p);
    }
}

