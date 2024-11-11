package com.Revshop;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
import java.util.List;
import java.util.Optional;
 
import javax.servlet.http.HttpSession;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
 
import com.Revshop.controllers.AdminController;
import com.Revshop.models.Category;
import com.Revshop.models.HelpDesk;
import com.Revshop.models.Order;
import com.Revshop.models.OrderDetails;
import com.Revshop.models.Product;
import com.Revshop.service.CategoryService;
import com.Revshop.service.CustomerService;
import com.Revshop.service.HelpDeskService;
import com.Revshop.service.OrderDetailsService;
import com.Revshop.service.OrderService;
import com.Revshop.service.ProductService;
 
@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {
 
    @Mock
    private CategoryService catsrv;
 
    @Mock
    private ProductService prodsrv;
 
    @Mock
    private CustomerService custsrv;
 
    @Mock
    private OrderService ordersrv;
 
    @Mock
    private OrderDetailsService odsrv;
 
    @Mock
    private HelpDeskService hdsrv;
 
    @Mock
    private HttpSession session;
 
    @Mock
    private Model model;
 
    @InjectMocks
    private AdminController adminController;
 
    @BeforeEach
    void setup() {

    }
 
    @Test
    void dashboard_ShouldReturnDashboardView() {
        when(custsrv.allCustomers()).thenReturn(List.of());
        when(prodsrv.allProducts()).thenReturn(List.of());
        when(ordersrv.allOrders()).thenReturn(List.of());
 
        String viewName = adminController.dashboard(model);
 
        assertEquals("dashboard", viewName);
        verify(model).addAttribute("totalusers", 0);
        verify(model).addAttribute("totalproducts", 0);
        verify(model).addAttribute("totalcategories", 5);
        verify(model).addAttribute("totalorders", 0);
    }
 
    @Test
    void confirmOrder_ShouldRedirectToOrdersAndSetSessionMessage() {
        int orderId = 1;
 
        String viewName = adminController.confirmOrder(orderId);
 
        assertEquals("redirect:/orders", viewName);
        verify(ordersrv, times(1)).confirmOrder(orderId);
        verify(session).setAttribute("msg", "Order Confirmed successfully");
    }
 
    @Test
    void products_ShouldReturnProductsView() {
        when(prodsrv.allProducts()).thenReturn(List.of(new Product()));
        when(catsrv.getAllCategories()).thenReturn(List.of(new Category()));
 
        String viewName = adminController.products(model);
 
        assertEquals("products", viewName);
        verify(model).addAttribute("prods", List.of(new Product()));
        verify(model).addAttribute("totalprods", 1);
        verify(model).addAttribute("cats", List.of(new Category()));
    }
 
    @Test
    void tickets_ShouldReturnTicketsViewAndPopulateModel() {
        when(hdsrv.getAllRequest()).thenReturn(List.of(new HelpDesk()));
 
        String viewName = adminController.helpdesk(model);
 
        assertEquals("tickets", viewName);
        verify(model).addAttribute("items", List.of(new HelpDesk()));
    }
 
    @Test
    void logout_ShouldInvalidateSessionAndRedirect() {
        String viewName = adminController.logout();
 
        assertEquals("redirect:/", viewName);
        verify(session, times(1)).invalidate();
    }
 
    @Test
    void orderdetails_ShouldReturnOrderDetailsViewWithOrderInfo() {
        int orderId = 1;
        Order mockOrder = new Order();
        List<OrderDetails> mockOrderDetailsList = List.of(new OrderDetails());
 
        when(ordersrv.getOrderDetails(orderId)).thenReturn(mockOrder);
        when(odsrv.allItemsinOrder(orderId)).thenReturn(mockOrderDetailsList);
        when(catsrv.getAllCategories()).thenReturn(List.of(new Category()));
 
        String viewName = adminController.orderdetails(orderId, model);
 
        assertEquals("orderdetails", viewName);
        verify(model).addAttribute("o", mockOrder);
        verify(model).addAttribute("items", mockOrderDetailsList);
        verify(model).addAttribute("cqty", mockOrderDetailsList.size());
        verify(model).addAttribute("cats", List.of(new Category()));
    }
}
