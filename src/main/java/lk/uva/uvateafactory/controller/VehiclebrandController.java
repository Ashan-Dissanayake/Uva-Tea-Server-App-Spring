package lk.uva.uvateafactory.controller;

import lk.uva.uvateafactory.dao.VehiclebrandDao;
import lk.uva.uvateafactory.entity.Vehiclebrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/vehiclebrands")
public class VehiclebrandController {

    @Autowired
    private VehiclebrandDao vehiclebrandDao;

    @GetMapping( path = "/list", produces = "application/json")
    public List<Vehiclebrand> get() {

        List<Vehiclebrand> vehiclebrands = this.vehiclebrandDao.findAll();

        vehiclebrands = vehiclebrands.stream().map(
                vehiclebrand -> { Vehiclebrand vb = new Vehiclebrand();
                    vb.setId(vehiclebrand.getId());
                    vb.setName(vehiclebrand.getName());
                    return vb; }
        ).collect(Collectors.toList());

        return vehiclebrands;
    }

}
