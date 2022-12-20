package extract.vehicule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import extract.auth.NoAuth;
import extract.auth.TokenSer;
import lombok.Data;

@Data
@Service
public class VehiculeSer {
    @Autowired
    VehiculeRepos vehiculeRepos;

    @Autowired
    TokenSer tokenSer;

    public void delete(Integer id) {
        Vehicule v = new Vehicule();
        v.setId(id);
        this.delete(v);
    }

    public void delete(Vehicule v) {
        this.getVehiculeRepos().delete(v);
    }

    public Vehicule getById(Integer id, String idU) throws NoAuth {
        int idUi = Integer.parseInt(idU);
        this.getTokenSer().askAccess(idUi);
        return this.getVehiculeRepos().findById(id).get();
    }

    public List<Vehicule> getByAssur(int mois,String idU) throws NoAuth {
        int idUi = Integer.parseInt(idU);
        this.getTokenSer().askAccess(idUi);
        
        return this.prepare(this.getVehiculeRepos().getVehiculeByAssur(mois));
    }

    public List<Vehicule> prepare(List<Vehicule> liste) {
        for (int i = 0; i < liste.size(); i++) {
            liste.get(i).prepare();
        }
        return liste;
    }

    public List<Vehicule> list() {
        List<Vehicule> ans = this.getVehiculeRepos().findAll();
        this.prepare(ans);
        return ans;
    }

    public void create(Vehicule vehicule) {
        this.getVehiculeRepos().save(vehicule);
    }
}
