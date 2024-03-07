package lk.uva.uvateafactory.controller;

import lk.uva.uvateafactory.dao.AttendencestatusDao;
import lk.uva.uvateafactory.entity.Attendstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/attendencestatuss")
public class AttendencestatusController {

    @Autowired
    private AttendencestatusDao attendencestatusDao;

    @GetMapping( path = "/list", produces = "application/json")
    public List<Attendstatus> get() {

        List<Attendstatus> attendencestatuss = this.attendencestatusDao.findAll();

        attendencestatuss = attendencestatuss.stream().map(
                attendencestatus -> { Attendstatus as = new Attendstatus();
                    as.setId(attendencestatus.getId());
                    as.setName(attendencestatus.getName());
                    return as; }
        ).collect(Collectors.toList());

        return attendencestatuss;
    }

}
