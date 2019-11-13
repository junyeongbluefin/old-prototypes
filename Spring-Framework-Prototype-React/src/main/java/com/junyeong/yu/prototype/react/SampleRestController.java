package com.junyeong.yu.prototype.react;

import com.junyeong.yu.prototype.react.model.Product;
import com.junyeong.yu.prototype.react.model.User;
import com.junyeong.yu.prototype.react.repository.ProductRepository;
import com.junyeong.yu.prototype.react.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/sample/api")
public class SampleRestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping("/")
    @ResponseBody
    public String hello() {
        return "{name: \"Hello World!\"}";
    }

    @RequestMapping("/userList")
    @ResponseBody
    public Object userList() {
        userRepository.save(new User("Junyeong" + new Random().nextInt(5000), "Yu" +  + new Random().nextInt(5000)));
        Page<User> result = userRepository.findAll(new PageRequest(0, 1000));
        List<User> userList = result.getContent();
        return userList;
    }

    @RequestMapping("/product/list")
    @ResponseBody
    public Object productList() {
        List<Product> userList = productRepository.findAllByOrderByIdDesc();
        return userList;
    }

    @RequestMapping("/product/create")
    @ResponseBody
    public Object createProduct(@RequestBody Product product) {
        if (product == null || product.getTitle() == null) {
            product = new Product();
            product.setTitle("HDD " + new Random().nextInt(500) + " MB");
            product.setDate(new Date());
            product.setPrice(new Random().nextInt(1000));
            product.setPhone("443-3840-0801");
            product.setAddress(new Random().nextInt(100) + " West Gate");
            product.setUsername("John" + new Random().nextInt(100));
        }

        productRepository.save(product);
        return product;
    }

    @RequestMapping("/product/removeAll")
    @ResponseBody
    public Object removeAllProducts() {
        productRepository.deleteAll();
        return new Product();
    }
}