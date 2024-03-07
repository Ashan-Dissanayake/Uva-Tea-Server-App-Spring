package lk.uva.uvateafactory.controller;

import lk.uva.uvateafactory.dao.FuelstationDao;
import lk.uva.uvateafactory.entity.Fuelstation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/fuelstations")
public class FuelstationController {

    @Autowired
    private FuelstationDao fuelstationDao;

    @GetMapping( path = "/list", produces = "application/json")
    public List<Fuelstation> get() {

        List<Fuelstation> fuelstations = this.fuelstationDao.findAll();

        fuelstations = fuelstations.stream().map(
                fuelstation -> { Fuelstation fst = new Fuelstation();
                    fst.setId(fuelstation.getId());
                    fst.setName(fuelstation.getName());
                    return fst; }
        ).collect(Collectors.toList());

        return fuelstations;
    }

}
