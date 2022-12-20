package extract.vehicule;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import extract.zframework.annotation.relation.Table;
import lombok.Data;

@Data
@Entity
@Table("imagevehicule")
public class Photo {
    @Id
    Integer id;
    String img;

    int idvehicule;

}
