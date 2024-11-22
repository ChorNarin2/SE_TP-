package chornarin.com.kh.TP_Operating_System.ServiceImpl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chornarin.com.kh.TP_Operating_System.Repository.ProductRepository;
import chornarin.com.kh.TP_Operating_System.models.Product;
import jakarta.annotation.PostConstruct;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void init() {
        List<Product> product = productRepository.findAll();
        if(product.isEmpty()){
            addsamepleProduct();
        }
    }
    public String addsamepleProduct() {
        try {
            List<Product> sampleProducts = Arrays.asList(
                new Product( "P001", "Laptop", "USA", 750.00, "/images/laptop.png", "A high-performance laptop"),
                new Product( "P002", "Smartphone", "China", 500.00, "/images/smartphone.png", "A sleek smartphone"),
                new Product( "P003", "Tablet", "Korea", 300.00, "/images/tablet.png", "A lightweight tablet")
            );
            productRepository.saveAll(sampleProducts);
            return "Add successful";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to add products: " + e.getMessage();
        }
    }
    
}
