package lk.uva.uvateafactory.controller;

import lk.uva.uvateafactory.dao.SupplierfertilizerDao;
import lk.uva.uvateafactory.entity.Supplierfertilizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/supllierfetilizer")
public class SupplierfertilizerController {

    @Autowired
    private SupplierfertilizerDao supplierfertilizerDao;

    @GetMapping( path = "/list", produces = "application/json")
    public List<Supplierfertilizer> get() {

        List<Supplierfertilizer> supplierfertilizers = this.supplierfertilizerDao.findAll();

        supplierfertilizers = supplierfertilizers.stream().map(
                supplifertilzer -> { Supplierfertilizer sf = new Supplierfertilizer();
                    sf.setId(supplifertilzer.getId());
                    sf.setSupplier(supplifertilzer.getSupplier());
                    sf.setFertilizer(supplifertilzer.getFertilizer());
                    return sf; }
        ).collect(Collectors.toList());

        return supplierfertilizers;
    }

}
