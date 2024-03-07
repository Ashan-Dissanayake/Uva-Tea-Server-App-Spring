package lk.uva.uvateafactory.controller;

import lk.uva.uvateafactory.dao.DistributorDao;
import lk.uva.uvateafactory.dao.EmployeeDao;
import lk.uva.uvateafactory.entity.Distributor;
import lk.uva.uvateafactory.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/distributors")
public class DistributorController {

    @Autowired
    private DistributorDao distributorDao;

    @GetMapping(produces = "application/json")
    public List<Distributor> get(@RequestParam HashMap<String, String> params){

        String name = params.get("name");
        String email = params.get("email");
        String distributortypeid = params.get("distributortypeid");

        List<Distributor> exporters  = this.distributorDao.findAll();
        if (params.isEmpty()) return exporters;

        Stream<Distributor> estream = exporters.stream();

        if (name!=null) estream = estream.filter(e -> e.getName().contains(name));
        if (email!=null) estream = estream.filter(e -> e.getEmail().contains(email));
        if (distributortypeid!=null) estream = estream.filter(e -> e.getDistributortype().getId()==Integer.parseInt(distributortypeid));

        return estream.collect(Collectors.toList());

    }

    @GetMapping(path ="/list",produces = "application/json")
    public List<Distributor> get() {

        List<Distributor> distributors = this.distributorDao.findAllNameID();

        distributors = distributors.stream().map(
                distributor -> {
                    Distributor distri = new Distributor(distributor.getId(),distributor.getName());
                    return distri;
                }
        ).collect(Collectors.toList());

        return distributors;

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> add(@RequestBody Distributor distributor){

        HashMap<String,String> response = new HashMap<>();
        String errors ="";

        if (distributorDao.findByEmail(distributor.getEmail())!=null)
            errors = errors+"<br> Existing Email";

        if (errors=="")
            distributorDao.save(distributor);
        else errors = "Server Validation Errors : <br> "+ errors;

        response.put("id",String.valueOf(distributor.getId()));
        response.put("url","/distributors/"+distributor.getId());
        response.put("errors",errors);

        return response;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> update(@RequestBody Distributor distributor){
        HashMap<String, String> response = new HashMap<>();

        String errors="";

        Distributor distri = distributorDao.findByEmail(distributor.getEmail());

        if (distri!=null && distributor.getId()!=distri.getId())
            errors = errors + "<br> Existing Email";

        if (errors=="") distributorDao.save(distributor);
        else errors = "Server Validation Errors : <br> "+ errors;

        response.put("id",String.valueOf(distributor.getId()));
        response.put("url","/distributors/"+distributor.getId());
        response.put("errors",errors);

        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id){

        HashMap<String,String> response = new HashMap<>();

        String errors="";

        Distributor dis = distributorDao.findByMyId(id);
        if(dis==null)
            errors = errors+"<br> Distributor Does Not Exist";
        if(errors=="") distributorDao.delete(dis);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id",String.valueOf(id));
        response.put("url","/distributors/"+id);
        response.put("errors",errors);

        return response;
    }

}
