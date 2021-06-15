package by.demoqa.db.mappers;

import by.demoqa.beans.Product;
import by.demoqa.beans.Productline;

import java.util.List;

public interface ProductlineMapper {
    List<Productline> getAll();

    Productline findByProductLine(String productLine);

    void createProductline(Productline productline);

    void deleteProductline(String productLine);

    void updateProductline(Productline productline);
}
