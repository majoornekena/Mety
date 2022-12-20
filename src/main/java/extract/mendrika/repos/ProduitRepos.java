package extract.mendrika.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import extract.mendrika.model.Produit;
public interface ProduitRepos extends JpaRepository<Produit, Integer> {

}
