package app.cs.impl.assortment;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cs.data.core.nosql.mongodb.MongoRepository;

import app.cs.impl.assortment.AssortmentRepository;
import app.cs.impl.chapter.InMemoryViewStructure;
import app.cs.impl.helper.Finder;
import app.cs.impl.model.Assortment;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.impl.model.Product;
import app.cs.interfaces.assortment.IAssortmentRepository;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AssortmentRepositoryUnitTests {

	IAssortmentRepository assortmentRepository;

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
		when(finder.getParentId(path)).thenReturn("test01");
		when(finder.getPublicationId(path)).thenReturn("test001");
		when(
				mongoRepository.getObjectByKey("test001",
						MultiDimensionalObject.class)).thenReturn(publication);
		when(finder.find(publication, "test01")).thenReturn(test);
		test.addAssortment(assortment);
		assortmentRepository.save(assortment, path);

		// then

		verify(finder).find(publication, "test01");
		verify(mongoRepository).save(test);

	}

	@Test
	public void itShouldCopyAssortmentFromOneLocationToOther() {

		String newPath = "A,B,C,D,E,test01";

		Assortment assortment = assortmentRepository.getAssortmentObject();
		System.out.println(publication);
		// when
		when(finder.getParentId(newPath)).thenReturn("test01");
		when(finder.getPublicationId(newPath)).thenReturn("test001");
		when(
				mongoRepository.getObjectByKey("test001",
						MultiDimensionalObject.class)).thenReturn(publication);
		when(finder.find(publication, "test01")).thenReturn(test);
		test.addAssortment(assortment);
		assortmentRepository.save(assortment, newPath);

		// then

		verify(finder).find(publication, "test01");
		verify(mongoRepository).save(test);
		System.out.println(test.getName());
		assertEquals((test.getName()), "test01");

	}
}
