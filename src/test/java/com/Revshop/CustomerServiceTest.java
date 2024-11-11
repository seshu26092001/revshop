package com.Revshop;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
 
import com.Revshop.exception.CustomerNotFoundException;
import com.Revshop.models.Customer;
import com.Revshop.repos.CustomerRepository;
import com.Revshop.service.CustomerService;
 
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
 
    @Mock
    private CustomerRepository crepo;
 
    @InjectMocks
    private CustomerService customerService;
 
    private Customer customer;
 
    @BeforeEach
    void setup() {
        customer = new Customer();
        customer.setUserid("user1");
        customer.setPwd("Balaji@1234");
        // Initialize other necessary fields of Customer if needed
    }
 
    @Test
    void allCustomers_ShouldReturnAllCustomers() {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        when(crepo.findAll()).thenReturn(customerList);
 
        List<Customer> result = customerService.allCustomers();
 
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("user1", result.get(0).getUserid());
        verify(crepo, times(1)).findAll();
    }
 
    @Test
    void findByUserId_ShouldReturnCustomer_WhenUserExists() {
        when(crepo.findById("user1")).thenReturn(Optional.of(customer));
 
        Customer result = customerService.findByUserId("user1");
 
        assertNotNull(result);
        assertEquals("user1", result.getUserid());
        verify(crepo, times(1)).findById("user1");
    }
 
    @Test
    void findByUserId_ShouldThrowException_WhenUserDoesNotExist() {
        when(crepo.findById("nonexistent")).thenReturn(Optional.empty());
 
        assertThrows(CustomerNotFoundException.class, () -> {
            customerService.findByUserId("nonexistent");
        });
 
        verify(crepo, times(1)).findById("nonexistent");
    }
 
    @Test
    void updatePassword_ShouldReturnTrue_WhenPasswordUpdatedSuccessfully() {
        when(crepo.findById("user1")).thenReturn(Optional.of(customer));
 
        boolean result = customerService.updatePassword("user1", "Inida@123", "Balaji@1234");
 
        assertTrue(result);
        assertEquals("Inida@123", customer.getPwd());
        verify(crepo, times(1)).save(customer);
    }
 
    @Test
    void updatePassword_ShouldReturnFalse_WhenOldPasswordIsIncorrect() {
        when(crepo.findById("user1")).thenReturn(Optional.of(customer));
 
        boolean result = customerService.updatePassword("user1", "India@123", "hero");
 
        assertFalse(result);
        verify(crepo, times(0)).save(customer); // Should not save if old password is incorrect
    }
 
    @Test
    void ValidateLogin_ShouldReturnCustomer_WhenCredentialsAreCorrect() {
        when(crepo.findById("user1")).thenReturn(Optional.of(customer));
 
        Customer result = customerService.ValidateLogin("user1", "Balaji@1234");
 
        assertNotNull(result);
        assertEquals("user1", result.getUserid());
        verify(crepo, times(1)).findById("user1");
    }
 
    @Test
    void ValidateLogin_ShouldReturnNull_WhenCredentialsAreIncorrect() {
        when(crepo.findById("user1")).thenReturn(Optional.of(customer));
 
        Customer result = customerService.ValidateLogin("user1", "india@321");
 
        assertNull(result);
        verify(crepo, times(1)).findById("user1");
    }
 
    @Test
    void VerifyUser_ShouldReturnOk_WhenUserDoesNotExist() {
        when(crepo.findById("nonexistent")).thenReturn(Optional.empty());
 
        String result = customerService.VerifyUser("nonexistent");
 
        assertEquals("ok", result);
        verify(crepo, times(1)).findById("nonexistent");
    }
 
    @Test
    void VerifyUser_ShouldReturnNo_WhenUserExists() {
        when(crepo.findById("user1")).thenReturn(Optional.of(customer));
 
        String result = customerService.VerifyUser("user1");
 
        assertEquals("no", result);
        verify(crepo, times(1)).findById("user1");
    }
 
    @Test
    void saveCustomer_ShouldReturnSavedCustomer() {
        when(crepo.save(customer)).thenReturn(customer);
 
        Customer result = customerService.saveCustomer(customer);
 
        assertNotNull(result);
        assertEquals("user1", result.getUserid());
        verify(crepo, times(1)).save(customer);
    }
 
    @Test
    void changePassword_ShouldUpdatePasswordSuccessfully() {
        when(crepo.findById("user1")).thenReturn(Optional.of(customer));
 
        customerService.changePassword("user1", "Inida@123");
 
        assertEquals("Inida@123", customer.getPwd());
        verify(crepo, times(1)).save(customer);
    }
 
    @Test
    void checkPassword_ShouldReturnTrue_WhenPasswordMatches() {
        when(crepo.findById("user1")).thenReturn(Optional.of(customer));
 
        boolean result = customerService.checkPassword("user1", "Balaji@1234");
 
        assertTrue(result);
        verify(crepo, times(1)).findById("user1");
    }
 
    @Test
    void checkPassword_ShouldReturnFalse_WhenPasswordDoesNotMatch() {
        when(crepo.findById("user1")).thenReturn(Optional.of(customer));
 
        boolean result = customerService.checkPassword("user1", "hero@123");
 
        assertFalse(result);
        verify(crepo, times(1)).findById("user1");
    }
}