package chornarin.com.kh.TP_Operating_System.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Product") 
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column()
    private String code;

    @Column()
    private String name;

    @Column()
    private String country;

    @Column()
    private double cost;

    @Column()
    private String image;

    @Column()
    private String descriptions;

}
