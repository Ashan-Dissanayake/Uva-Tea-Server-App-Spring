package lk.uva.uvateafactory.controller;

import lk.uva.uvateafactory.dao.PluckingDao;
import lk.uva.uvateafactory.entity.Plucking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/pluckings")
public class PluckingController {

    @Autowired
    private PluckingDao pluckingDao;

    @GetMapping(produces = "application/json")
    public List<Plucking> get(@RequestParam HashMap<String,String> params) {

        String areaid = params.get("areaid");
        String pluckerid = params.get("pluckerid");
        String sessionid = params.get("sessionid");
        String leaftypeid = params.get("leaftypeid");
        String kankaniid = params.get("kankaniid");
        String date = params.get("date");

//        System.out.println(date);

        List<Plucking> pluckings = this.pluckingDao.findAll();

        if(params.isEmpty()) return pluckings;

        Stream<Plucking> pluckingStream = pluckings.stream();

        if(areaid!=null) pluckingStream = pluckingStream.filter(plucking -> plucking.getArea().getId()== Integer.parseInt(areaid));
        if(pluckerid!=null) pluckingStream = pluckingStream.filter(plucking -> plucking.getEmpplucker().getId()== Integer.parseInt(pluckerid));
        if(sessionid!=null) pluckingStream = pluckingStream.filter(plucking -> plucking.getPluckingseesion().getId()== Integer.parseInt(sessionid));
        if(leaftypeid!=null) pluckingStream = pluckingStream.filter(plucking -> plucking.getLeaftype().getId()== Integer.parseInt(leaftypeid));
        if(kankaniid!=null) pluckingStream = pluckingStream.filter(plucking -> plucking.getEmpkankani().getId()== Integer.parseInt(kankaniid));
        if(date!=null) pluckingStream = pluckingStream.filter(plucking -> plucking.getDate().toString().equals(date));

        return pluckingStream.collect(Collectors.toList());

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> add(@RequestBody Plucking plucking) {

//        System.out.println("Checkk"+plucking);
        Integer bonusMark =5;

        Integer bonusValue = plucking.getQty() - bonusMark;

        if(bonusValue < 0) { plucking.setBonus(0); }
        else { plucking.setBonus(bonusValue); }

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        if(errors=="") pluckingDao.save(plucking);

        responce.put("id",String.valueOf(plucking.getId()));
        responce.put("url","/pluckings/"+plucking.getId());
        responce.put("errors",errors);

        return responce;

    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> update(@RequestBody Plucking plucking) {

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        Integer bonusMark =5;

        Integer bonusValue = plucking.getQty() - bonusMark;

        if(bonusValue < 0) { plucking.setBonus(0); }
        else { plucking.setBonus(bonusValue); }

        if(errors=="") pluckingDao.save(plucking);
        else errors = "Server Validation Errors : <br>"+errors;

        responce.put("id",String.valueOf(plucking.getId()));
        responce.put("url","/pluckings/"+plucking.getId());
        responce.put("errors",errors);

        return responce;

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id) {

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        Plucking plucking = pluckingDao.findByMyId(id);

        if(plucking==null)
            errors = errors+"<br> Plucking Does Not Existed.";

        if(errors=="") pluckingDao.delete(plucking);
        else errors = "Server Validation Errors : <br>"+errors;

        responce.put("id",String.valueOf(id));
        responce.put("url","/employees/"+id);
        responce.put("errors",errors);

        return responce;

    }

//    @GetMapping(path = "/pluckerbonus/{id}", produces = "application/json")
//    public Plucking getPluckerBonus(@PathVariable Integer id) {
//
//       return pluckingDao.getPluckerPaymntBonus(id);
//    }

    @GetMapping(path = "/pluckerbonus", produces = "application/json")
    public Plucking getPluckerBonus(@RequestParam HashMap<String,String> params) {

        String pluckerid = params.get("pluckerid");
        String dostart = params.get("dostart");
        String doend = params.get("doend");

        Date startdate = Date.valueOf(dostart);
        Date enddate = Date.valueOf(doend);

        return pluckingDao.getPluckerPaymntBonus(Integer.valueOf(pluckerid),startdate,enddate);

    }


}
