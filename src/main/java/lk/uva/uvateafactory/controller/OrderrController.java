package lk.uva.uvateafactory.controller;


import lk.uva.uvateafactory.dao.OrderrDao;
import lk.uva.uvateafactory.dao.ProductionDao;
import lk.uva.uvateafactory.entity.Orderr;
import lk.uva.uvateafactory.entity.Orderrproduct;
import lk.uva.uvateafactory.entity.Production;
import lk.uva.uvateafactory.entity.Productionproduct;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/orderrs")
public class OrderrController {

    @Autowired
    private OrderrDao orderrDao;

    @GetMapping(produces = "application/json")
    public List<Orderr> get(@RequestParam HashMap<String,String> params) {

        String date = params.get("date");
        String name = params.get("name");
        String distributorid = params.get("distributorid");
        String statusid = params.get("statusid");

        List<Orderr> orderrs = this.orderrDao.findAll();

        if(params.isEmpty()) return orderrs;

        Stream<Orderr> orderstrm = orderrs.stream();

        if(date!=null) orderstrm = orderstrm.filter(e -> e. getDoorder().toString().equals(date));
        if(name!=null) orderstrm = orderstrm.filter(e -> e. getName().contains(name));
        if(distributorid!=null) orderstrm = orderstrm.filter(e -> e. getDistributor().getId()==Integer.parseInt(distributorid));
        if(statusid!=null) orderstrm = orderstrm.filter(e -> e. getOrderstatus().getId()==Integer.parseInt(statusid));

        return orderstrm.collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> add(@RequestBody Orderr orderr) {

        HashMap<String, String> response = new HashMap<>();
        String errors = "";

        for (Orderrproduct orproduct : orderr.getOrderrproducts()) orproduct.setOrderr(orderr);

        orderrDao.save(orderr);

        response.put("id", String.valueOf(orderr.getId()));
        response.put("url", "/orderrs/" + orderr.getId());
        response.put("errors", errors);

        return response;

    }


    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> update(@RequestBody Orderr orderr) {

        HashMap<String, String> response = new HashMap<>();
        String errors = "";


        Orderr extOrderr = orderrDao.findByMyId(orderr.getId());


        if (extOrderr != null) {

            try {
                extOrderr.getOrderrproducts().clear();
                orderr.getOrderrproducts().forEach(orderprodct -> {
                    orderprodct.setOrderr(extOrderr);
                    extOrderr.getOrderrproducts().add(orderprodct);
                    orderprodct.setOrderr(extOrderr);
                });

                BeanUtils.copyProperties(orderr, extOrderr, "id","orderrproducts");

                orderrDao.save(extOrderr); // Save the updated extProduction object

                response.put("id", String.valueOf(orderr.getId()));
                response.put("url", "/orderrs/" + orderr.getId());
                response.put("errors", errors);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id) {

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        Orderr orderr = orderrDao.findByMyId(id);

        if(orderr==null)
            errors = errors+"<br> Order Does Not Existed.";

        if(errors=="") orderrDao.delete(orderr);
        else errors = "Server Validation Errors : <br>"+errors;

        responce.put("id",String.valueOf(id));
        responce.put("url","/orderrs/"+id);
        responce.put("errors",errors);

        return responce;

    }

}
