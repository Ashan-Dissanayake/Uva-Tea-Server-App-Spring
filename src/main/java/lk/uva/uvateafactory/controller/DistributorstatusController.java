package lk.uva.uvateafactory.controller;


import lk.uva.uvateafactory.dao.DistributorstatusDao;
import lk.uva.uvateafactory.entity.Distributorstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/distributorstatuss")
public class DistributorstatusController {

    @Autowired
    private DistributorstatusDao distributorstatusDao;

    @GetMapping( path = "/list", produces = "application/json")
    public List<Distributorstatus> get() {

        List<Distributorstatus> distributorstatuss = this.distributorstatusDao.findAll();

        distributorstatuss = distributorstatuss.stream().map(
                distributorstatus -> { Distributorstatus g = new Distributorstatus();
                    g.setId(distributorstatus.getId());
                    g.setName(distributorstatus.getName());
                    return g; }
        ).collect(Collectors.toList());

        return distributorstatuss;
    }

}
