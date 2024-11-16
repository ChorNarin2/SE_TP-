package chornarin.com.kh.TP_Operating_System.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.core.model.Model;
import chornarin.com.kh.TP_Operating_System.models.Product;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/TP05")
public class ProductConntroller {

    @GetMapping("/task1")
    public String getProducts(Model model){
        // model.addAttribute("products", new Product());
        // model.addAttribute("products", "products");
        return "products/product";
    }

    @PostMapping("path")
    public String postMethodName(@RequestBody String entity) {
        
        return entity;
    }
    

}
