package lk.uva.uvateafactory.controller;


import lk.uva.uvateafactory.dao.PorderstatusDao;
import lk.uva.uvateafactory.entity.Porderstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/porderstatuss")
public class PorderstatusController {

    @Autowired
    private PorderstatusDao porderstatusDao;

    @GetMapping( path = "/list", produces = "application/json")
    public List<Porderstatus> get() {

        List<Porderstatus> porderstatuses = this.porderstatusDao.findAll();

        porderstatuses = porderstatuses.stream().map(
                porderstatus -> { Porderstatus g = new Porderstatus();
                    g.setId(porderstatus.getId());
                    g.setName(porderstatus.getName());
                    return g; }
        ).collect(Collectors.toList());

        return porderstatuses;
    }

}
