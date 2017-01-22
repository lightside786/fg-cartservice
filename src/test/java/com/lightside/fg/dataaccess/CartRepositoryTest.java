package com.lightside.fg.dataaccess;

import com.lightside.fg.domain.Cart;
import com.lightside.fg.repository.ICartRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Sql(scripts = "classpath:dataCart.sql", config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.DEFAULT))
public class CartRepositoryTest {

    @Autowired
    ICartRepository cartRepository;

    @Test
    public void checkMethodFindByRecordId() {

        Cart cart = cartRepository.findByRecordId("a9eb8e75-45f8-41e4-ab80-e02b6567a758");

        Assert.assertNotNull("cart table should have values inserted for cart", cart);

        Assert.assertTrue("cart key should be the same", cart.getId() == 1);
    }


}
