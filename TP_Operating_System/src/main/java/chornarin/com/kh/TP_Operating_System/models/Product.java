package chornarin.com.kh.TP_Operating_System.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Product") 
@Data
@NoArgsConstructor 
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Renamed to lowercase "id" for consistency

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private double cost;

    @Column(nullable = false)
    private String image;

    @Column(length = 500) 
    private String descriptions;

    public Product(String code, String name, String country, double cost, String image, String descriptions) {
        this.code = code;
        this.name = name;
        this.country = country;
        this.cost = cost;
        this.image = image;
        this.descriptions = descriptions;
    }
}
