package extract.vehicule.kilometrage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KilometrageRepos extends JpaRepository<Kilometrage, Integer> {
    @Query(value = "select * from kilometrage where vehiculeid=:idv", nativeQuery = true)
    public List<Kilometrage> getByVehicule(@Param("idv") Integer idV);
}
