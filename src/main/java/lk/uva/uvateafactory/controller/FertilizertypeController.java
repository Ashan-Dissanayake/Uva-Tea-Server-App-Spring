package lk.uva.uvateafactory.controller;

import lk.uva.uvateafactory.dao.FertilizertypeDao;
import lk.uva.uvateafactory.entity.Fertilizertype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/fertilizertypes")
public class FertilizertypeController {

    @Autowired
    private FertilizertypeDao fertilizertypeDao;

    @GetMapping( path = "/list", produces = "application/json")
    public List<Fertilizertype> get() {

        List<Fertilizertype> fertilizertypes = this.fertilizertypeDao.findAll();

        fertilizertypes = fertilizertypes.stream().map(
                fertilizertype -> { Fertilizertype ft = new Fertilizertype();
                    ft.setId(fertilizertype.getId());
                    ft.setName(fertilizertype.getName());
                    return ft; }
        ).collect(Collectors.toList());

        return fertilizertypes;
    }

}
