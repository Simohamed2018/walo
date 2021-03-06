package com.mproduits.service;

import com.mproduits.dao.ProductDao;
import com.mproduits.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class) // on peut l enlver et le test fonctionne
public class ProductServiceImpTest {

    @Mock
    private ProductDao daoMock;

    @InjectMocks
    private ProductServiceImp service;

    Product p = new Product();

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        p.setId(4);
        p.setTitre("mockProduct");
    }

    @Test
    public void recupererUnProduitTest() {

        // Configure mock
        when(daoMock.findById(4)).thenReturn(p);
        // Perform the test
        Product productTest = service.recupererUnProduit(4);
        int actual = productTest.getId();

        // Junit asserts
        int expected = p.getId();
        assertEquals(expected, actual);
        //verfiy is the method is called
        verify(daoMock, Mockito.times(1)).findById(4);

    }

    @Test(expected = Exception.class)
    public void recupererUnProduit_should_return_null() throws Exception {

        // Configure mock
        doThrow(new Exception()).when(daoMock.findById(anyInt()));
        //perform the test must trow exception
        service.recupererUnProduit(4);

    }

    /*
    Mockito.verify(someMock, Mockito.never()).bla(); // same as Mockito.times(0)

    Mockito.verify(someMock, Mockito.atLeast(3)).bla(); // min 3 calls

    Mockito.verify(someMock, Mockito.atLeastOnce()).bla(); // same as Mockito.atLeast(1)

    Mockito.verify(someMock, Mockito.atMost(3)).bla(); // max 3 calls
*/
}



