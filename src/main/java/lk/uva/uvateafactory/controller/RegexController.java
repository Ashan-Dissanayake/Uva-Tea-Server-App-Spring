package lk.uva.uvateafactory.controller;

import lk.uva.uvateafactory.entity.*;
import lk.uva.uvateafactory.util.RegexProvider;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@CrossOrigin
@RestController
@RequestMapping(value = "/regexes")
public class RegexController {

    @GetMapping(path = "/employee",produces = "application/json")
    public HashMap<String, HashMap<String,String>> employee() {
         return RegexProvider.get(new Employee());
    }

    @GetMapping(path = "/area",produces = "application/json")
    public HashMap<String, HashMap<String,String>> area() {
        return RegexProvider.get(new Area());
    }

    @GetMapping(path = "/plucking",produces = "application/json")
    public HashMap<String, HashMap<String,String>> plucking() {
        return RegexProvider.get(new Plucking());
    }

    @GetMapping(path = "/fertilizer",produces = "application/json")
    public HashMap<String, HashMap<String,String>> fertilizer() {
        return RegexProvider.get(new Fertilizer());
    }

    @GetMapping(path = "/vehicle",produces = "application/json")
    public HashMap<String, HashMap<String,String>> vehicle() {
        return RegexProvider.get(new Vehicle());
    }

    @GetMapping(path = "/transport",produces = "application/json")
    public HashMap<String, HashMap<String,String>> transport() {
        return RegexProvider.get(new Transport());
    }

    @GetMapping(path = "/fuel",produces = "application/json")
    public HashMap<String, HashMap<String,String>> fuel() {
        return RegexProvider.get(new Fuel());
    }

    @GetMapping(path = "/supplier",produces = "application/json")
    public HashMap<String, HashMap<String,String>> supplier() {
        return RegexProvider.get(new Supplier());
    }

    @GetMapping(path = "/productionorder",produces = "application/json")
    public HashMap<String, HashMap<String,String>> productionorder() {
        return RegexProvider.get(new Productionorder());
    }

    @GetMapping(path ="/users", produces = "application/json")
    public HashMap<String, HashMap<String, String>> user() {
        return RegexProvider.get(new User());
    }


}
