package lk.uva.uvateafactory.controller;


import lk.uva.uvateafactory.dao.OrderrstatusDao;
import lk.uva.uvateafactory.entity.Orderstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/orderstatuss")
public class OrderstatusController {

    @Autowired
    private OrderrstatusDao orderrstatusDao;

    @GetMapping( path = "/list", produces = "application/json")
    public List<Orderstatus> get() {

        List<Orderstatus> orderstatuses = this.orderrstatusDao.findAll();

        orderstatuses = orderstatuses.stream().map(
                orderstatus -> { Orderstatus g = new Orderstatus();
                    g.setId(orderstatus.getId());
                    g.setName(orderstatus.getName());
                    return g; }
        ).collect(Collectors.toList());

        return orderstatuses;
    }

}
