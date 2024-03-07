package lk.uva.uvateafactory.controller;


import lk.uva.uvateafactory.dao.SupplierDao;
import lk.uva.uvateafactory.entity.Supplier;
import lk.uva.uvateafactory.entity.Supplierfertilizer;
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
@RequestMapping(value = "/suppliers")
public class SupplierController {

    @Autowired
    private SupplierDao supplierDao;

    @GetMapping(produces = "application/json")
    public List<Supplier> get(@RequestParam HashMap<String,String> params) {

        String suppliername = params.get("suppliername");
        String fertilizerid = params.get("fertilizerid");

        List<Supplier> suppliers = this.supplierDao.findAll();

        if(params.isEmpty()) return suppliers;

        Stream<Supplier> sstream = suppliers.stream();

        if(suppliername!=null) sstream = sstream.filter(s -> s.getName().contains(suppliername));
        if(fertilizerid!=null) sstream = sstream.filter(s -> s.getSupplierfertilizers().stream().anyMatch(sf -> sf.getFertilizer().getId()==Integer.parseInt(fertilizerid)));

        return sstream.collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> add(@RequestBody Supplier supplier){

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        if(supplierDao.findByName(supplier.getName())!= null)
            errors = errors+"<br> Existing Supplier Name";

        if(errors==""){
            for(Supplierfertilizer sf : supplier.getSupplierfertilizers()) sf.setSupplier(supplier);
            supplierDao.save(supplier);

//        if(errors==""){
//            for(Userrole u : user.getUserroleList()) u.setUser(user);
//            userdao.save(user);

            responce.put("id",String.valueOf(supplier.getId()));
            responce.put("url","/suppliers/"+supplier.getId());
            responce.put("errors",errors);

            return responce;
        }

        else errors = "Server Validation Errors : <br> "+errors;

        responce.put("id",String.valueOf(supplier.getId()));
        responce.put("url","/suppliers/"+supplier.getId());
        responce.put("errors",errors);

        return responce;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> update(@RequestBody Supplier supplier) {
        HashMap<String, String> response = new HashMap<>();
        String errors = "";


        Supplier extsupplier = supplierDao.findByMyId(supplier.getId());


        // Update basic user properties
        //  BeanUtils.copyProperties(extUser, user, "id");

        if (extsupplier != null) {
            try {
                extsupplier.getSupplierfertilizers().clear();
                supplier.getSupplierfertilizers().forEach(newsupplierfertilizer -> {
                    newsupplierfertilizer.setSupplier(extsupplier);
                    extsupplier.getSupplierfertilizers().add(newsupplierfertilizer);
                    newsupplierfertilizer.setSupplier(extsupplier);
                });

//                extsupplier.setName(supplier.getName());
//                extsupplier.setContactperson(supplier.getContactperson());
//                extsupplier.setEmail(supplier.getEmail());
//                extsupplier.setCreditlimit(supplier.getCreditlimit());
//                extsupplier.setAddress(supplier.getAddress());
//                extsupplier.setOfficetp(supplier.getOfficetp());
//                extsupplier.setContactpersontp(supplier.getContactpersontp());
//                extsupplier.setSupplierstatus(supplier.getSupplierstatus());

//                extUser.setDescription(user.getDescription());
//                extUser.setTocreated(user.getTocreated());
//                extUser.setDocreated(user.getDocreated());
//                extUser.setUserstatus(user.getUserstatus());

                // Update basic user properties
                BeanUtils.copyProperties(supplier, extsupplier, "id","supplierfertilizers");

                supplierDao.save(extsupplier); // Save the updated extUser object

                response.put("id", String.valueOf(supplier.getId()));
                response.put("url", "/suppliers/" + supplier.getId());
                response.put("errors", errors);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id){

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        Supplier existsupplier = supplierDao.findByMyId(id);

        if(existsupplier==null)
            errors = errors+"<br> Supplier Does Not Existed";

        if(errors=="") supplierDao.delete(existsupplier);
        else errors = "Server Validation Errors : <br> "+errors;

        responce.put("id",String.valueOf(id));
        responce.put("url","/suppliers/"+id);
        responce.put("errors",errors);

        return responce;
    }

}
