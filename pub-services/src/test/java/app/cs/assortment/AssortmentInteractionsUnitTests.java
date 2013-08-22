package app.cs.assortment;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.impl.Assortment.AssortmentRepository;
import app.cs.interfaces.model.Assortment;
import app.cs.interfaces.model.Product;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AssortmentInteractionsUnitTests {

	@Mock
	AssortmentRepository assortmentRepository;

	AssortmentInteractions assortmentUseCases;

	@Before
	public void setUp() {
		assortmentUseCases = new AssortmentInteractions(assortmentRepository);
	}

	@Test
	public void itShouldCreateAnAssortment() {
		// given
		String name = "Assortment1";
		String path = "Cp01,Mp01,P01";
		String type = "Assorment";
		Product iPhone = new Product();
		Product iPad = new Product();
		List<Product> products = new ArrayList<Product>();
		products.add(iPhone);
		products.add(iPad);

		// when
		when(assortmentRepository.getAssortmentObject()).thenReturn(new Assortment());
		Assortment assortment =assortmentRepository.getAssortmentObject();
		assortment.setProducts(products);
		assortmentUseCases.create(name, path, products);

		// then
		verify(assortmentRepository).save(assortment);

	}
}
