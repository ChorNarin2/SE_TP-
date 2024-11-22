package chornarin.com.kh.TP_Operating_System.Controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import chornarin.com.kh.TP_Operating_System.Repository.ProductRepository;
import chornarin.com.kh.TP_Operating_System.models.Product;
import lombok.RequiredArgsConstructor;


import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/TP05")
@RequiredArgsConstructor
public class ProductController {


    private final ProductRepository productRepository;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

    private final String locationUpload = "images/";
    private final String routeUI="products/includes";

    @GetMapping("/form-create")
    public String getForm(Model model) {
        model.addAttribute("formProduct", new Product());
        model.addAttribute("content", routeUI+"/form");
        model.addAttribute("title", "Form Create");
        return "layout";
    }
    
    @PostMapping("/save")
    public String postProduct(
            @RequestParam("name") String name,
            @RequestParam("descriptions") String description,
            @RequestParam("cost") double price,
            @RequestParam("image") MultipartFile image,
            @RequestParam("code") String code,
            @RequestParam("country") String country, Model model) {
        String imageUrl = "";
        if (!image.isEmpty()) {
            try {
                Path imagePath = Paths.get(locationUpload + image.getOriginalFilename());
                Files.createDirectories(imagePath.getParent());
                Files.write(imagePath, image.getBytes());
                imageUrl = "products/files/" + image.getOriginalFilename();
            } catch (Exception e) {
                System.out.println(e.getMessage()); 
            }
        }
        productRepository.save(new Product(name, country,  code, price, imageUrl, description));
        model.addAttribute("successMessage", "Product has been successfully submitted!");
        return "redirect:form-create";
    }
    @GetMapping("Task-1")
    public String  getproduct(Model model){
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "product";
    }


     // GET method to load the edit product page
     @GetMapping("/edit/{id}")
     public String editProductForm(@PathVariable Long id, Model model) {
         Optional<Product> productOpt = productRepository.findById(id);
         if (productOpt.isPresent()) {
             model.addAttribute("product", productOpt.get());
             return "editProduct"; // Name of the Thymeleaf template for editing products
         } else {
             model.addAttribute("error", "Product not found");
             return "error"; // Redirect to an error page if product not found
         }
     }


     // POST method to handle product updates
    @PostMapping("/Task-3/update")
    public String updateProduct(
            @RequestParam("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("descriptions") String description,
            @RequestParam("cost") double cost,
            @RequestParam("image") MultipartFile image,
            @RequestParam("code") String code,
            @RequestParam("country") String country,
            Model model) {
        Optional<Product> productOpt = productRepository.findById(id);

        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            product.setName(name);
            product.setDescriptions(description);
            product.setCost(cost);
            product.setCode(code);
            product.setCountry(country);

            if (!image.isEmpty()) {
                try {
                    Path imagePath = Paths.get(locationUpload + image.getOriginalFilename());
                    Files.createDirectories(imagePath.getParent());
                    Files.write(imagePath, image.getBytes());
                    product.setImage("products/files/" + image.getOriginalFilename());
                } catch (Exception e) {
                    model.addAttribute("error", "Failed to upload image");
                    return "editProduct";
                }
            }
            productRepository.save(product);
            model.addAttribute("message", "Product updated successfully");
            return "redirect:/TP05/Task-1"; 
        } else {
            model.addAttribute("error", "Product not found");
            return "error"; 
        }
    }

    @GetMapping("/files/{file:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable("file") String filename) {
        Path file = Paths.get(locationUpload).resolve(filename);
        try {
            Resource resource = new UrlResource(file.toUri());
            if (resource.isReadable() || resource.exists()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            }
        } catch (Exception e) {
        }
        return ResponseEntity.notFound().build();
    }

    
}
