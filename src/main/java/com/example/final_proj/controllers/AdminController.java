package com.example.final_proj.controllers;

import com.example.final_proj.enumm.Status;
import com.example.final_proj.models.Category;
import com.example.final_proj.models.Image;
import com.example.final_proj.models.Order;
import com.example.final_proj.models.Product;
import com.example.final_proj.repository.CategoryRepository;
import com.example.final_proj.repository.ImageRepository;
import com.example.final_proj.repository.OrderRepository;
import com.example.final_proj.services.ImageService;
import com.example.final_proj.services.OrderService;
import com.example.final_proj.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class AdminController {
    private final ImageRepository imageRepository;
    private final ProductService productService;
    private final ImageService imageService;

    private final OrderService orderService;

    private final OrderRepository orderRepository;

    @Value("${upload.path}")
    private String uploadPath;
    private CategoryRepository categoryRepository;

    public AdminController(ImageRepository imageRepository, ProductService productService, ImageService imageService, OrderRepository orderRepository, OrderService orderService, OrderRepository orderRepository1, CategoryRepository categoryRepository) {
        this.imageRepository = imageRepository;
        this.productService = productService;
        this.imageService = imageService;
        this.orderService = orderService;
        this.orderRepository = orderRepository1;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/admin/product/add")
    public String addProduct(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("category", categoryRepository.findAll());
        return "product/addProduct";
    }
    @PostMapping("/admin/product/add")
    public String addProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, @RequestParam("file_one")MultipartFile file_one, @RequestParam("file_two")MultipartFile file_two, @RequestParam("file_three")MultipartFile file_three, @RequestParam("file_four")MultipartFile file_four, @RequestParam("file_five")MultipartFile file_five, @RequestParam("category") int category, Model model) throws Exception{
        Category category_db=categoryRepository.findById(category).orElseThrow();
        System.out.println(category_db.getName());
        if(bindingResult.hasErrors()){
            model.addAttribute("category", categoryRepository.findAll());
            return "product/addProduct";
        }
        if(file_one!=null){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName=uuidFile+"."+file_one.getOriginalFilename();
            file_one.transferTo(new File(uploadPath+"/"+resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }

        if(file_two!=null){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName=uuidFile+"."+file_two.getOriginalFilename();
            file_two.transferTo(new File(uploadPath+"/"+resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }

        if(file_three!=null){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName=uuidFile+"."+file_three.getOriginalFilename();
            file_three.transferTo(new File(uploadPath+"/"+resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }

        if(file_four!=null){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName=uuidFile+"."+file_four.getOriginalFilename();
            file_four.transferTo(new File(uploadPath+"/"+resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }
        if(file_five!=null){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName=uuidFile+"."+file_five.getOriginalFilename();
            file_five.transferTo(new File(uploadPath+"/"+resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }

        productService.saveProduct(product, category_db);

        return "redirect:/admin";
    }

    @GetMapping("/admin")
    public String admin(Model model){
        model.addAttribute("products", productService.getAllProduct());
        return "admin";
    }
    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id){
        productService.deleteProduct(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/product/edit/{id}")
    public String editProduct(Model model, @PathVariable("id") int id){
        model.addAttribute("product", productService.getProduct(id));
        model.addAttribute("category", categoryRepository.findAll());
        return "product/editProduct";

    }

    @PostMapping("/admin/product/edit/{id}")
    public String editProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, @PathVariable("id") int id, @RequestParam("file_one")MultipartFile file_one, @RequestParam("file_two")MultipartFile file_two, @RequestParam("file_three")MultipartFile file_three, @RequestParam("file_four")MultipartFile file_four, @RequestParam("file_five")MultipartFile file_five, Model model) throws IOException {
        if(bindingResult.hasErrors()){
            model.addAttribute("category", categoryRepository.findAll());
            return "product/editProduct";
        }

            if((!file_one.getOriginalFilename().isEmpty())){
                File uploadDir = new File(uploadPath);
                if(!uploadDir.exists()){
                    uploadDir.mkdir();
                }
                imageService.deleteImage(productService.getProduct(id).getImageList().get(0).getId());
                String uuidFile = UUID.randomUUID().toString();
                String resultFileName=uuidFile+"."+file_one.getOriginalFilename();
                file_one.transferTo(new File(uploadPath+"/"+resultFileName));
                Image image = new Image();
                image.setProduct(product);
                image.setFileName(resultFileName);
                product.addImageToProduct(image);
            }

        if(!file_two.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            imageService.deleteImage(productService.getProduct(id).getImageList().get(1).getId());
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName=uuidFile+"."+file_two.getOriginalFilename();
            file_two.transferTo(new File(uploadPath+"/"+resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }

        if(!file_three.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            imageService.deleteImage(productService.getProduct(id).getImageList().get(2).getId());
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName=uuidFile+"."+file_three.getOriginalFilename();
            file_three.transferTo(new File(uploadPath+"/"+resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }

        if(!file_four.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            imageService.deleteImage(productService.getProduct(id).getImageList().get(3).getId());
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName=uuidFile+"."+file_four.getOriginalFilename();
            file_four.transferTo(new File(uploadPath+"/"+resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }
        if(!file_five.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            imageService.deleteImage(productService.getProduct(id).getImageList().get(4).getId());
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName=uuidFile+"."+file_five.getOriginalFilename();
            file_five.transferTo(new File(uploadPath+"/"+resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }

        productService.updateProduct(id, product);



        return "redirect:/admin";
    }

    @GetMapping("admin/orders")
    public String orderUser(Model model){
        model.addAttribute("status", Status.values());
        model.addAttribute("orders", orderRepository.findAll());
        return "/admin/orders";
    }

    @PostMapping("/admin/edit/status/{id}")
    public String editStatus(@ModelAttribute("order") Order order, @PathVariable("id") int id,  Model model) throws IOException {

        System.out.println( id+ "====================================================================================================================================================");
        System.out.println( order.getStatus()+ "====================================================================================================================================================");
        orderService.updateStatus(id, id);
//        switch (order.getStatus()){
//            case Принят:
//                orderRepository.updateStatus(1, id);
//                break;
//            case Оформлен:
//                orderRepository.updateStatus(2, id);
//                break;
//            case Ожидает:
//                orderRepository.updateStatus(3, id);
//                break;
//            case Получен:
//                orderRepository.updateStatus(4, id);
//                break;
//        }

       // orderRepository.updateStatus(id);

 //       model.addAttribute("status", Status.values());
//        model.addAttribute("orders", orderRepository.findAll());

        return "redirect:/admin/orders";
    }

    @PostMapping("/admin/order/search")
    public String productSearch(@RequestParam("search") String search, Model model){

       // model.addAttribute("value_search", search);

        model.addAttribute("search_order", orderRepository.findByName(search));
        model.addAttribute("orders", orderRepository.findAll());

        return "admin/orders";
    }


}
