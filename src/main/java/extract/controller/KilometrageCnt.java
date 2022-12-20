package extract.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import extract.auth.TokenSer;
import extract.except.HError;
import extract.vehicule.kilometrage.Kilometrage;
import extract.vehicule.kilometrage.KilometrageSer;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@RestController
@EqualsAndHashCode(callSuper = true)
public class KilometrageCnt extends Cnt {
    @Autowired
    KilometrageSer kilometrageSer;

    @Autowired
    TokenSer tokenSer;

    @DeleteMapping("/kilometrage/{id}")
    public String delete(@PathVariable String id) {
        try {
            Kilometrage k = new Kilometrage();
            k.setId(Integer.parseInt(id));
            this.getKilometrageSer().delete(k);
            return "ok";
        } catch (Exception e) {
            HError err = new HError(404, e.getMessage());
            return this.getGson().toJson(err);
        }
    }

    @PutMapping("/kilometrage/{id}")
    public String update(@PathVariable String id, @RequestBody Kilometrage k) {
        try {
            k.setId(Integer.parseInt(id));
            return this.create(k);
        } catch (Exception e) {
            HError err = new HError(404, e.getMessage());
            return this.getGson().toJson(err);
        }
    }

    @GetMapping("/kilometrage/{id}")
    public String getOne(@PathVariable String id) {
        try {
            Kilometrage k = new Kilometrage();
            k.setId(Integer.parseInt(id));
            k = this.getKilometrageSer().getById(k);
            return this.getGson().toJson(k);
        } catch (Exception e) {
            HError err = new HError(404, e.getMessage());
            return this.getGson().toJson(err);
        }
    }

    @GetMapping("/kilometrage/vehicule/{id}")
    public String getByVehicule(@PathVariable String id, @RequestParam("idUser") String idUser) {
        try {
            this.getTokenSer().askAccess(Integer.parseInt(idUser));
            List<Kilometrage> ans = this.getKilometrageSer().getByVehicule(Integer.parseInt(id));
            // System.out.println(ans.get(0).getVehicule().getKilometrage());
            return this.getGson().toJson(ans);
        } catch (Exception e) {
            HError err = new HError(404, e.getMessage());
            return this.getGson().toJson(err);
        }
    }

    @PostMapping("/kilometrage")
    public String create(@RequestBody Kilometrage k) {
        try {
            this.getKilometrageSer().create(k);
            return "ok";
        } catch (Exception e) {
            HError err = new HError(404, e.getMessage());
            return this.getGson().toJson(err);
        }
    }
}
