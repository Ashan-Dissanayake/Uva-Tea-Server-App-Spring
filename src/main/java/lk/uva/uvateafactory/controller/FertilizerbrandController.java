package lk.uva.uvateafactory.controller;

import lk.uva.uvateafactory.dao.FertilizerbrandDao;
import lk.uva.uvateafactory.entity.Fertilzerbrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/fertilizerbrands")
public class FertilizerbrandController {

    @Autowired
    private FertilizerbrandDao fertilizerbrandDao;

    @GetMapping( path = "/list", produces = "application/json")
    public List<Fertilzerbrand> get() {

        List<Fertilzerbrand> fertilizerbrands = this.fertilizerbrandDao.findAll();

        fertilizerbrands = fertilizerbrands.stream().map(
                fertilizerbrand -> { Fertilzerbrand fb = new Fertilzerbrand();
                    fb.setId(fertilizerbrand.getId());
                    fb.setName(fertilizerbrand.getName());
                    return fb; }
        ).collect(Collectors.toList());

        return fertilizerbrands;
    }

}
