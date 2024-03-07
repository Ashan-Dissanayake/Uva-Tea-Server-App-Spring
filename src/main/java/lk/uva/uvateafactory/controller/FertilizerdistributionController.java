package lk.uva.uvateafactory.controller;

import lk.uva.uvateafactory.dao.FertilizerDao;
import lk.uva.uvateafactory.dao.FertilizerdistributionDao;
import lk.uva.uvateafactory.entity.Fertilizer;
import lk.uva.uvateafactory.entity.Fertilizerdistribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/fertilizerdistributions")
public class FertilizerdistributionController {

    @Autowired
    private FertilizerdistributionDao fertilizerdistributionDao;

    @Autowired
    private FertilizerDao fertilizerDao;


    @GetMapping(produces = "application/json")
    public List<Fertilizerdistribution> get(@RequestParam HashMap<String,String> params) {

        String areaid = params.get("areaid");
        String brandid = params.get("brandid");
        String fertypeid = params.get("fertypeid");
        String ferdisstateid = params.get("ferdisstateid");
        String kankaniid = params.get("kankaniid");
        String date = params.get("date");
        String fertiname = params.get("fertiname");

//        BigDecimal num1 = BigDecimal.valueOf(14.56);
//        BigDecimal num2 = BigDecimal.valueOf(14.56);
//
//        BigDecimal reslt = num2.subtract(num1);
//
//        if(reslt.compareTo(BigDecimal.ZERO)<0) {
//            System.out.println("Minus");
//        }
//        else {
//            System.out.println("Positive");
//        }

        List<Fertilizerdistribution> fertilizerdistributions = this.fertilizerdistributionDao.findAll();

        if(params.isEmpty()) return fertilizerdistributions;

        Stream<Fertilizerdistribution> fertilizerdistributionStream = fertilizerdistributions.stream();

        if(areaid!=null) fertilizerdistributionStream = fertilizerdistributionStream.filter(fds -> fds.getArea().getId()==Integer.parseInt(areaid));
        if(fertiname!=null) fertilizerdistributionStream = fertilizerdistributionStream.filter(fds -> fds.getFertilizer().getName().contains(fertiname));
        if(brandid!=null) fertilizerdistributionStream = fertilizerdistributionStream.filter(fds -> fds.getFertilizer().getFertilzerbrand().getId()==Integer.parseInt(brandid));
        if(fertypeid!=null) fertilizerdistributionStream = fertilizerdistributionStream.filter(fds -> fds.getFertilizer().getFertilizertype().getId()==Integer.parseInt(fertypeid));
        if(ferdisstateid!=null) fertilizerdistributionStream = fertilizerdistributionStream.filter(fds -> fds.getFerdistributionstate().getId()==Integer.parseInt(ferdisstateid));
        if(kankaniid!=null) fertilizerdistributionStream = fertilizerdistributionStream.filter(fds -> fds.getKankani().getId()==Integer.parseInt(kankaniid));
        if(date!=null) fertilizerdistributionStream = fertilizerdistributionStream.filter(fds -> fds.getDate().toString().equals(date));

        return fertilizerdistributionStream.collect(Collectors.toList());
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> add(@RequestBody Fertilizerdistribution fertilizerdistribution) {

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        Fertilizer fertilizer = fertilizerDao.findByMyId(fertilizerdistribution.getFertilizer().getId());

        BigDecimal storeqty = fertilizer.getQuantity();
        BigDecimal reqstqty = fertilizerdistribution.getQuantitydis();

        BigDecimal reslt = storeqty.subtract(reqstqty);

        if(reslt.compareTo(BigDecimal.ZERO)<0) {
//            System.out.println("No Enough Fertilizer is Available");
            errors = errors + "No Enough Fertilizer is Available";
        }
        else {
            fertilizer.setQuantity(reslt);
//            System.out.println("db save");
        }


        if(errors=="") {
            fertilizerdistributionDao.save(fertilizerdistribution);
            fertilizerDao.save(fertilizer);
        }
        else {
            errors = "Server Validation Errors : <br> <br>"+errors;
        }

        responce.put("id",String.valueOf(fertilizerdistribution.getId()));
        responce.put("url","/fertilizerdistributions/"+fertilizerdistribution.getId());
        responce.put("errors",errors);

        return responce;

    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> update(@RequestBody Fertilizerdistribution fertilizerdistribution) {

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        BigDecimal nowqty = fertilizerdistribution.getQuantitydis();
        Fertilizerdistribution oldfertilizerdistribution = fertilizerdistributionDao.findByMyId(fertilizerdistribution.getId());
        BigDecimal prevqty = oldfertilizerdistribution.getQuantitydis();

        Fertilizer existfertilizer = fertilizerDao.findByMyId(oldfertilizerdistribution.getFertilizer().getId());

        Fertilizer reqstFertilizer = null;


        BigDecimal storeqty = existfertilizer.getQuantity();
        BigDecimal reslt = storeqty.subtract(nowqty);

        if(fertilizerdistribution.getFertilizer().getId()==oldfertilizerdistribution.getFertilizer().getId()){
            //if fertilizer ids are equal(MEANS NOT CHANGE THE FERTILIZER)

            if(fertilizerdistribution.getFerdistributionstate().getName().equalsIgnoreCase("Approved")){

                if(reslt.compareTo(BigDecimal.ZERO)<0) {
//            System.out.println("No Enough Fertilizer is Available");
                    errors = errors + "No Enough Fertilizer is Available";
                }

                else{

//                this works when fertilizer available in store.

                    String nowfertilizerstate = fertilizerdistribution.getFerdistributionstate().getName();
                    String olderfertilizerstate = oldfertilizerdistribution.getFerdistributionstate().getName();

                    if(nowfertilizerstate.equalsIgnoreCase(olderfertilizerstate)) {
//                    check prev and current fertilizer distribution state,if equal then run
                        BigDecimal differenceqty = nowqty.subtract(prevqty);
                        System.out.println("Differ: "+differenceqty);
                        System.out.println("Store: "+existfertilizer.getQuantity());

                        if(differenceqty.compareTo(BigDecimal.ZERO)<0) {
                            differenceqty = differenceqty.abs();
                            existfertilizer.setQuantity(storeqty.add(differenceqty));
                        }
                        else{
                            existfertilizer.setQuantity(storeqty.subtract(differenceqty));
                        }
                    }
                    else{
//                    run, if state not equal
                        System.out.println("need block");
                        existfertilizer.setQuantity(storeqty.subtract(nowqty));
                    }
                }
            }
            else{
//            if requesting fertilizer state is not equal to Approved
                existfertilizer.setQuantity(storeqty.add(nowqty));
            }

        }

        else{
//            fertilizer ids are not equal(MEANS IT CHANGE THE FERTILIZER)
            existfertilizer.setQuantity(storeqty.add(oldfertilizerdistribution.getQuantitydis()));

            Integer requstfertilizerId = fertilizerdistribution.getFertilizer().getId();
            reqstFertilizer = fertilizerDao.findByMyId(requstfertilizerId);
            BigDecimal existQty = reqstFertilizer.getQuantity();

            BigDecimal reslt2 = existQty.subtract(nowqty);


            if(reslt2.compareTo(BigDecimal.ZERO)<0) {
                errors = errors + "No Enough Fertilizer2 is Available";
            }
            else{
                reqstFertilizer.setQuantity(existQty.subtract(nowqty));
            }
        }
        
        if(errors=="") {
            fertilizerdistributionDao.save(fertilizerdistribution);
            fertilizerDao.save(existfertilizer);
            if(reqstFertilizer!=null) fertilizerDao.save(reqstFertilizer);
        }
        else errors = "Server Validation Errors : <br> <br>"+errors;

        responce.put("id",String.valueOf(fertilizerdistribution.getId()));
        responce.put("url","/fertilizerdistributions/"+fertilizerdistribution.getId());
        responce.put("errors",errors);

        return responce;

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id) {

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        Fertilizerdistribution fertilizerdistribution= fertilizerdistributionDao.findByMyId(id);

        if(fertilizerdistribution==null)
            errors = errors+"<br> Fertilizer Distribution Does Not Existed.";

        if(errors=="") fertilizerdistributionDao.delete(fertilizerdistribution);
        else errors = "Server Validation Errors : <br>"+errors;

        responce.put("id",String.valueOf(id));
        responce.put("url","/fertilizerdistributions/"+id);
        responce.put("errors",errors);

        return responce;

    }

    /*@PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> update(@RequestBody Fertilizerdistribution fertilizerdistribution) {

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        BigDecimal nowqty = fertilizerdistribution.getQuantitydis();
        Fertilizerdistribution oldfertilizerdistribution = fertilizerdistributionDao.findByMyId(fertilizerdistribution.getId());
        BigDecimal prevqty = oldfertilizerdistribution.getQuantitydis();

        Fertilizer existfertilizer = fertilizerDao.findByMyId(fertilizerdistribution.getFertilizer().getId());


        BigDecimal storeqty = existfertilizer.getQuantity();
        BigDecimal reslt = storeqty.subtract(nowqty);

        if(fertilizerdistribution.getFerdistributionstate().getName().equalsIgnoreCase("Approved")){

            if(reslt.compareTo(BigDecimal.ZERO)<0) {
//            System.out.println("No Enough Fertilizer is Available");
                errors = errors + "No Enough Fertilizer is Available";
            }

            else{

//                this works when fertilizer available in store.

                String nowfertilizerstate = fertilizerdistribution.getFerdistributionstate().getName();
                String olderfertilizerstate = oldfertilizerdistribution.getFerdistributionstate().getName();

                if(nowfertilizerstate.equalsIgnoreCase(olderfertilizerstate)) {
//                    check prev and current fertilizer distribution state,if equal then run
                    BigDecimal differenceqty = nowqty.subtract(prevqty);
                    System.out.println("Differ: "+differenceqty);
                    System.out.println("Store: "+existfertilizer.getQuantity());

                    if(differenceqty.compareTo(BigDecimal.ZERO)<0) {
                        differenceqty = differenceqty.abs();
                        existfertilizer.setQuantity(storeqty.add(differenceqty));
                    }
                    else{
                        existfertilizer.setQuantity(storeqty.subtract(differenceqty));
                    }
                }
                else{
//                    run, if state not equal
                    System.out.println("need block");
                    existfertilizer.setQuantity(storeqty.subtract(nowqty));
                }
            }
        }
        else{
//            if requesting fertilizer state is not equal to Approved
            existfertilizer.setQuantity(storeqty.add(nowqty));
        }



        if(errors=="") {
            fertilizerdistributionDao.save(fertilizerdistribution);
            fertilizerDao.save(existfertilizer);
        }
        else errors = "Server Validation Errors : <br> <br>"+errors;

        responce.put("id",String.valueOf(fertilizerdistribution.getId()));
        responce.put("url","/fertilizerdistributions/"+fertilizerdistribution.getId());
        responce.put("errors",errors);

        return responce;

    }*/



}
