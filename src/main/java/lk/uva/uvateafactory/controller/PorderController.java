package lk.uva.uvateafactory.controller;

import lk.uva.uvateafactory.dao.FertilizerDao;
import lk.uva.uvateafactory.dao.PorderDao;
import lk.uva.uvateafactory.entity.Fertilizer;
import lk.uva.uvateafactory.entity.Porder;
import lk.uva.uvateafactory.entity.Porderfertilizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/porders")
public class PorderController {

    @Autowired
    private PorderDao porderDao;

    @Autowired
    private FertilizerDao fertilizerDao;

    @GetMapping(produces = "application/json")
    public List<Porder> get(@RequestParam HashMap<String,String> params) {

        String supplierid = params.get("supplierid");
        String postatusid = params.get("postatusid");
        String fertilizerid = params.get("fertilizerid");
        String date = params.get("date");

        List<Porder> porders = this.porderDao.findAll();

        if(params.isEmpty()) return porders;

        Stream<Porder> porderstream = porders.stream();

        if(supplierid!=null) porderstream = porderstream.filter(e -> e. getSupplier().getId()==Integer.parseInt(supplierid));
        if(postatusid!=null) porderstream = porderstream.filter(e -> e. getPorderstatus().getId()==Integer.parseInt(postatusid));
        if(fertilizerid!=null) porderstream = porderstream.filter(e -> e.getPorderfertilizers().stream().anyMatch(pofertilizers->pofertilizers.getFertilizer().getId()==Integer.parseInt(fertilizerid)));
        if(date!=null) porderstream = porderstream.filter(e -> e. getDoplaced().toString().equals(date));

        return porderstream.collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> add(@RequestBody Porder porder) {
        
        HashMap<String, String> response = new HashMap<>();
        String errors = "";

        ArrayList<Fertilizer> fertilizers = new ArrayList<>();

        for (Porderfertilizer pof : porder.getPorderfertilizers()) {
            pof.setPorder(porder);

            BigDecimal fertilizerqtyorder = pof.getQty();

            Fertilizer fertilizer = fertilizerDao.findByMyId(pof.getFertilizer().getId());
            fertilizer.setQuantity(fertilizer.getQuantity().add(fertilizerqtyorder));
            fertilizers.add(fertilizer);
        }
        
        Porder posave = porderDao.save(porder);

        if (posave.getId() != null && porder.getPorderstatus().getName().equalsIgnoreCase("Received")) {
            fertilizerDao.saveAll(fertilizers);
        }
        response.put("id", String.valueOf(porder.getId()));
        response.put("url", "/porders/" + porder.getId());
        response.put("errors", errors);
        
        return response;
    }


    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> update(@RequestBody Porder porder) {

        HashMap<String, String> response = new HashMap<>();
        String errors = "";

        // AtomicReference to track whether initial update occurred
        AtomicReference<Boolean> initialUpdated = new AtomicReference<>(true);
        // Retrieve the existing Porder from the database
        Porder extPorder = porderDao.findByMyId(porder.getId());
        // Check if the status is changing to 'Received' and was not 'Received' before
        if (Objects.equals(porder.getPorderstatus().getName(), "Received") &&
                !Objects.equals(extPorder.getPorderstatus().getName(), "Received")) {

            // Update Fertilizer quantities for each Porderfertilizer
            porder.getPorderfertilizers().forEach(pof -> {
                Fertilizer fertilizer = pof.getFertilizer();
                // Mark that the initial update has occurred
                initialUpdated.set(false);
                // Retrieve the existing Fertilizer from the database
                Fertilizer existFertilizer = fertilizerDao.findByMyId(fertilizer.getId());
                // Update Fertilizer quantity
                existFertilizer.setQuantity(existFertilizer.getQuantity().add(pof.getQty()));
                fertilizerDao.save(existFertilizer);
            });
        } else if (Objects.equals(porder.getPorderstatus().getName(), "Received") &&
                Objects.equals(extPorder.getPorderstatus().getName(), "Received")) {

            // If status was 'Received' and still 'Received', update Fertilizer quantities
            initialUpdated.set(true);

            extPorder.getPorderfertilizers().forEach(pof -> {
                Fertilizer fertilizer = pof.getFertilizer();
                BigDecimal afterReduced = fertilizer.getQuantity().subtract(pof.getQty());

                fertilizer.setQuantity(afterReduced);
                fertilizerDao.save(fertilizer);
            });
        }

        if (extPorder != null) {
            try {

                porder.getPorderfertilizers().forEach(porderfertilizer -> porderfertilizer.setPorder(porder));

                Porder pordersaved = porderDao.save(porder);

                // Update fertilizer count with new updated fertilizers if status is 'Received'
                if (pordersaved.getId() != null && porder.getPorderstatus().getName().equalsIgnoreCase("Received")
                        && initialUpdated.get()) {
                    pordersaved.getPorderfertilizers().forEach(pof -> {
                        Fertilizer fertilizer = pof.getFertilizer();

                        // Update Fertilizer quantity
                        BigDecimal newTotal = fertilizer.getQuantity().add(pof.getQty());
                        System.out.println("NewQTY " + newTotal);
                        fertilizer.setQuantity(fertilizer.getQuantity().add(pof.getQty()));
                        fertilizerDao.save(fertilizer);
                    });
                }

                response.put("id", String.valueOf(porder.getId()));
                response.put("url", "/porders/" + porder.getId());
                response.put("errors", errors);

            } catch (Exception e) {
                errors += "Poder is not found"+porder.getId();
                response.put("url", "/porders/" + porder.getId());
                response.put("errors",errors);
            }
        }

        return response;
    }



    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id) {

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        Porder porder = porderDao.findByMyId(id);

        if(porder==null)
            errors = errors+"<br> Purchase Order Does Not Existed.";

        if(errors.isEmpty()) porderDao.delete(porder);
        else errors = "Server Validation Errors : <br>"+errors;

        responce.put("id",String.valueOf(id));
        responce.put("url","/porders/"+id);
        responce.put("errors",errors);

        return responce;

    }



//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public HashMap<String, String> add(@RequestBody Porder porder) {
//
//        HashMap<String, String> response = new HashMap<>();
//        String errors = "";
//
//        ArrayList<Fertilizer>fertilizers = new ArrayList<Fertilizer>();
//
//        for (Porderfertilizer pof : porder.getPorderfertilizers()) {
//            pof.setPorder(porder);
//
//            BigDecimal fertilizerqtyorder = pof.getQty();
//
//            Fertilizer fertilizer = fertilizerDao.findByMyId(pof.getFertilizer().getId());
//            fertilizer.setQuantity(fertilizer.getQuantity().add(fertilizerqtyorder));
//
//            fertilizers.add(fertilizer);
//        }
//
//        Porder posave = porderDao.save(porder);
//
////        Updated the store fertilizer quantity only when the status is equal to received
//
//        if(posave.getId()!=null && porder.getPorderstatus().getName().equalsIgnoreCase("Received")) {
//            fertilizerDao.saveAll(fertilizers);
//        }
//
//        response.put("id", String.valueOf(porder.getId()));
//        response.put("url", "/porders/" + porder.getId());
//        response.put("errors", errors);
//
//        return response;
//
//    }
//
//
//    @PutMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public HashMap<String, String> update(@RequestBody Porder porder) {
//
//        HashMap<String, String> response = new HashMap<>();
//        String errors = "";
//
//        AtomicReference<Boolean> initialupadted = new AtomicReference<>(true);
//
//        Porder extPorder = porderDao.findByMyId(porder.getId());
//
//        if(Objects.equals(porder.getPorderstatus().getName(), "Received") && !Objects.equals(extPorder.getPorderstatus().getName(), "Received")) {
//            porder.getPorderfertilizers().forEach(pof->{
//                Fertilizer fertilizer = pof.getFertilizer();
//
//                initialupadted.set(false);
//
//                Fertilizer existfertilizer = fertilizerDao.findByMyId(fertilizer.getId());
//
//                BigDecimal firstgetquantity = existfertilizer.getQuantity();
//                BigDecimal firsttotalquantity = existfertilizer.getQuantity().add(pof.getQty());
//
//                existfertilizer.setQuantity(existfertilizer.getQuantity().add(pof.getQty()));
//                fertilizerDao.save(existfertilizer);
//            });
//
//        }
//
//        else if(Objects.equals(porder.getPorderstatus().getName(), "Received") && Objects.equals(extPorder.getPorderstatus().getName(), "Received")) {
//
//            initialupadted.set(true);
//
//            extPorder.getPorderfertilizers().forEach(pof->{
//                Fertilizer fertilizer = pof.getFertilizer();
//                BigDecimal Afteredued = fertilizer.getQuantity().subtract(pof.getQty());
//                fertilizer.setQuantity(Afteredued);
//
//                fertilizerDao.save(fertilizer);
//            });
//
//        }
//
//        if (extPorder != null) {
//
//            try {
//                extPorder.getPorderfertilizers().clear();
//                porder.getPorderfertilizers().forEach(newpofertilizer -> {
//                    newpofertilizer.setPorder(extPorder);
//                    extPorder.getPorderfertilizers().add(newpofertilizer);
//                    newpofertilizer.setPorder(extPorder);
//
//                });
//
//                BeanUtils.copyProperties(porder, extPorder, "id","porderfertilizers");
//
//                Porder pordersaved = porderDao.save(extPorder);
//
////                Updated fertlizer count with new updated fertilizers.
//                if(pordersaved.getId()!=null && porder.getPorderstatus().getName().equalsIgnoreCase("Received") && initialupadted.get()) {
//                    pordersaved.getPorderfertilizers().forEach(pof->{
//                        Fertilizer fertilizer = pof.getFertilizer();
//
//                        BigDecimal newtotal = fertilizer.getQuantity().add(pof.getQty());
//                        System.out.println("NewQTY "+ newtotal);
//                        fertilizer.setQuantity(fertilizer.getQuantity().add(pof.getQty()));
//                        fertilizerDao.save(fertilizer);
//                    });
//                }
//
//                response.put("id", String.valueOf(porder.getId()));
//                response.put("url", "/porders/" + porder.getId());
//                response.put("errors", errors);
//
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        return response;
//    }
//


}
