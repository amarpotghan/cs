package app.cs.impl.assortment;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.impl.delegate.factory.DomainFactory;
import app.cs.impl.helper.Finder;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.impl.model.Product;

import com.cs.data.core.nosql.mongodb.MongoRepository;

@RunWith(MockitoJUnitRunner.class)
public class AssortmentRepositoryUnitTests {

	private AssortmentRepository repository;

	@Mock
	private MongoRepository noSqlTemplateForMongo;

	@Mock
	private Finder finder;

	@Mock
	private DomainFactory factory;

	MultiDimensionalObject publication;

	MultiDimensionalObject test;
	MultiDimensionalObject assortmentTest;
	Product iPad;
	Product iPhone;

	@Before
	public void setUp() {
		repository = new AssortmentRepository(noSqlTemplateForMongo, factory,
				finder);
		publication = new MultiDimensionalObject("Test", "publication",
				"A,B,C,D,E", true);
		test = new MultiDimensionalObject("test01", "chapter",
				"A,B,C,D,E,publication", false);
		test.addchild(new MultiDimensionalObject("test03", "test", "test", true));
		assortmentTest = new MultiDimensionalObject("assortmentContainer",
				"publication", "A,B,C,D", true);
		publication.addchild(test);
		publication.addchild(new MultiDimensionalObject("test02", "test", "A",
				true));
		iPad = new Product("iPad1155", "iPad nano", "electronics");
		iPhone = new Product("iPhone1155", "iPhone nano", "electronics");

	}

	@Test
	public void itShouldCreateAssortmentInTheParentPublication() {
		// given
		MultiDimensionalObject assortment = new MultiDimensionalObject("test",
				"test", "A,B,C,D,E,test01", true);
		List<Product> products = new ArrayList<Product>();
		products.add(iPhone);
		products.add(iPad);
		assortment.setProducts(products);

		String result = "success";
		// when
		when(noSqlTemplateForMongo.save(publication)).thenReturn(result);
		when(finder.getPublicationId(assortment.getPath())).thenReturn("D");
		when(
				noSqlTemplateForMongo.getObjectByKey("D",
						MultiDimensionalObject.class)).thenReturn(publication);

		when(finder.getParentId(assortment.getPath())).thenReturn("test01");
		when(finder.find(publication, "test01")).thenReturn(test);
		String actualResult = repository.save(assortment);

		// then
		verify(noSqlTemplateForMongo).getObjectByKey("D",
				MultiDimensionalObject.class);
		verify(noSqlTemplateForMongo).save(publication);
		assertThat(actualResult).isEqualTo(result);
		// TODO publication assortment ?
	}

	@Test
	public void itShouldCopyAssortmentFromOneLocationToOther() {
		String newPath = "A,B,C,D,E,test02";
		//Assortment isFolder ? false ?
		MultiDimensionalObject assortment = new MultiDimensionalObject(
				"test01", "test", "A,B,C,D,E,test01", true);
		List<Product> products = new ArrayList<Product>();
		products.add(iPhone);
		products.add(iPad);
		assortment.setProducts(products);
		// when

		when(finder.getPublicationId(assortment.getPath())).thenReturn("D");
		when(finder.getPublicationId(newPath)).thenReturn("D");
		when(
				noSqlTemplateForMongo.getObjectByKey("D",
						MultiDimensionalObject.class)).thenReturn(publication);
		when(finder.getParentId(assortment.getPath())).thenReturn("test01");
		when(finder.getParentId(newPath)).thenReturn("test02");
		when(finder.find(publication, "test01")).thenReturn(test);
		when(finder.find(publication, "test02")).thenReturn(assortmentTest);

		repository.copy(assortment, newPath);

		// then
		verify(noSqlTemplateForMongo).save(publication);
		// TODO publication assortment ?

	}

	@Test
	public void itShouldUpdateAssortment() {
		// given

		MultiDimensionalObject newAssortment = new MultiDimensionalObject(
				"test01", "test", "A,B,C,D,E,test01", true);
		
		List<Product> products = new ArrayList<Product>();
		products.add(iPhone);
		products.add(iPad);
		newAssortment.setProducts(products);
		
		MultiDimensionalObject oldAssortment = new MultiDimensionalObject(
				"test01", "test", "A,B,C,D,E,test01", true);
		String result = "success";

		// when
		when(noSqlTemplateForMongo.save(publication)).thenReturn(result);
		when(finder.getPublicationId(newAssortment.getPath())).thenReturn("D");
		when(
				noSqlTemplateForMongo.getObjectByKey("D",
						MultiDimensionalObject.class)).thenReturn(publication);
		when(finder.find(publication, "test01")).thenReturn(oldAssortment);
        //TODO ? verify return
		repository.updateAssortment(newAssortment);
		// then
		verify(noSqlTemplateForMongo).save(publication);
		assertThat(oldAssortment.getProducts()).isNotEmpty();
		assertThat(oldAssortment.getProducts().size()).isEqualTo(2);

	}
}
