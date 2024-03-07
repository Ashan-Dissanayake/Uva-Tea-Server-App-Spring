package lk.uva.uvateafactory.controller;


import lk.uva.uvateafactory.dao.ProductionorderDao;
import lk.uva.uvateafactory.entity.Productionorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/productionorders")
public class ProductionorderController {

    @Autowired
    private ProductionorderDao productionorderDao;

    @GetMapping(produces = "application/json")
    public List<Productionorder> get(@RequestParam HashMap<String,String> params) {

        String date = params.get("date");
        String areaid = params.get("areaid");
        String teamakerid = params.get("teamakerid");
        String statusid = params.get("statusid");

        List<Productionorder> productionorders = this.productionorderDao.findAll();

        if(params.isEmpty()) return productionorders;

        Stream<Productionorder> producorderstream = productionorders.stream();

        if(date!=null) producorderstream = producorderstream.filter(e -> e.getDate().toString().equals(date));
        if(areaid!=null) producorderstream = producorderstream.filter(e -> e.getArea().getId()==Integer.parseInt(areaid));
        if(teamakerid!=null) producorderstream = producorderstream.filter(e -> e.getTeamaker().getId()==Integer.parseInt(teamakerid));
        if(statusid!=null) producorderstream = producorderstream.filter(e -> e.getProductionorderstatus().getId()==Integer.parseInt(statusid));

        return producorderstream.collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> add(@RequestBody Productionorder productionorder) {

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        if(errors=="") productionorderDao.save(productionorder);
        else errors = "Server Validation Errors : <br>"+errors;

        responce.put("id",String.valueOf(productionorder.getId()));
        responce.put("url","/productionorders/"+productionorder.getId());
        responce.put("errors",errors);

        return responce;

    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> update(@RequestBody Productionorder productionorder) {

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        if(errors=="") productionorderDao.save(productionorder);
        else errors = "Server Validation Errors : <br>"+errors;

        responce.put("id",String.valueOf(productionorder.getId()));
        responce.put("url","/productionorders/"+productionorder.getId());
        responce.put("errors",errors);

        return responce;

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id) {

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        Productionorder productionorder = productionorderDao.findByMyId(id);

        if(productionorder==null)
            errors = errors+"<br> Productionorder Does Not Existed.";

        if(errors=="") productionorderDao.delete(productionorder);
        else errors = "Server Validation Errors : <br>"+errors;

        responce.put("id",String.valueOf(id));
        responce.put("url","/productionorders/"+id);
        responce.put("errors",errors);

        return responce;

    }


}
