package extract.vehicule.kilometrage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import extract.vehicule.Vehicule;
import lombok.Data;

@Data
@Service
public class KilometrageSer {
    @Autowired
    KilometrageRepos kilometrageRepos;

    public void delete(Kilometrage k) {
        this.getKilometrageRepos().delete(k);
    }

    public Kilometrage getById(Integer id) {
        Kilometrage k = new Kilometrage();
        k.setId(id);
        return this.getById(k);
    }

    public Kilometrage getById(Kilometrage v) {
        return this.getKilometrageRepos().findById(v.getId()).get();
    }

    public List<Kilometrage> getByVehicule(Integer id) {
        Vehicule v = new Vehicule();
        v.setId(id);
        return this.getByVehicule(v);
    }

    public List<Kilometrage> getByVehicule(Vehicule v) {
        return this.getKilometrageRepos().getByVehicule(v.getId());
    }

    public void create(Kilometrage k) {
        this.getKilometrageRepos().save(k);
    }
}
