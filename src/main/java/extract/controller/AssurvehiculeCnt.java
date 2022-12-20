package extract.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import extract.vehicule.assurance.AssurvehiculeServ;
import extract.vehicule.assurance.Assurvehicule;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@RestController
@EqualsAndHashCode(callSuper = true)
public class AssurvehiculeCnt extends Cnt {
    @Autowired
    AssurvehiculeServ assurvehiculeServ;


    @GetMapping("/Assurvehicule")
    public String getAll() {
        List<Assurvehicule> ans = this.getAssurvehiculeServ().list();
        return this.getGson().toJson(ans);
    }

    @PostMapping("/Assurvehicule")
    public String create(@RequestBody Assurvehicule assveh) {
        this.getAssurvehiculeServ().create(assveh);
        return "okay";
    }
}
