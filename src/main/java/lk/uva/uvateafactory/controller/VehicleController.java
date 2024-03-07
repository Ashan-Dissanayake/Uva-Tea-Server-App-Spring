package lk.uva.uvateafactory.controller;

import lk.uva.uvateafactory.dao.VehicleDao;
import lk.uva.uvateafactory.entity.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/vehicles")
public class VehicleController {

    @Autowired
    private VehicleDao vehicleDao;

    @GetMapping(produces = "application/json")
    public List<Vehicle> get(@RequestParam HashMap<String,String> params) {

        String number = params.get("number");
        String vehiclestatusid = params.get("vehiclestatusid");
        String vehicletypeid = params.get("vehicletypeid");
        String vehiclebrandid = params.get("vehiclebrandid");

        List<Vehicle> vehicles = this.vehicleDao.findAll();

        if(params.isEmpty()) return vehicles;

        Stream<Vehicle> vstream = vehicles.stream();

        if(number!=null) vstream = vstream.filter(e -> e.getNumber().contains(number));
        if(vehiclestatusid!=null) vstream = vstream.filter(e -> e.getVehiclestatus().getId()==Integer.parseInt(vehiclestatusid));
        if(vehicletypeid!=null) vstream = vstream.filter(e -> e.getVehicletype().getId()==Integer.parseInt(vehicletypeid));
        if(vehiclebrandid!=null) vstream = vstream.filter(e -> e.getVehiclemodel().getVehiclebrand().getId()==Integer.parseInt(vehiclebrandid));

        return vstream.collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> add(@RequestBody Vehicle vehicle) {

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        if(vehicleDao.findByNumber(vehicle.getNumber()) != null)
            errors = errors+"<br> Existing Number";

        if(errors=="") vehicleDao.save(vehicle);
        else errors = "Server Validation Errors : <br>"+errors;

        responce.put("id",String.valueOf(vehicle.getId()));
        responce.put("url","/vehicles/"+vehicle.getId());
        responce.put("errors",errors);

        return responce;

    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> update(@RequestBody Vehicle vehicle) {

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        Vehicle vehicle1 = vehicleDao.findByNumber(vehicle.getNumber());

        if(vehicle1!=null && vehicle1.getId()!=vehicle.getId())
            errors = errors+"<br> Existing Vehicle Number";

        if(errors=="") vehicleDao.save(vehicle);
        else errors = "Server Validation Errors : <br> "+errors;

        responce.put("id",String.valueOf(vehicle.getId()));
        responce.put("url","/vehicles/"+vehicle.getId());
        responce.put("errors",errors);

        return responce;

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id) {

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        Vehicle vehicle = vehicleDao.findByMyId(id);

        if(vehicle==null)
            errors = errors+"<br> Vehicle Does Not Existed.";

        if(errors=="") vehicleDao.delete(vehicle);
        else errors = "Server Validation Errors : <br>"+errors;

        responce.put("id",String.valueOf(id));
        responce.put("url","/vehicles/"+id);
        responce.put("errors",errors);

        return responce;

    }



}
