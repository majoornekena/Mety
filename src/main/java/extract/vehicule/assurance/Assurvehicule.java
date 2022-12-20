package extract.vehicule.assurance;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;

import extract.vehicule.Vehicule;
import lombok.Data;

@Data
@Entity
@Table(name = "assurancerestant")
public class Assurvehicule {
    @Id
    Integer id;

    @ManyToOne
    @JoinColumn(name = "idassurance", nullable = false, updatable = false)
    Assurance assurance;

    // @ManyToOne
    // @JoinColumn(name = "idvehicule", nullable = false, updatable = false)
    // Vehicule vehicule;
    int idvehicule;

    int mois;
    int jour;
}