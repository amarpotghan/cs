package app.cs.integration;

import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import app.cs.impl.delegate.factory.DomainFactory;
import app.cs.impl.dimension.DimensionRepository;
import app.cs.impl.dimension.InMemoryDimensionGroup;
import app.cs.interfaces.dimension.IDimensionRepository;
import app.cs.interfaces.dimension.IInMemoryDimensionGroup;
import app.cs.model.MultiDimensionalObject;

import com.cs.data.api.core.nosql.redis.InMemoryNoSqlRepository;
import com.cs.data.core.nosql.mongodb.MongoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context-test.xml")
public class DimensionIntegrationTests {

	@Autowired
	private MongoRepository noSqlRepository;

	List<MultiDimensionalObject> models = new ArrayList<MultiDimensionalObject>();

	private IInMemoryDimensionGroup cache;
	private IDimensionRepository dimensionRepository;


	DomainFactory factory = new DomainFactory();

	@Autowired
	private InMemoryNoSqlRepository inMemoryNosqlRepository;

	@Before
	public void setUp() {

		MultiDimensionalObject mp01 = new MultiDimensionalObject("mp01",
				"MasterPublication", "mp01", "mp01", "-1");
		MultiDimensionalObject mp012 = new MultiDimensionalObject("mp012",
				"MasterPublication", "mp012", "mp012", "-1");
		MultiDimensionalObject cp01 = new MultiDimensionalObject("cp01",
				"Campaign", "cp01", "cp01", "mp01");
		MultiDimensionalObject cp02 = new MultiDimensionalObject("cp02",
				"Campaign", "cp02", "cp02", "-1");

		MultiDimensionalObject mp02 = new MultiDimensionalObject("mp02",
				"MasterPublication", "mp02", "mp02", "cp02");
		MultiDimensionalObject pg02 = new MultiDimensionalObject("pg02",
				"PublicationGroup", "pg02", "pg02", "cp02,mp02");
		MultiDimensionalObject p02 = new MultiDimensionalObject("p02",
				"Publication", "p02", "p02", "cp02,mp02,pg02");
		MultiDimensionalObject cp03 = new MultiDimensionalObject("cp03",
				"Campaign", "cp03", "cp03", "-1");
		MultiDimensionalObject mp03 = new MultiDimensionalObject("mp03",
				"MasterPublication", "mp03", "mp03", "cp03");
		MultiDimensionalObject pg03 = new MultiDimensionalObject("pg03",
				"PublicationGroup", "pg03", "pg03", "cp03,mp03");
		MultiDimensionalObject p03 = new MultiDimensionalObject("p03",
				"Publication", "p03", "p03", "cp03,mp03,pg03");
		MultiDimensionalObject cp04 = new MultiDimensionalObject("cp04",
				"Campaign", "cp04", "cp04", "-1");
		MultiDimensionalObject mp04 = new MultiDimensionalObject("mp04",
				"MasterPublication", "mp04", "mp04", "cp04");

		models.add(mp01);
		models.add(mp012);
		models.add(cp01);

		models.add(cp02);

		models.add(mp02);
		models.add(pg02);
		models.add(p02);
		models.add(cp03);
		models.add(mp03);
		models.add(pg03);
		models.add(p03);
		models.add(cp04);
		models.add(mp04);

	}

	@Test
	public void itShouldCreateMultipleDimensionGroupsForGivenModels() {

		cache = new InMemoryDimensionGroup(inMemoryNosqlRepository);
		for (MultiDimensionalObject dimension : models) {

			dimensionRepository = new DimensionRepository(null, cache,
					noSqlRepository, factory);
			String test = dimensionRepository.createDimension(dimension);
			assertThat(test).isNotNull();
			assertThat(test).isEqualTo(dimension.getId());

		}

	}
}
