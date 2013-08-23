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

	@Mock
	private Assortment assortment;

	String path = "Cp01,Mp01,P01";

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

		// when
		assortmentRepository.save(assortment, path);

		// then
		verify(mongoRepository).save(assortment);

	}

	@Test
	public void itShouldSaveAssortmentUnderGivenPublication() {
		// given

		String path = "CP01,MP01,P01";
		Assortment assortment = new Assortment();

		// when
		assortmentRepository.save(assortment, path);

	}
}
