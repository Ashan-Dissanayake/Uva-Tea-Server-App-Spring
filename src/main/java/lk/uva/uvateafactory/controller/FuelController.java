package lk.uva.uvateafactory.controller;


import lk.uva.uvateafactory.dao.FuelDao;
import lk.uva.uvateafactory.entity.Fuel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/fuels")
public class FuelController {

    @Autowired
    private FuelDao fuelDao;

    @GetMapping(produces = "application/json")
    public List<Fuel> get(@RequestParam HashMap<String,String> params) {

        String vehiclenumber = params.get("vehiclenumber");
        String fueltypeid = params.get("fueltypeid");
        String driverid = params.get("driverid");
        String fuelstationid = params.get("fuelstationid");
        String date = params.get("date");

        List<Fuel> fuels = this.fuelDao.findAll();

        if(params.isEmpty()) return fuels;

        Stream<Fuel> fuelstream = fuels.stream();

        if(vehiclenumber!=null) fuelstream = fuelstream.filter(fs -> fs.getVehicle().getNumber().contains(vehiclenumber));
        if(fueltypeid!=null) fuelstream = fuelstream.filter(fs -> fs.getFueltype().getId()==Integer.parseInt(fueltypeid));
        if(driverid!=null) fuelstream = fuelstream.filter(fs -> fs.getDriveronduty().getId()==Integer.parseInt(driverid));
        if(fueltypeid!=null) fuelstream = fuelstream.filter(fs -> fs.getFueltype().getId()==Integer.parseInt(fueltypeid));
        if(fuelstationid!=null) fuelstream = fuelstream.filter(fs -> fs.getFuelstation().getId()==Integer.parseInt(fuelstationid));
        if(date!=null) fuelstream = fuelstream.filter(fs -> fs.getDate().toString().equals(date));

        return fuelstream.collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> add(@RequestBody Fuel fuel) {

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        System.out.println("Name: "+fuel.getDriveronduty().getCallingname());

        if(errors=="") fuelDao.save(fuel);
        else errors = "Server Validation Errors : <br>"+errors;

        responce.put("id",String.valueOf(fuel.getId()));
        responce.put("url","/fuels/"+fuel.getId());
        responce.put("errors",errors);

        return responce;

    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> update(@RequestBody Fuel fuel) {

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        if(errors=="") fuelDao.save(fuel);
        else errors = "Server Validation Errors : <br>"+errors;

        responce.put("id",String.valueOf(fuel.getId()));
        responce.put("url","/fuels/"+fuel.getId());
        responce.put("errors",errors);

        return responce;

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id) {

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        Fuel fuel = fuelDao.findByMyId(id);

        if(fuel==null)
            errors = errors+"<br> Fuel Does Not Existed.";

        if(errors=="") fuelDao.delete(fuel);
        else errors = "Server Validation Errors : <br>"+errors;

        responce.put("id",String.valueOf(id));
        responce.put("url","/fuels/"+id);
        responce.put("errors",errors);

        return responce;

    }

}
