package app.cs.impl.Assortment;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cs.data.core.nosql.mongodb.MongoRepository;

import app.cs.interfaces.model.Assortment;
import app.cs.interfaces.model.Product;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AssortmentRepositoryUnitTests {

	AssortmentRepository assortmentRepository;

	@Mock
	private MongoRepository mongoRepository;

	@Before
	public void setUp() {

		assortmentRepository = new AssortmentRepository(mongoRepository);
	}

	@Test
	public void itShouldReturnAssortmentObject() {
		// given

		// when
		Assortment assortment = assortmentRepository.getAssortmentObject();
		// then
		assertThat(assortment).isNotNull();

	}

	@Test
	public void itShouldSaveAssortmentToMongoDB() {

		// given
		Product iPhone = new Product();
		Product iPad = new Product();
		List<Product> products = new ArrayList<Product>();
		products.add(iPhone);
		products.add(iPad);
		Assortment assortment = new Assortment();
		assortment.setProducts(products);

		// when
		assortmentRepository.save(assortment);

		// then
		verify(mongoRepository).save(assortment);

	}

	@Test
	public void itShouldSaveAssortmentUnderGivenPublication() {
		//given
		
		path="CP01,MP01,P01";

	}
}
