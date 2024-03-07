package lk.uva.uvateafactory.controller;

import lk.uva.uvateafactory.dao.VehicletypeDao;
import lk.uva.uvateafactory.entity.Vehicletype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/vehicletypes")
public class VehiclestypeController {

    @Autowired
    private VehicletypeDao vehicletypeDao;

    @GetMapping( path = "/list", produces = "application/json")
    public List<Vehicletype> get() {

        List<Vehicletype> vehicletypes = this.vehicletypeDao.findAll();

        vehicletypes = vehicletypes.stream().map(
                vehicletype -> { Vehicletype vt = new Vehicletype();
                    vt.setId(vehicletype.getId());
                    vt.setName(vehicletype.getName());
                    return vt; }
        ).collect(Collectors.toList());

        return vehicletypes;
    }

}
