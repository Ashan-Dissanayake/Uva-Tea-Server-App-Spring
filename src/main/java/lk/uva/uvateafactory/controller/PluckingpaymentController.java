package lk.uva.uvateafactory.controller;

import lk.uva.uvateafactory.dao.PluckingpaymentDao;
import lk.uva.uvateafactory.entity.Pluckingpayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/pluckingpayments")
public class PluckingpaymentController {

    @Autowired
    private PluckingpaymentDao pluckingpaymentDao;

    @GetMapping(produces = "application/json")
    public List<Pluckingpayment> get(@RequestParam HashMap<String,String> params) {

        String pluckerid = params.get("pluckerid");
        String issuerid = params.get("issuerid");
        String dopayment = params.get("dopayment");

        List<Pluckingpayment> pluckingpayments = this.pluckingpaymentDao.findAll();

        if(params.isEmpty()) return pluckingpayments;

        Stream<Pluckingpayment> ppstream = pluckingpayments.stream();

        if(pluckerid!=null) ppstream = ppstream.filter(e -> e.getPlucker().getId()==Integer.parseInt(pluckerid));
        if(issuerid!=null) ppstream = ppstream.filter(e -> e.getIssuer().getId()==Integer.parseInt(issuerid));
        if(dopayment!=null) ppstream = ppstream.filter(e -> e.getDopayment().toString().equals(dopayment));

        return ppstream.collect(Collectors.toList());
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> add(@RequestBody Pluckingpayment pluckingpayment) {

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        if(errors=="") pluckingpaymentDao.save(pluckingpayment);
        else errors = "Server Validation Errors : <br>"+errors;

        responce.put("id",String.valueOf(pluckingpayment.getId()));
        responce.put("url","/pluckingpayments/"+pluckingpayment.getId());
        responce.put("errors",errors);

        return responce;

    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> update(@RequestBody Pluckingpayment pluckingpayment) {

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        if(errors=="") pluckingpaymentDao.save(pluckingpayment);
        else errors = "Server Validation Errors : <br>"+errors;

        responce.put("id",String.valueOf(pluckingpayment.getId()));
        responce.put("url","/pluckingpayments/"+pluckingpayment.getId());
        responce.put("errors",errors);

        return responce;

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id) {

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        Pluckingpayment pp1 = pluckingpaymentDao.findByMyId(id);

        if(pp1==null)
            errors = errors+"<br> Plucking Payment Does Not Existed.";

        if(errors=="") pluckingpaymentDao.delete(pp1);
        else errors = "Server Validation Errors : <br>"+errors;

        responce.put("id",String.valueOf(id));
        responce.put("url","/pluckingpayments/"+id);
        responce.put("errors",errors);

        return responce;

    }


}
