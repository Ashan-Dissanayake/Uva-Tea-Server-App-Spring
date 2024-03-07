package lk.uva.uvateafactory.controller;


import lk.uva.uvateafactory.dao.DistributortypeDao;
import lk.uva.uvateafactory.entity.Distributortype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/distributortypes")
public class DistributortypeController {

    @Autowired
    private DistributortypeDao distributortypeDao;

    @GetMapping( path = "/list", produces = "application/json")
    public List<Distributortype> get() {

        List<Distributortype> distributortypes = this.distributortypeDao.findAll();

        distributortypes = distributortypes.stream().map(
                distributortype -> { Distributortype g = new Distributortype();
                    g.setId(distributortype.getId());
                    g.setName(distributortype.getName());
                    return g; }
        ).collect(Collectors.toList());

        return distributortypes;
    }

}
