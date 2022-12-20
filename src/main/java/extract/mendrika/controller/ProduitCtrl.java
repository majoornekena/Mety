package extract.mendrika.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.gson.Gson;

import lombok.Data;
import lombok.EqualsAndHashCode;

import extract.mendrika.service.ProduitService;
import extract.mendrika.model.Produit;

@Data
//@EqualsAndHashCode(callSuper = false)
@RestController
public class ProduitCtrl {
    

    @Autowired
    ProduitService Pservice;


    @PostMapping("/Produit")
    public String testcreate(@RequestParam(name="nom") String nom,@RequestParam(name="prixunitaire") String prixunitaire)throws Exception {
        this.getPservice().create(nom,prixunitaire/*new Double(prixunitaire).doubleValue()*/);
        return "zan ar eh ";
    }

    @GetMapping("/Produits")
    public  String getProduits() {
        List<Produit> prd = this.getPservice().getAll();
        Gson son= new Gson();
        return (son.toJson(prd));
    }

 
}
