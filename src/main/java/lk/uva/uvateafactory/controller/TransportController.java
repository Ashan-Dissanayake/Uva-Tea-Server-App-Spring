package lk.uva.uvateafactory.controller;


import lk.uva.uvateafactory.dao.TransportDao;
import lk.uva.uvateafactory.entity.Transport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/transports")
public class TransportController {

    @Autowired
    private TransportDao transportDao;

    @GetMapping(produces = "application/json")
    public List<Transport> get(@RequestParam HashMap<String,String> params) {

        String number = params.get("number");
        String driver = params.get("driver");
        String date = params.get("date");
        String rootid = params.get("rootid");
        String transpurposeid = params.get("transpurposeid");

        List<Transport> transports = this.transportDao.findAll();

        if(params.isEmpty()) return transports;

        Stream<Transport> tstream = transports.stream();

        if(number!=null) tstream = tstream.filter(e -> e.getVehicle().getNumber().contains(number));
        if(driver!=null) tstream = tstream.filter(e -> e.getDriver().getCallingname().contains(driver));
        if(date!=null) tstream = tstream.filter(e -> e.getDate().toString().equals(date));
        if(rootid!=null) tstream = tstream.filter(e -> e.getRoot().getId()==Integer.parseInt(rootid));
        if(transpurposeid!=null) tstream = tstream.filter(e -> e.getTransportpurpose().getId()==Integer.parseInt(transpurposeid));

        return tstream.collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> add(@RequestBody Transport transport) {

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        if(errors=="") transportDao.save(transport);
        else errors = "Server Validation Errors : <br>"+errors;

        responce.put("id",String.valueOf(transport.getId()));
        responce.put("url","/transports/"+transport.getId());
        responce.put("errors",errors);

        return responce;

    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> update(@RequestBody Transport transport) {

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        if(errors=="") transportDao.save(transport);
        else errors = "Server Validation Errors : <br>"+errors;

        responce.put("id",String.valueOf(transport.getId()));
        responce.put("url","/transports/"+transport.getId());
        responce.put("errors",errors);

        return responce;

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id) {

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        Transport transport = transportDao.findByMyId(id);

        if(transport==null)
            errors = errors+"<br> Transport Does Not Existed.";

        if(errors=="") transportDao.delete(transport);
        else errors = "Server Validation Errors : <br>"+errors;

        responce.put("id",String.valueOf(id));
        responce.put("url","/transports/"+id);
        responce.put("errors",errors);

        return responce;

    }



}
