package lk.uva.uvateafactory.controller;


import lk.uva.uvateafactory.dao.InvoiceDao;
import lk.uva.uvateafactory.dao.OrderrDao;
import lk.uva.uvateafactory.entity.Invoice;
import lk.uva.uvateafactory.entity.Invoiceproduct;
import lk.uva.uvateafactory.entity.Orderr;
import lk.uva.uvateafactory.entity.Orderrproduct;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceDao invoiceDao;

    @GetMapping(produces = "application/json")
    public List<Invoice> get(@RequestParam HashMap<String,String> params) {

        String date = params.get("date");
        String distributorid = params.get("distributorid");
        String managerid = params.get("managerid");
        String statusid = params.get("statusid");

        List<Invoice> invoices = this.invoiceDao.findAll();

        if(params.isEmpty()) return invoices;

        Stream<Invoice> invoistrm = invoices.stream();

        if(date!=null) invoistrm = invoistrm.filter(e -> e. getDate().toString().equals(date));
        if(distributorid!=null) invoistrm = invoistrm.filter(e -> e. getOrderr().getDistributor().getId()==Integer.parseInt(distributorid));
        if(managerid!=null) invoistrm = invoistrm.filter(e -> e. getManager().getId()==Integer.parseInt(managerid));
        if(statusid!=null) invoistrm = invoistrm.filter(e -> e. getInvoicestatus().getId()==Integer.parseInt(statusid));

        return invoistrm.collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> add(@RequestBody Invoice invoice) {

        HashMap<String, String> response = new HashMap<>();
        String errors = "";

        for (Invoiceproduct invoiprocdt : invoice.getInvoiceproducts()) invoiprocdt.setInvoice(invoice);

        invoiceDao.save(invoice);

        response.put("id", String.valueOf(invoice.getId()));
        response.put("url", "/invoices/" + invoice.getId());
        response.put("errors", errors);

        return response;

    }


    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> update(@RequestBody Invoice invoice) {

        HashMap<String, String> response = new HashMap<>();
        String errors = "";


        Invoice extInvoice = invoiceDao.findByMyId(invoice.getId());


        if (extInvoice != null) {

            try {
                extInvoice.getInvoiceproducts().clear();
                invoice.getInvoiceproducts().forEach(inviceprodct -> {
                    inviceprodct.setInvoice(extInvoice);
                    extInvoice.getInvoiceproducts().add(inviceprodct);
                    inviceprodct.setInvoice(extInvoice);
                });

                BeanUtils.copyProperties(invoice, extInvoice, "id","invoiceproducts");

                invoiceDao.save(extInvoice); // Save the updated extProduction object

                response.put("id", String.valueOf(invoice.getId()));
                response.put("url", "/invoices/" + invoice.getId());
                response.put("errors", errors);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id) {

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        Invoice invoice = invoiceDao.findByMyId(id);

        if(invoice==null)
            errors = errors+"<br> Invoice Does Not Existed.";

        if(errors=="") invoiceDao.delete(invoice);
        else errors = "Server Validation Errors : <br>"+errors;

        responce.put("id",String.valueOf(id));
        responce.put("url","/invoices/"+id);
        responce.put("errors",errors);

        return responce;

    }

}
