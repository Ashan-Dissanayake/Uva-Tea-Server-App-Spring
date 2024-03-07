package lk.uva.uvateafactory.controller;


import lk.uva.uvateafactory.dao.ProductionDao;

import lk.uva.uvateafactory.entity.Production;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/productions")
public class ProductionController {

    @Autowired
    private ProductionDao productionDao;

    @GetMapping(produces = "application/json")
    public List<Production> get(@RequestParam HashMap<String,String> params) {

        String date = params.get("date");
        String areaid = params.get("areaid");
        String teamakerid = params.get("teamakerid");
        String productid = params.get("productid");

        List<Production> productions = this.productionDao.findAll();

        if(params.isEmpty()) return productions;

        Stream<Production> productionstrm = productions.stream();

        if(date!=null) productionstrm = productionstrm.filter(e -> e. getDate().toString().equals(date));
        if(areaid!=null) productionstrm = productionstrm.filter(e -> e. getProductionorder().getArea().getId()==Integer.parseInt(areaid));
        if(teamakerid!=null) productionstrm = productionstrm.filter(e -> e. getProductionorder().getTeamaker().getId()==Integer.parseInt(teamakerid));
        if(productid!=null) productionstrm = productionstrm.filter(e -> e. getProductionproducts().stream().anyMatch(produproduct-> produproduct.getProduct().getId()==Integer.parseInt(productid)));


        return productionstrm.collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> add(@RequestBody Production production) {

        HashMap<String, String> response = new HashMap<>();
        String errors = "";

        production.getProductionproducts().forEach(proproduct -> proproduct.setProduction(production));
        productionDao.save(production);

        response.put("id", String.valueOf(production.getId()));
        response.put("url", "/productions/" + production.getId());
        response.put("errors", errors);

        return response;

    }


    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> update(@RequestBody Production production) {

        HashMap<String, String> response = new HashMap<>();
        String errors = "";

        Production extProduction = productionDao.findByMyId(production.getId());


        if (extProduction != null) {

            try {

                production.getProductionproducts().forEach(proproduct -> proproduct.setProduction(production));
                productionDao.save(production);

                response.put("id", String.valueOf(production.getId()));
                response.put("url", "/productions/" + production.getId());
                response.put("errors", errors);

            } catch (Exception e) {
                errors += "Production is not found"+production.getId();
                response.put("url", "/productions/" + production.getId());
                response.put("errors",errors);
            }
        }

        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id) {

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        Production production = productionDao.findByMyId(id);

        if(production==null)
            errors = errors+"<br> Production Does Not Existed.";

        if(errors.isEmpty()) productionDao.delete(production);
        else errors = "Server Validation Errors : <br>"+errors;

        responce.put("id",String.valueOf(id));
        responce.put("url","/productions/"+id);
        responce.put("errors",errors);

        return responce;

    }

}
