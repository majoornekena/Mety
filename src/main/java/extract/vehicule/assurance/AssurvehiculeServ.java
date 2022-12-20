package extract.vehicule.assurance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import extract.vehicule.assurance.AssurvehiculeRepos;
import extract.vehicule.assurance.Assurvehicule;

import lombok.Data;

@Data
@Service
public class AssurvehiculeServ {
    @Autowired
    AssurvehiculeRepos assurvehiculeRepos;

    // public void delete(Integer id) {
    //     Vehicule v = new Vehicule();
    //     v.setId(id);
    //     this.delete(v);
    // }

    // public void delete(Vehicule v) {
    //     this.getVehiculeRepos().delete(v);
    // }

    // public Vehicule getById(Integer id) {
    //     return this.getVehiculeRepos().findById(id).get();
    // }

    public List<Assurvehicule> list() {
        return this.getAssurvehiculeRepos().findAll();
    }

    public void create(Assurvehicule assvehicule) {
        this.getAssurvehiculeRepos().save(assvehicule);
    }
}
