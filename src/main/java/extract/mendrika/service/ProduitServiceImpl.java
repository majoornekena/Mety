package extract.mendrika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import extract.mendrika.model.Produit;
import extract.mendrika.repos.ProduitRepos;

import lombok.Data;

@Service
@Data
public class ProduitServiceImpl implements ProduitService {
    @Autowired
    ProduitRepos pr;

    public void create(String nom, String prixunitaire)throws Exception {
        Produit prod = new Produit(nom, new Double(prixunitaire));
        this.getPr().save(prod);
    }

    public List<Produit> getAll() {
        return this.getPr().findAll();
    }

}
