package extract.mendrika.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Produit")
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String nom;
    Double prixunitaire;

    public void setPrixunitaire(Double prixunitaire)throws Exception{
        if(prixunitaire<0){
            throw new Exception("prixunitaire<0");
        }
        this.setPrixunitaire(prixunitaire);
    }

    public Produit(String nom, Double prixunitaire)throws Exception {
        this.setNom(nom);
        this.setPrixunitaire(prixunitaire);
    }

}