package by.demoqa.db.mappers;

import by.demoqa.beans.Product;
import by.demoqa.beans.Productline;

import java.util.List;

public interface ProductMapper {
    List<Product> getAll();

    Product getByProductCode(String productCode);

    List<Product> getAllByProductLine(Productline productline);

    List<Product> getAllByRangePrice(double priceFrom, double priceTo);

    void createProduct(Product product);

    void deleteProduct(String productCode);

    void updateProduct(Product product);
}
