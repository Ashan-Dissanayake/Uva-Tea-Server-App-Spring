package lk.uva.uvateafactory.controller;

import lk.uva.uvateafactory.dao.ActivityDao;
import lk.uva.uvateafactory.entity.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/activities")
public class ActivityController {

    @Autowired
    private ActivityDao activityDao;

    @GetMapping(produces = "application/json")
    public List<Activity> get(@RequestParam HashMap<String,String> params) {

        String date = params.get("date");
        String approverid = params.get("approverid");
        String ssactivitytypeid = params.get("ssactivitytypeid");

        List<Activity> activities = this.activityDao.findAll();

        if(params.isEmpty()) return activities;

        Stream<Activity> actistream = activities.stream();

        if(date!=null) actistream = actistream.filter(e -> e.getDate().toString().equals(date));
        if(approverid!=null) actistream = actistream.filter(e -> e.getApprover().getId()==Integer.parseInt(approverid));
        if(ssactivitytypeid!=null) actistream = actistream.filter(e -> e.getActivitytype().getId()==Integer.parseInt(ssactivitytypeid));

        return actistream.collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> add(@RequestBody Activity activity) {

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        if(errors=="") activityDao.save(activity);
        else errors = "Server Validation Errors : <br>"+errors;

        responce.put("id",String.valueOf(activity.getId()));
        responce.put("url","/activities/"+activity.getId());
        responce.put("errors",errors);

        return responce;

    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> update(@RequestBody Activity activity) {

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        if(errors=="") activityDao.save(activity);
        else errors = "Server Validation Errors : <br>"+errors;

        responce.put("id",String.valueOf(activity.getId()));
        responce.put("url","/activities/"+activity.getId());
        responce.put("errors",errors);

        return responce;

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id) {

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        Activity activity = activityDao.findByMyId(id);

        if(activity==null)
            errors = errors+"<br> Activity Does Not Existed.";

        if(errors=="") activityDao.delete(activity);
        else errors = "Server Validation Errors : <br>"+errors;

        responce.put("id",String.valueOf(id));
        responce.put("url","/activities/"+id);
        responce.put("errors",errors);

        return responce;

    }

    @GetMapping(path = "/currentfutureactivity",produces = "application/json")
    public List<Activity> getEvent(){
        return activityDao.upComingEvents();

    }



}
