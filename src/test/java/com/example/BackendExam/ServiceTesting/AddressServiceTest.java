package com.example.BackendExam.ServiceTesting;
import com.example.BackendExam.model.Address;
import com.example.BackendExam.repository.AddressRepository;
import com.example.BackendExam.service.AddressService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    @Test
    public void testFindAll() {
        Address address1 = new Address();
        Address address2 = new Address();
        List<Address> addresses = Arrays.asList(address1, address2);

        when(addressRepository.findAll()).thenReturn(addresses);
        List<Address> result = addressService.findAll();

        assertEquals(2, result.size());
        verify(addressRepository, times(1)).findAll();
    }
}
