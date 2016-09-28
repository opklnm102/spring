package me.dong.repository;

import me.dong.DaoJpaApplication;
import me.dong.domain.Product;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = DaoJpaApplication.class)
public class ProductRepositoryTest {

    @Autowired
    ProductRepository repository;

    @Autowired
    Environment env;

    Log log = LogFactory.getLog(ProductRepositoryTest.class);

    @Test
    public void testFindAll(){
        List<Product> products = repository.findAll();
        assertThat(products.size(), is(5));
    }

}
