package lk.uva.uvateafactory.controller;


import lk.uva.uvateafactory.dao.ProductDao;
import lk.uva.uvateafactory.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @GetMapping( path = "/list", produces = "application/json")
    public List<Product> get() {

        List<Product> products = this.productDao.findAll();

        products = products.stream().map(
                product -> { Product p = new Product();
                    p.setId(product.getId());
                    p.setName(product.getName());
                    p.setUnitprice(product.getUnitprice());
                    p.setQtyonhand(product.getQtyonhand());
                    return p; }
        ).collect(Collectors.toList());

        return products;
    }

}
