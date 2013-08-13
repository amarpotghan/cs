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


import com.cs.data.api.core.nosql.InMemoryNoSqlRepository;
import com.cs.data.core.nosql.mongodb.MongoRepository;

import app.cs.inmemory.InMemoryDimensionGroup;
import app.cs.model.HierarchicalObject;
import app.cs.repository.DimensionRepository;
import app.cs.repository.api.IDimensionRepository;
import app.cs.service.Service;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context-test.xml")
public class DimensionIntegrationTests {

	@Autowired
	private MongoRepository noSqlRepository;

	List<HierarchicalObject> models = new ArrayList<HierarchicalObject>();

	private InMemoryDimensionGroup cache;
	private IDimensionRepository dimensionRepository;

	private Service dimensionService;

	@Autowired
	private InMemoryNoSqlRepository inMemoryNosqlRepository;

	@Before
	public void setUp() {

		HierarchicalObject mp01 = new HierarchicalObject("mp01", "MasterPublication",
				"mp01", "mp01", "-1");
		HierarchicalObject mp012 = new HierarchicalObject("mp012", "MasterPublication",
				"mp012", "mp012", "-1");
		HierarchicalObject cp01 = new HierarchicalObject("cp01", "Campaign", "cp01",
				"cp01", "mp01");
		HierarchicalObject cp02 = new HierarchicalObject("cp02", "Campaign", "cp02",
				"cp02", "-1");

		HierarchicalObject mp02 = new HierarchicalObject("mp02", "MasterPublication",
				"mp02", "mp02", "cp02");
		HierarchicalObject pg02 = new HierarchicalObject("pg02", "PublicationGroup",
				"pg02", "pg02", "cp02,mp02");
		HierarchicalObject p02 = new HierarchicalObject("p02", "Publication", "p02",
				"p02", "cp02,mp02,pg02");
		HierarchicalObject cp03 = new HierarchicalObject("cp03", "Campaign", "cp03",
				"cp03", "-1");
		HierarchicalObject mp03 = new HierarchicalObject("mp03", "MasterPublication",
				"mp03", "mp03", "cp03");
		HierarchicalObject pg03 = new HierarchicalObject("pg03", "PublicationGroup",
				"pg03", "pg03", "cp03,mp03");
		HierarchicalObject p03 = new HierarchicalObject("p03", "Publication", "p03",
				"p03", "cp03,mp03,pg03");
		HierarchicalObject cp04 = new HierarchicalObject("cp04", "Campaign", "cp04",
				"cp04", "-1");
		HierarchicalObject mp04 = new HierarchicalObject("mp04", "MasterPublication",
				"mp04", "mp04", "cp04");

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
		for (HierarchicalObject dimension : models) {
			dimensionRepository = new DimensionRepository(null, cache,
					noSqlRepository);
			String test = dimensionRepository.createDimension(dimension);
			assertThat(test).isNotNull();
			assertThat(test).isEqualTo(dimension.getId());

		}

	}
}
