package lk.uva.uvateafactory.controller;

import lk.uva.uvateafactory.dao.VehiclestatusDao;
import lk.uva.uvateafactory.entity.Vehiclestatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/vehiclestatuss")
public class VehiclestatusController {

    @Autowired
    private VehiclestatusDao vehiclestatusDao;

    @GetMapping( path = "/list", produces = "application/json")
    public List<Vehiclestatus> get() {

        List<Vehiclestatus> vehiclestatuss = this.vehiclestatusDao.findAll();

        vehiclestatuss = vehiclestatuss.stream().map(
                vehiclestatus -> { Vehiclestatus vs = new Vehiclestatus();
                    vs.setId(vehiclestatus.getId());
                    vs.setName(vehiclestatus.getName());
                    return vs; }
        ).collect(Collectors.toList());

        return vehiclestatuss;
    }

}
