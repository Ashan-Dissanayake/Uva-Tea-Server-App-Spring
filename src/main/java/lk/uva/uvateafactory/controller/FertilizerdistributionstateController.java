package lk.uva.uvateafactory.controller;

import lk.uva.uvateafactory.dao.FertilizerdistributionstateDao;
import lk.uva.uvateafactory.entity.Ferdistributionstate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/fertilizerdistributionstates")
public class FertilizerdistributionstateController {

    @Autowired
    private FertilizerdistributionstateDao fertilizerdistributionstateDao;

    @GetMapping( path = "/list", produces = "application/json")
    public List<Ferdistributionstate> get() {

        List<Ferdistributionstate> fertilizerdistributionstates = this.fertilizerdistributionstateDao.findAll();

        fertilizerdistributionstates = fertilizerdistributionstates.stream().map(
                fertilizerdistribution -> { Ferdistributionstate ferdisstate = new Ferdistributionstate();
                    ferdisstate.setId(fertilizerdistribution.getId());
                    ferdisstate.setName(fertilizerdistribution.getName());
                    return ferdisstate; }
        ).collect(Collectors.toList());

        return fertilizerdistributionstates;
    }

}
