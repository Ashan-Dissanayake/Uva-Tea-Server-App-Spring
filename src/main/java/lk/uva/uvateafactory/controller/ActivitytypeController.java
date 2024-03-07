package lk.uva.uvateafactory.controller;


import lk.uva.uvateafactory.dao.ActivitytypeDao;
import lk.uva.uvateafactory.entity.Activitytype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/activitytypes")
public class ActivitytypeController {

    @Autowired
    private ActivitytypeDao activitytypeDao;

    @GetMapping( path = "/list", produces = "application/json")
    public List<Activitytype> get() {

        List<Activitytype> activitytypes = this.activitytypeDao.findAll();

        activitytypes = activitytypes.stream().map(
                activitytype -> { Activitytype at = new Activitytype();
                    at.setId(activitytype.getId());
                    at.setName(activitytype.getName());
                    return at; }
        ).collect(Collectors.toList());

        return activitytypes;
    }

}
