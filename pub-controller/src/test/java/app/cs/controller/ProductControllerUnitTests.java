package app.cs.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.fest.assertions.Assertions.assertThat;

import app.cs.pim.PIMInteractions;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerUnitTests {

	private ProductController productController;

	@Mock
	private PIMInteractions productInteractions;

	@Before
	public void setUp() {
		productController = new ProductController(productInteractions);

	}

	@Test
	public void itShouldReturnProductDataFromPIM() {

		// given
		String restURLForPIM = "/list";
		String id = "62";

		String result = "success";
		// when
		when(productInteractions.getProducts(id)).thenReturn(result);

		String actualResult = productController.listForGivenId(id);
		// then

		verify(productInteractions).getProducts(id);
		assertThat(actualResult).isEqualTo(result);

	}

	@Test
	public void itShouldReturnProductDataFromPIMWhenIdIsNotGiven() {

		// given
		String restURLForPIM = "/list";

		String result = "success";
		// when
		when(productInteractions.getProducts(null)).thenReturn(result);

		String actualResult = productController.list();
		// then

		verify(productInteractions).getProducts(null);
		assertThat(actualResult).isEqualTo(result);

	}
}
