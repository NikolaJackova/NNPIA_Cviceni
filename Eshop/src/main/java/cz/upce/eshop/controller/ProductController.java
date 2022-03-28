package cz.upce.eshop.controller;

import cz.upce.eshop.dto.AddOrEditProductDto;
import cz.upce.eshop.entity.Product;
import cz.upce.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.Base64;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @ExceptionHandler(Exception.class)
    public String handlerException(){
        return "error";
    }

    @GetMapping("/")
    public String showAllProducts(Model model) {
        model.addAttribute("productList", productRepository.findAll());
        return "product-list";
    }

    @GetMapping("/product-detail/{id}")
    public String showProductDetail(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id).get();
        model.addAttribute("product", product);
        model.addAttribute("image", Base64.getEncoder().encodeToString(product.getImage()));
        return "product-detail";
    }

    @GetMapping(value = {"/product-form", "/product-form/{id}"})
    public String showProductForm(@PathVariable(required = false) Long id, Model model) {
        if (id != null) {
            Product byId = productRepository.findById(id).orElse(new Product());

            AddOrEditProductDto addOrEditProductDto = new AddOrEditProductDto();
            addOrEditProductDto.setId(byId.getId());
            addOrEditProductDto.setProductName(byId.getProductName());
            addOrEditProductDto.setDescription(byId.getDescription());

            model.addAttribute("product", byId);
        } else {
            model.addAttribute("product", new AddOrEditProductDto());
        }
        return "product-form";
    }

    @PostMapping("/product-form-process")
    public String productFormProcess(AddOrEditProductDto addOrEditProductDto) {
        Product product = new Product();
        product.setId(addOrEditProductDto.getId());
        product.setProductName(addOrEditProductDto.getProductName());
        product.setDescription(addOrEditProductDto.getDescription());
        try {
            product.setImage(addOrEditProductDto.getImage().getBytes());
        } catch (IOException e) {
            return "error";
        }
        productRepository.save(product);

        return "redirect:/";
    }
}

