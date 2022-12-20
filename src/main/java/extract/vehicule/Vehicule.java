package extract.vehicule;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

import extract.vehicule.assurance.Assurvehicule;
import extract.vehicule.kilometrage.Kilometrage;
import lombok.Data;

@Data
@Entity
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String marque;

    @OneToMany(mappedBy = "vehicule")
    List<Kilometrage> kilometrage;
    
    @OneToOne
    @JoinColumn(name = "id", nullable = false, updatable = false)
    Assurvehicule assurvehicule;

    String img;

    public void prepare() {
        for (int i = 0; i < this.getKilometrage().size(); i++) {
            this.getKilometrage().get(i).setVehicule(null);
        }
    }
}
