package com.adb.persistence;

import com.adb.domain.Orders;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DaoServiceTest {

    @Test
    public void testCreateGetDeleteOrder() {
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

        orderFound.setBuyerId(55L);
        DaoService.INSTANCE.merge(orderFound);

        Orders orderUpdated = DaoService.INSTANCE.getOrder(1L);

        assertNotNull(orderUpdated);
        assertEquals(55L, orderFound.getBuyerId());

        DaoService.INSTANCE.deleteOrder(1L);

        orderFound = DaoService.INSTANCE.getOrder(1L);

        assertNull(orderFound);

    }

}