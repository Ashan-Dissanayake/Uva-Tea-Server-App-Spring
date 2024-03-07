package lk.uva.uvateafactory.controller;

import lk.uva.uvateafactory.dao.FertilizerstatusDao;
import lk.uva.uvateafactory.entity.Fertilizerstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/fertilizerstatuss")
public class FertilizerstatusController {

    @Autowired
    private FertilizerstatusDao fertilizerstatusDao;

    @GetMapping( path = "/list", produces = "application/json")
    public List<Fertilizerstatus> get() {

        List<Fertilizerstatus> fertilizerstatuss = this.fertilizerstatusDao.findAll();

        fertilizerstatuss = fertilizerstatuss.stream().map(
                fertilizerstatus -> { Fertilizerstatus fs = new Fertilizerstatus();
                    fs.setId(fertilizerstatus.getId());
                    fs.setName(fertilizerstatus.getName());
                    return fs; }
        ).collect(Collectors.toList());

        return fertilizerstatuss;
    }

}
