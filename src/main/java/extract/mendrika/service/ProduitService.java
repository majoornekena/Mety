package extract.mendrika.service;

import java.util.List;

import extract.mendrika.model.Produit;

public interface ProduitService {
    public void create(String nom, String prixunitaire)throws Exception;

    public List<Produit> getAll();
}
