package lk.uva.uvateafactory.controller;

import lk.uva.uvateafactory.dao.FueltypeDao;
import lk.uva.uvateafactory.entity.Fueltype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/fueltypes")
public class FueltypeController {

    @Autowired
    private FueltypeDao fueltypeDao;

    @GetMapping( path = "/list", produces = "application/json")
    public List<Fueltype> get() {

        List<Fueltype> fueltypes = this.fueltypeDao.findAll();

        fueltypes = fueltypes.stream().map(
                fueltype -> { Fueltype ft = new Fueltype();
                    ft.setId(fueltype.getId());
                    ft.setName(fueltype.getName());
                    return ft; }
        ).collect(Collectors.toList());

        return fueltypes;
    }

}
