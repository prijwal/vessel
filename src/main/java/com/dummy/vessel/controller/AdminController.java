package com.dummy.vessel.controller;

import com.dummy.vessel.entities.Role;
import com.dummy.vessel.service.impl.CategoryDasImpl;
import com.dummy.vessel.service.impl.ProductDasImpl;
import com.dummy.vessel.service.impl.RoleDasImpl;
import com.dummy.vessel.service.impl.UserDasImpl;
import com.dummy.vessel.dto.ProductDTO;
import com.dummy.vessel.dto.UserDTO;
import com.dummy.vessel.entities.Category;
import com.dummy.vessel.entities.Product;
import com.dummy.vessel.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserDasImpl userDasImpl;

    @Autowired
    CategoryDasImpl categoryService;
    @Autowired
    ProductDasImpl productDasImpl;

    @Autowired
    RoleDasImpl roleDasImpl;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping({"/index", "/"})
    public String showDashboard(Model m, Principal p) {
        m.addAttribute("title", "dashboard");
        String name = p.getName();
        User user = userDasImpl.getUserByUserName(name);
        m.addAttribute("username", user.getUsername());
        return "dummy_admins/admin_sections/dashboard";
    }

    //    ----------- categories ------------


    @GetMapping("/all-categories")
    public String showAllCategories(Model m, Principal p) {
        m.addAttribute("title", "all-categories");
        String name = p.getName();
        User user = userDasImpl.getUserByUserName(name);
        m.addAttribute("username", user.getUsername());
        m.addAttribute("category", categoryService.getAllCategories());
        return "dummy_admins/admin_sections/all-categories";
    }

    @GetMapping("/add-categories")
    public String addCategories(Model m, Principal p) {
        System.out.println("#### add-categories is called with model " + m.toString());
        m.addAttribute("title", "add-categories");
        String name = p.getName();
        User user = userDasImpl.getUserByUserName(name);
        m.addAttribute("username", user.getUsername());
        m.addAttribute("category", new Category());
        return "dummy_admins/admin_sections/add-categories";
    }


    @PostMapping("/add-categories")
    public String addCategories(@ModelAttribute("category") Category category) {
        categoryService.addCategory(category);
        return "redirect:all-categories";
    }

    @GetMapping("/delete-category/{id}")
    public String deleteCategory(@PathVariable int id) {
        categoryService.deleteCategoryById(id);
        return "redirect:../all-categories";
    }

    @GetMapping("/update-category/{id}")
    public String updateCategory(@PathVariable int id, Model m, Principal p) {
        Optional<Category> category = categoryService.getCategoryById(id);
        String name = p.getName();
        User user = userDasImpl.getUserByUserName(name);
        m.addAttribute("username", user.getUsername());
        if (category.isPresent()) {
            m.addAttribute("category", category.get());
            return "dummy_admins/admin_sections/add-categories";
        } else {
            return "404";
        }


    }


//    ----------- products ------------

    @GetMapping("/all-products")
    public String showAllProducts(Model m, Principal p) {
        m.addAttribute("title", "all-products");
        String name = p.getName();
        User user = userDasImpl.getUserByUserName(name);
        m.addAttribute("username", user.getUsername());
        m.addAttribute("products", productDasImpl.getAllProduct());
        return "dummy_admins/admin_sections/all-products";
    }

    @GetMapping("/add-products")
    public String addProducts(Model m, Principal p) {
        m.addAttribute("title", "add-products");
        String name = p.getName();
        User user = userDasImpl.getUserByUserName(name);
        m.addAttribute("username", user.getUsername());
        m.addAttribute("productDTO", new ProductDTO());
        m.addAttribute("categories", categoryService.getAllCategories());
        return "dummy_admins/admin_sections/add-products";
    }


    @PostMapping("/add-products")
    public String addProducts(@ModelAttribute("productDTO") ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        productDasImpl.addProduct(product);
        return "redirect:all-products";
    }

    @GetMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productDasImpl.removeProduct(id);
        return "redirect:../all-products";
    }

    @GetMapping("/update-product/{id}")
    public String updateProduct(@PathVariable Long id, Model m, Principal p) {
        String name = p.getName();
        User user = userDasImpl.getUserByUserName(name);
        m.addAttribute("username", user.getUsername());
        Optional<Product> optionalProduct = productDasImpl.getProductById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setCategoryId(product.getCategory().getId());
            productDTO.setPrice(product.getPrice());
            productDTO.setDescription(product.getDescription());
            m.addAttribute("categories", categoryService.getAllCategories());
            m.addAttribute("productDTO", productDTO);
            return "dummy_admins/admin_sections/add-products";
        } else {
            return "404";
        }
    }


    //    ----------- Users ------------

    @GetMapping("/all-users")
    public String showAllUsers(Model m, Principal p) {
        m.addAttribute("title", "all-users");
        String name = p.getName();
        User user = userDasImpl.getUserByUserName(name);
        m.addAttribute("username", user.getUsername());
        m.addAttribute("users", userDasImpl.findAllUsers());

        return "dummy_admins/admin_sections/all-users";
    }

    @GetMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable Long id) {
        User user = userDasImpl.getUserById(id);
        user.getRoles().clear();
        userDasImpl.removeUser(id);
        return "redirect:../all-users";
    }

    @GetMapping("/add-user")
    public String addUser(Model m, Principal p) {
        m.addAttribute("title", "add-user");
        m.addAttribute("userDTO", new UserDTO());
        m.addAttribute("roles", roleDasImpl.getAllRoles());
        m.addAttribute("updateUser", false);
        String name = p.getName();
        User user = userDasImpl.getUserByUserName(name);
        m.addAttribute("username", user.getUsername());
        return "dummy_admins/admin_sections/add-user";
    }


    @PostMapping("/add-user")
    public String addUser(@ModelAttribute("userDTO") UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        String email = userDTO.getEmail();
        int index = email.indexOf('@');
        String username = email.substring(0, index);
        user.setUsername(username);
        Collection<Role> roles = roleDasImpl.getRoleById(userDTO.getRoleId());

        user.setRoles(roles);

        if (!userDTO.getPassword().isEmpty())
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        else
            user.setPassword(userDTO.getPassword());

        user.setEnabled(userDTO.isEnabled());
        userDasImpl.save(user);
        return "redirect:all-users";
    }


    @GetMapping("/update-user/{id}")
    public String updateUser(@PathVariable Long id, Model m, Principal p) {
        String name = p.getName();
        User loggedInUser = userDasImpl.getUserByUserName(name);
        m.addAttribute("username", loggedInUser.getUsername());
        User user = userDasImpl.getUserById(id);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setEmail(user.getEmail());
        Collection<Role> roles = user.getRoles();
        userDTO.setRoleId(roles.iterator().next().getId());
        userDTO.setEnabled(user.isEnabled());
        m.addAttribute("roles", roleDasImpl.getAllRoles());
        m.addAttribute("userDTO", userDTO);
        m.addAttribute("updateUser", true);
        return "dummy_admins/admin_sections/add-user";
    }

}
