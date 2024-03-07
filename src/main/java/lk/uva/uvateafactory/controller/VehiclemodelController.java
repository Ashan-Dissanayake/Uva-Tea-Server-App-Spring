package lk.uva.uvateafactory.controller;

import lk.uva.uvateafactory.dao.VehiclemodelDao;
import lk.uva.uvateafactory.entity.Vehiclemodel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/vehiclemodels")
public class VehiclemodelController {

    @Autowired
    private VehiclemodelDao vehiclemodelDao;

    @GetMapping( path = "/list", produces = "application/json")
    public List<Vehiclemodel> get() {

        List<Vehiclemodel> vehiclemodels = this.vehiclemodelDao.findAll();

        vehiclemodels = vehiclemodels.stream().map(
                vehiclemodel -> { Vehiclemodel vm = new Vehiclemodel();
                    vm.setId(vehiclemodel.getId());
                    vm.setName(vehiclemodel.getName());
                    vm.setVehiclebrand(vehiclemodel.getVehiclebrand());
                    return vm; }
        ).collect(Collectors.toList());

        return vehiclemodels;
    }

    @GetMapping(value = "/{id}",produces ="application/json")
    public List<Vehiclemodel> getModels(@PathVariable Integer id) {

        List<Vehiclemodel> vehiclemodels = vehiclemodelDao.findByGivenId(id);
        return vehiclemodels;

    }

}
