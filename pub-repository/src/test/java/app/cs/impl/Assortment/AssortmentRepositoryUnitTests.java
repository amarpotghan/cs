package app.cs.impl.assortment;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cs.data.core.nosql.mongodb.MongoRepository;

import app.cs.helper.Finder;
import app.cs.impl.assortment.AssortmentRepository;
import app.cs.impl.chapter.InMemoryViewStructure;
import app.cs.interfaces.model.Assortment;
import app.cs.interfaces.model.MultiDimensionalObject;
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

	@Mock
	private Finder finder;

	String path = "Cp01,Mp01,test01";

	MultiDimensionalObject test;

	private MultiDimensionalObject publication;

	@Before
	public void setUp() {

		assortmentRepository = new AssortmentRepository(mongoRepository, finder);
		publication = new MultiDimensionalObject("Test", "publication",
				"A,B,C,D,E", true);
		test = new MultiDimensionalObject("test01", "chapter",
				"A,B,C,D,E,publication", false);
		test.addchild(new MultiDimensionalObject("test03", "test", "test", true));
		publication.addchild(test);
		publication.addchild(new MultiDimensionalObject("test02", "test", "A",
				true));
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

		System.out.println(publication);
		// when
		when(finder.getParentPublication(path)).thenReturn(publication);
		when(finder.getParentId(path)).thenReturn("test01");

		when(finder.find(publication, "test01")).thenReturn(test);
		test.addAssortment(assortment);
		assortmentRepository.save(assortment, path);

		// then

		verify(finder).find(publication, "test01");
		verify(finder).getParentPublication(path);
		verify(mongoRepository).save(test);

	}

}
