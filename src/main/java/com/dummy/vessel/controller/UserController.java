package com.dummy.vessel.controller;

//import com.dummy.vessel.das.UserDasImpl;
//import com.dummy.vessel.entities.User;
import com.dummy.vessel.service.impl.CategoryDasImpl;
import com.dummy.vessel.service.impl.ProductDasImpl;
import com.dummy.vessel.service.impl.UserDasImpl;
import com.dummy.vessel.dto.ProductDTO;
import com.dummy.vessel.entities.Product;
import com.dummy.vessel.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserDasImpl userDasImpl;

    @Autowired
    ProductDasImpl productDasImpl;

    @Autowired
    CategoryDasImpl categoryService;

    @GetMapping({"/index", "/"})
    public String dashboard(Model model, Principal p) {
        model.addAttribute("title", "User Profile");
        String name = p.getName();
        User user = userDasImpl.getUserByUserName(name);
        model.addAttribute("user", user);
        System.out.println("#### user from dashboard is ");
        return "dummy_users/user_sections/dashboard";
    }

//   ----------- products----------

    @GetMapping("/all-products")
    public String showAllProducts(Model m){
        m.addAttribute("title", "all-products");
        m.addAttribute("products", productDasImpl.getAllProduct());
        System.out.println("###### showing all products");
        return "dummy_users/user_sections/all-products";
    }

    @GetMapping("/add-products")
    public String addProducts(Model m){
        m.addAttribute("title", "add-products");
        m.addAttribute("productDTO", new ProductDTO());
        m.addAttribute("categories", categoryService.getAllCategories());
        return "dummy_users/user_sections/add-products";
    }


    @PostMapping("/add-products")
    public String addProducts(@ModelAttribute("productDTO") ProductDTO productDTO) {
        System.out.println("####### received DTO is " + productDTO.toString());
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        System.out.println("#### saving product is " + product.toString());
        productDasImpl.addProduct(product);
        return "redirect:all-products";
    }

    @GetMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable Long id){
        productDasImpl.removeProduct(id);
        return "redirect:../all-products";
    }

    @GetMapping("/update-product/{id}")
    public String updateProduct(@PathVariable Long id, Model m){
        Optional<Product> optionalProduct = productDasImpl.getProductById(id);
        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setCategoryId(product.getCategory().getId());
            productDTO.setPrice(product.getPrice());
            productDTO.setDescription(product.getDescription());
            m.addAttribute("categories", categoryService.getAllCategories());
            m.addAttribute("productDTO", productDTO);
            return "dummy_users/user_sections/add-products";
        }
        else{
            return "404";
        }
    }

}
