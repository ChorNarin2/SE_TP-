package chornarin.com.kh.TP_Operating_System.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chornarin.com.kh.TP_Operating_System.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
