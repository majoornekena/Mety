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

import extract.auth.NoAuth;
import extract.vehicule.Vehicule;
import extract.vehicule.VehiculeSer;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@RestController
@EqualsAndHashCode(callSuper = true)
public class VehiculeCnt extends Cnt {
    @Autowired
    VehiculeSer vehiculeSer;

    public String toAns(Object o) {
        Success s = new Success();
        s.setData(o);
        return this.getGson().toJson(s);
    }

    public String toErr(Exception e) {
        Error err = new Error();
        err.code = 500;
        err.setMessage(e.getMessage());
        return this.getGson().toJson(err);
    }

    @PutMapping("/voiture/{id}")
    public String update(@PathVariable String id, @RequestBody Vehicule v) {
        try {
            v.setId(Integer.parseInt(id));
            this.getVehiculeSer().create(v);
            return this.toAns(v);
        } catch (Exception e) {
            e.printStackTrace();
            return this.toErr(e);
        }
    }

    @DeleteMapping("/voiture/{id}")
    public String delete(@PathVariable String id) {
        try {
            this.getVehiculeSer().delete(Integer.parseInt(id));
            return "ok";
        } catch (Exception e) {
            return this.toErr(e);
        }
    }

    @GetMapping("/voiture/{id}")
    public String getOne(@PathVariable String id, @RequestParam("idUser") String idU) {
        try {
            Vehicule v = this.getVehiculeSer().getById(Integer.parseInt(id), idU);
            return this.getGson().toJson(v);
        } catch (Exception e) {
            return this.toErr(e);
        }
    }

    @GetMapping("/voiture")
    public String getAll() {
        List<Vehicule> ans = this.getVehiculeSer().list();
        return this.toAns(ans);
    }

    @GetMapping("/voiture/mois/{mois}")
    public String getByAssurance(@PathVariable String mois, @RequestParam("idUser") String idU) {
        try {
            List<Vehicule> ans = this.getVehiculeSer().getByAssur(Integer.parseInt(mois), idU);
            return this.toAns(ans);
        } catch (NumberFormatException | NoAuth e) {
            return this.toErr(e);
        }
    }

    @PostMapping("/voiture")
    public String create(@RequestBody Vehicule vehicule) {
        this.getVehiculeSer().create(vehicule);
        return this.toAns("ok");
    }
}
