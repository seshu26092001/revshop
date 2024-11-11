package com.Revshop;

import com.Revshop.controllers.HomeController;
import com.Revshop.models.*;
import com.Revshop.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
 
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
 
public class HomeControllerTest {
 
    @InjectMocks
    private HomeController homeController;
 
    @Mock
    private CategoryService catsrv;
    @Mock
    private ProductService prodsrv;
    @Mock
    private HttpSession session;
    @Mock
    private CustomerService custsrv;
    @Mock
    private CartService cartsrv;
    @Mock
    private OrderService ordersrv;
    @Mock
    private OrderDetailsService odsrv;
    @Mock
    private HelpDeskService hdsrv;
    @Mock
    private EmailService emailService;
    @Mock
    private Model model;
 
    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    void testHomepage() {
        // Mock data
        when(catsrv.getAllCategories()).thenReturn(new ArrayList<>());
        when(prodsrv.allProducts()).thenReturn(new ArrayList<>());
 
        String viewName = homeController.homepage(model);
 
        verify(model).addAttribute("cats", catsrv.getAllCategories());
        verify(model).addAttribute("prods", prodsrv.allProducts());
        assertEquals("index", viewName);
    }
 
    @Test
    void testListByCategory() {
        int categoryId = 1;
        when(catsrv.getAllCategories()).thenReturn(new ArrayList<>());
        when(prodsrv.allCategoryProducts(categoryId)).thenReturn(new ArrayList<>());
 
        String viewName = homeController.listbycategory(model, categoryId);
 
        verify(model).addAttribute("cats", catsrv.getAllCategories());
        verify(model).addAttribute("prods", prodsrv.allCategoryProducts(categoryId));
        assertEquals("catlist", viewName);
    }
 
    @Test
    void testAddToCart() {
        int productId = 1;
        Product product = new Product(); // Mock or initialize your Product object as needed
        when(catsrv.getAllCategories()).thenReturn(new ArrayList<>());
        when(prodsrv.findProductById(productId)).thenReturn(product);
 
        String viewName = homeController.addtocart(model, productId);
 
        verify(model).addAttribute("cats", catsrv.getAllCategories());
        verify(model).addAttribute("p", product);
        assertEquals("addtocart", viewName);
    }
 
    @Test
    void testSaveItemToCart_ItemAlreadyInCart() {
        Cart cart = new Cart(); // Mock or initialize your Cart object as needed
        when(session.getAttribute("userid")).thenReturn("user1");
        when(cartsrv.checkItem(cart)).thenReturn(true);
 
        String viewName = homeController.saveItemtoCart(cart, model);
 
        verify(session).setAttribute("error", "Item already in cart");
        assertEquals("redirect:/", viewName);
    }
 
    @Test
    void testSaveItemToCart_ItemAddedSuccessfully() {
        Cart cart = new Cart(); // Mock or initialize your Cart object as needed
        when(session.getAttribute("userid")).thenReturn("user1");
        when(cartsrv.checkItem(cart)).thenReturn(false);
        when(cartsrv.getItemsinCart("user1")).thenReturn(1);
 
        String viewName = homeController.saveItemtoCart(cart, model);
 
        verify(cartsrv).saveItem(cart);
        verify(model).addAttribute("cqty", cartsrv.getItemsinCart("user1"));
        verify(session).setAttribute("msg", "Item added to cart");
        assertEquals("redirect:/", viewName);
    }
 
    @Test
    void testInvoice() {
        int orderId = 1;
        Order order = new Order(); // Mock or initialize your Order object as needed
        List<OrderDetails> orderDetailsList = new ArrayList<>();
 
        when(ordersrv.getOrderDetails(orderId)).thenReturn(order);
        when(odsrv.allItemsinOrder(orderId)).thenReturn(orderDetailsList);
        when(catsrv.getAllCategories()).thenReturn(new ArrayList<>());
 
        String viewName = homeController.invoice(orderId, model);
 
        verify(model).addAttribute("cats", catsrv.getAllCategories());
        verify(model).addAttribute("o", order);
        verify(model).addAttribute("items", orderDetailsList);
        assertEquals("invoice", viewName);
    }
 
    @Test
    void testPlaceOrder() {
        Order order = new Order(); // Mock or initialize your Order object as needed
        when(session.getAttribute("userid")).thenReturn("user1");
        when(ordersrv.placeOrder(order, "user1")).thenReturn(1);
        when(cartsrv.getItemsinCart("user1")).thenReturn(0);
 
        String viewName = homeController.placeorder(order, model);
 
        verify(model).addAttribute("cqty", cartsrv.getItemsinCart("user1"));
        verify(session).setAttribute("msg", "Order Placed Successfully");
        assertEquals("redirect:/invoice/1", viewName);
    }
 
    @Test
    void testOrderHistory() {
        when(session.getAttribute("userid")).thenReturn("user1");
        when(catsrv.getAllCategories()).thenReturn(new ArrayList<>());
        when(ordersrv.allUserOrders("user1")).thenReturn(new ArrayList<>());
 
        String viewName = homeController.orderhistory(model);
 
        verify(model).addAttribute("cats", catsrv.getAllCategories());
        verify(model).addAttribute("orders", ordersrv.allUserOrders("user1"));
        assertEquals("history", viewName);
    }
 
    // Additional tests for other methods can be added here
}
