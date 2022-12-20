package extract.vehicule;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VehiculeRepos extends JpaRepository<Vehicule, Integer> {
    @Query(value = "select v.id,v.marque from vassur v where v.mois<=?1", nativeQuery = true)
    public List<Vehicule> getVehiculeByAssur(int mois);
}
