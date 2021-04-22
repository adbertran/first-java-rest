package com.adb.persistence;

import com.adb.domain.Orders;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DaoServiceTest {

    @Test
    public void testCreateAndGetOrder() {
        Orders order = new Orders();
        order.setOrderId(1L);
        order.setBuyerId(2L);
        order.setSellerId(3L);
        order.setSiteId("MLA");
        order.setItemId("MLA123");

        DaoService.INSTANCE.merge(order);

        Orders orderFound = DaoService.INSTANCE.getOrder(1L);

        assertNotNull(orderFound);
        assertEquals(2L, orderFound.getBuyerId());
    }

}