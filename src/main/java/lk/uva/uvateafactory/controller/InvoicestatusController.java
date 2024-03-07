package lk.uva.uvateafactory.controller;


import lk.uva.uvateafactory.dao.InvoicestatusDao;
import lk.uva.uvateafactory.dao.OrderrstatusDao;
import lk.uva.uvateafactory.entity.Invoicestatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/invoicestatuss")
public class InvoicestatusController {

    @Autowired
    private InvoicestatusDao invoicestatusDao;

    @GetMapping( path = "/list", produces = "application/json")
    public List<Invoicestatus> get() {

        List<Invoicestatus> invoicestatuses = this.invoicestatusDao.findAll();

        invoicestatuses = invoicestatuses.stream().map(
                invoicestatus -> { Invoicestatus g = new Invoicestatus();
                    g.setId(invoicestatus.getId());
                    g.setName(invoicestatus.getName());
                    return g; }
        ).collect(Collectors.toList());

        return invoicestatuses;
    }

}
