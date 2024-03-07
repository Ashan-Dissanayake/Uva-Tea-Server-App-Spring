package lk.uva.uvateafactory.controller;

import lk.uva.uvateafactory.dao.AttendenceDao;
import lk.uva.uvateafactory.entity.Attendence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/attendences")
public class AttendenceController {

    @Autowired
    private AttendenceDao attendenceDao;

    @GetMapping(produces = "application/json")
    public List<Attendence> get(@RequestParam HashMap<String,String> params) {

        String date = params.get("date");
        String employeeid = params.get("employeeid");
        String attndstatusid = params.get("attndstatusid");

        List<Attendence> attendences = this.attendenceDao.findAll();

        if(params.isEmpty()) return attendences;

        Stream<Attendence> astream = attendences.stream();

        if(date!=null) astream = astream.filter(e -> e.getDate().toString().equals(date));
        if(employeeid!=null) astream = astream.filter(e -> e.getEmployee().getId()==Integer.parseInt(employeeid));
        if(attndstatusid!=null) astream = astream.filter(e -> e.getAttendstatus().getId()==Integer.parseInt(attndstatusid));

        return astream.collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> add(@RequestBody Attendence attendence) {

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        if(errors=="") attendenceDao.save(attendence);
        else errors = "Server Validation Errors : <br>"+errors;

        responce.put("id",String.valueOf(attendence.getId()));
        responce.put("url","/attendences/"+attendence.getId());
        responce.put("errors",errors);

        return responce;

    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> update(@RequestBody Attendence attendence) {

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        if(errors=="") attendenceDao.save(attendence);
        else errors = "Server Validation Errors : <br>"+errors;

        responce.put("id",String.valueOf(attendence.getId()));
        responce.put("url","/attendences/"+attendence.getId());
        responce.put("errors",errors);

        return responce;

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id) {

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        Attendence attendence = attendenceDao.findByMyId(id);

        if(attendence==null)
            errors = errors+"<br> Attendence Does Not Existed.";

        if(errors=="") attendenceDao.delete(attendence);
        else errors = "Server Validation Errors : <br>"+errors;

        responce.put("id",String.valueOf(id));
        responce.put("url","/attendences/"+id);
        responce.put("errors",errors);

        return responce;

    }


}
