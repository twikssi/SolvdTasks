package by.demoqa.db;

import by.demoqa.beans.Productline;
import by.demoqa.db.mappers.ProductlineMapper;
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

public class DbProductlineTest extends AbstractTest {
    private static final Logger log = LoggerFactory.getLogger(DbProductlineTest.class);
    private Productline productline;

    @BeforeClass
    public void setup() {
        productline = new Productline();
        productline.setProductLine("Bicycle");
        productline.setTextDescription("3, 4 wheel bicycles are new brand in the world. It very fast gain popularity");
    }

    @Test
    @MethodOwner(owner = "Andrew")
    public void testCreateProductline() {
        try (SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession(true)) {
            ProductlineMapper productMapper = session.getMapper(ProductlineMapper.class);

            productMapper.createProductline(productline);
            Assert.assertTrue(productMapper.getAll().contains(productline), "Productline isn't present");
        }
    }

    @Test(dependsOnMethods = "testCreateProductline")
    @MethodOwner(owner = "Andrew")
    public void testUpdateProductline() {
        String description = "hello new description";
        try (SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession(true)) {
            ProductlineMapper productMapper = session.getMapper(ProductlineMapper.class);

            Productline prodline = new Productline();
            prodline.setTextDescription(description);
            prodline.setProductLine(productline.getProductLine());

            productMapper.updateProductline(prodline);
            List<Productline> allProductlines = productMapper.getAll();
            allProductlines.forEach(a -> log.info(a.toString()));
            Assert.assertFalse(allProductlines.contains(productline), "Productline is present");
            productline.setTextDescription(description);
            Assert.assertTrue(allProductlines.contains(productline), "New productline isn't present");
        }
    }

    @Test(dependsOnMethods = "testUpdateProductline")
    @MethodOwner(owner = "Andrew")
    public void testDeleteProductline() {
        try (SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession(true)) {
            ProductlineMapper productMapper = session.getMapper(ProductlineMapper.class);

            productMapper.deleteProductline(productline.getProductLine());
            Assert.assertFalse(productMapper.getAll().contains(productline), "Productline is present");
        }
    }
}
