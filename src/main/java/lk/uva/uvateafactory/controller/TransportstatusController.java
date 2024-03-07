package lk.uva.uvateafactory.controller;

import lk.uva.uvateafactory.dao.TransportstatusDao;
import lk.uva.uvateafactory.entity.Transportstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/transportstatuss")
public class TransportstatusController {

    @Autowired
    private TransportstatusDao transportstatusDao;

    @GetMapping( path = "/list", produces = "application/json")
    public List<Transportstatus> get() {

        List<Transportstatus> transportstatuss = this.transportstatusDao.findAll();

        transportstatuss = transportstatuss.stream().map(
                transportstatus -> { Transportstatus ts = new Transportstatus();
                    ts.setId(transportstatus.getId());
                    ts.setName(transportstatus.getName());
                    return ts; }
        ).collect(Collectors.toList());

        return transportstatuss;
    }

}
