package lk.uva.uvateafactory.controller;


import lk.uva.uvateafactory.dao.FertilizerDao;
import lk.uva.uvateafactory.entity.Fertilizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/fertilizers")
public class FertilizerController {

    @Autowired
    private FertilizerDao fertilizerDao;

    @GetMapping(produces = "application/json")
    public List<Fertilizer> get(@RequestParam HashMap<String,String> params) {

        String name = params.get("name");
        String brandid = params.get("brandid");
        String fertypeid = params.get("fertypeid");
        String ferstatusid = params.get("ferstatusid");

        List<Fertilizer> fertilizers = this.fertilizerDao.findAll();

        if(params.isEmpty()) return fertilizers;

        Stream<Fertilizer> fertilizerStream = fertilizers.stream();

        if(name!=null) fertilizerStream = fertilizerStream.filter(fstream -> fstream.getName().contains(name));
        if(brandid!=null) fertilizerStream = fertilizerStream.filter(fstream -> fstream.getFertilzerbrand().getId()==Integer.parseInt(brandid));
        if(fertypeid!=null) fertilizerStream = fertilizerStream.filter(fstream -> fstream.getFertilizertype().getId()==Integer.parseInt(fertypeid));
        if(ferstatusid!=null) fertilizerStream = fertilizerStream.filter(fstream -> fstream.getFertilizerstatus().getId()==Integer.parseInt(ferstatusid));
        if(brandid!=null) fertilizerStream = fertilizerStream.filter(fstream -> fstream.getFertilzerbrand().getId()==Integer.parseInt(brandid));

        return fertilizerStream.collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> add(@RequestBody Fertilizer fertilizer) {

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        if(errors=="") fertilizerDao.save(fertilizer);
        else errors = "Server Validation Errors : <br>"+errors;

        responce.put("id",String.valueOf(fertilizer.getId()));
        responce.put("url","/fertilizers/"+fertilizer.getId());
        responce.put("errors",errors);

        return responce;

    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> update(@RequestBody Fertilizer fertilizer) {

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        if(errors=="") fertilizerDao.save(fertilizer);
        else errors = "Server Validation Errors : <br>"+errors;

        responce.put("id",String.valueOf(fertilizer.getId()));
        responce.put("url","/fertilizers/"+fertilizer.getId());
        responce.put("errors",errors);

        return responce;

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id) {

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        Fertilizer fertilizer = fertilizerDao.findByMyId(id);

        if(fertilizer==null)
            errors = errors+"<br> Fertilizer Does Not Existed.";

        if(errors=="") fertilizerDao.delete(fertilizer);
        else errors = "Server Validation Errors : <br>"+errors;

        responce.put("id",String.valueOf(id));
        responce.put("url","/fertilizers/"+id);
        responce.put("errors",errors);

        return responce;

    }


}
