package lk.uva.uvateafactory.controller;

import lk.uva.uvateafactory.dao.TransportpurposeDao;
import lk.uva.uvateafactory.entity.Transportpurpose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/transportpurposes")
public class TransportpurposeController {

    @Autowired
    private TransportpurposeDao transportpurposeDao;

    @GetMapping( path = "/list", produces = "application/json")
    public List<Transportpurpose> get() {

        List<Transportpurpose> transportpurposes = this.transportpurposeDao.findAll();

        transportpurposes = transportpurposes.stream().map(
                transportpurpose -> { Transportpurpose tp = new Transportpurpose();
                    tp.setId(transportpurpose.getId());
                    tp.setName(transportpurpose.getName());
                    return tp; }
        ).collect(Collectors.toList());

        return transportpurposes;
    }

}
