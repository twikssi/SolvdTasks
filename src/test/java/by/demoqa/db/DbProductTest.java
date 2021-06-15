package by.demoqa.db;

import by.demoqa.beans.Product;
import by.demoqa.beans.Productline;
import by.demoqa.db.mappers.ProductMapper;
import by.demoqa.util.ConnectionFactory;
import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class DbProductTest extends AbstractTest {
    private static final Logger log = LoggerFactory.getLogger(DbProductTest.class);
    private Productline productline;
    private Product product;

    @BeforeClass
    private void setup() {
        productline = new Productline();
        productline.setProductLine("Classic Cars");
        productline.setTextDescription("Attention car enthusiasts: Make your wildest car ownership dreams come true. Whether you are looking for classic muscle cars, dream sports cars or movie-inspired miniatures, you will find great choices in this category. These replicas feature superb attention to detail and craftsmanship and offer features such as working steering system, opening forward compartment, opening rear trunk with removable spare wheel, 4-wheel independent spring suspension, and so on. The models range in size from 1:10 to 1:24 scale and include numerous limited edition and several out-of-production vehicles. All models include a certificate of authenticity from their manufacturers and come fully assembled and ready for display in the home or office.");

        product = Product.builder().
                productCode("S66_6666").
                productName("BelarusCar").
                productLine(productline).
                productScale("1:350").
                productVendor("here is vendor").
                productDescription("literally fast car").
                quantityInStock(6).
                buyPrice(66.33).
                MSRP(50.1).
                build();

    }

    @Test()
    @MethodOwner(owner = "Andrew")
    public void testGetAllByProductLine() {
        int expectedSize = 38;
        try (SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession(true)) {
            ProductMapper productMapper = session.getMapper(ProductMapper.class);

            List<Product> productList = productMapper.getAllByProductLine(productline);
            productList.forEach(a -> log.info(a.toString()));
            Assert.assertEquals(productList.size(), expectedSize, "Number of product doesn't match");
        }
    }

    @Test
    @MethodOwner(owner = "Andrew")
    public void testGetByProductCode() {
        String expectedProductCode = "S18_1367";
        try (SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession(true)) {
            ProductMapper productMapper = session.getMapper(ProductMapper.class);

            Product prod = productMapper.getByProductCode(expectedProductCode);
            Assert.assertTrue(productMapper.getAll().contains(prod), "Product isn't present");
        }
    }

    @Test
    @MethodOwner(owner = "Andrew")
    public void testGetAllByPriceRange() {
        double expectedPriceFrom = 20d;
        double expectedPriceTo = 25d;
        try (SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession(true)) {
            ProductMapper productMapper = session.getMapper(ProductMapper.class);

            List<Product> productList = productMapper.getAllByRangePrice(expectedPriceFrom, expectedPriceTo);
            productList.forEach(a -> log.info(a.toString()));
            boolean actualResult = productList.stream().allMatch(a -> a.getBuyPrice() > expectedPriceFrom && a.getBuyPrice() < expectedPriceTo);
            Assert.assertTrue(actualResult, "Number of range price doesn't match");
        }
    }

    @Test
    @MethodOwner(owner = "Andrew")
    public void testCreateProduct() {
        try (SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession(true)) {
            ProductMapper productMapper = session.getMapper(ProductMapper.class);
            productMapper.createProduct(product);
            boolean isProductPresent = productMapper.getAll().contains(product);
            Assert.assertTrue(isProductPresent, "New Product isn't present");
        }
    }

    @Test(dependsOnMethods = "testCreateProduct")
    @MethodOwner(owner = "Andrew")
    public void testUpdateProduct() {
        String name = "Mustache car";
        try (SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession(true)) {
            ProductMapper productMapper = session.getMapper(ProductMapper.class);
            Product prod = new Product();
            prod.setProductName(name);
            prod.setProductCode(product.getProductCode());
            productMapper.updateProduct(prod);

            List<Product> allProduct = productMapper.getAll();
            Assert.assertFalse(allProduct.contains(product), "Old product is present");

        }
    }

    @Test(dependsOnMethods = "testCreateProduct")
    @MethodOwner(owner = "Andrew")
    public void testDeleteProduct() {
        try (SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession(true)) {
            ProductMapper productMapper = session.getMapper(ProductMapper.class);
            productMapper.deleteProduct(product.getProductCode());

            boolean isProductPresent = productMapper.getAll().contains(product);
            Assert.assertFalse(isProductPresent, "Product is present");
        }
    }


}
