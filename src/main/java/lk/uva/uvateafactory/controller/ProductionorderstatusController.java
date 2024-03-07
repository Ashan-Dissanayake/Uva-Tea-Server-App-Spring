package lk.uva.uvateafactory.controller;


import lk.uva.uvateafactory.dao.ProductionorderstatusDao;
import lk.uva.uvateafactory.entity.Productionorderstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/productionorderstatuss")
public class ProductionorderstatusController {

    @Autowired
    private ProductionorderstatusDao productionorderstatusDao;

    @GetMapping( path = "/list", produces = "application/json")
    public List<Productionorderstatus> get() {

        List<Productionorderstatus> productionorderstatuss = this.productionorderstatusDao.findAll();

        productionorderstatuss = productionorderstatuss.stream().map(
                productionorderstatus -> { Productionorderstatus proorder = new Productionorderstatus();
                    proorder.setId(productionorderstatus.getId());
                    proorder.setName(productionorderstatus.getName());
                    return proorder; }
        ).collect(Collectors.toList());

        return productionorderstatuss;
    }

}
