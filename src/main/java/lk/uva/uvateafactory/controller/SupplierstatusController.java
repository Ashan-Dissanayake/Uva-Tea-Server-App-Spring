package lk.uva.uvateafactory.controller;

import lk.uva.uvateafactory.dao.SupplierstatusDao;
import lk.uva.uvateafactory.entity.Supplierstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/supplierstatuss")
public class SupplierstatusController {

    @Autowired
    private SupplierstatusDao supplierstatusDao;

    @GetMapping( path = "/list", produces = "application/json")
    public List<Supplierstatus> get() {

        List<Supplierstatus> supplierstatuss = this.supplierstatusDao.findAll();

        supplierstatuss = supplierstatuss.stream().map(
                supplierstatus -> { Supplierstatus g = new Supplierstatus();
                    g.setId(supplierstatus.getId());
                    g.setName(supplierstatus.getName());
                    return g; }
        ).collect(Collectors.toList());

        return supplierstatuss;
    }

}
