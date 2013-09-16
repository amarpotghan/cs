package app.cs.impl.assortment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.impl.delegate.factory.DomainFactory;
import app.cs.impl.helper.Finder;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.interfaces.assortment.IAssortmentRepository;

import com.cs.data.api.core.nosql.mongodb.NoSqlRepository;

/**
 * The Class AssortmentRepository.
 */
@Component
public class AssortmentRepository implements IAssortmentRepository {

	/** The nosql template for mongo. */
	private NoSqlRepository mongoRepository;

	/** The finder. */
	private Finder finder;

	private DomainFactory factory;

	/**
	 * Instantiates a new assortment repository.
	 * 
	 * @param nosqlTemplateForMongo
	 *            the nosql template for mongo
	 * @param cache
	 *            the cache
	 */
	@Autowired
	public AssortmentRepository(NoSqlRepository noSqlRepository,
			DomainFactory factory, Finder finder) {
		this.mongoRepository = noSqlRepository;
		this.factory = factory;
		this.finder = finder;

	}

	@Override
	public String save(MultiDimensionalObject assortment) {

		System.out.println(assortment.getPath());
		System.out.println(assortment.getId());
		MultiDimensionalObject publication = getParentPublication(assortment
				.getPath());
		return addAssortmentToPublication(publication, assortment);

	}

	/**
	 * Adds the assortment to publication.
	 * 
	 * @param publication
	 *            the publication
	 * @param assortment
	 * 
	 */
	private String addAssortmentToPublication(
			MultiDimensionalObject publication,
			MultiDimensionalObject assortment) {
		MultiDimensionalObject parent;
		parent = finder.find(publication,
				finder.getParentId(assortment.getPath()));
		parent.addchild(assortment);
		return saveToRepository(publication);

	}

	/**
	 * Save given publication to mongoDb database..
	 * 
	 * @param publication
	 *            the publication
	 */
	private String saveToRepository(MultiDimensionalObject publication) {
		return mongoRepository.save(publication);
	}

	@Override
	public MultiDimensionalObject getDomain(String type) {

		return factory.getDomainObject(type);
	}

	@Override
	public void copy(MultiDimensionalObject assortment) {
		/*
		 * MultiDimensionalObject parentPublication =
		 * getParentPublication(assortment .getPath()); MultiDimensionalObject
		 * assortmentForNewLocation = finder.find( parentPublication,
		 * assortment.getId()); assortmentForNewLocation.setPath(newPath);
		 */
		save(assortment);

	}

	public MultiDimensionalObject getParentPublication(String path) {
		return mongoRepository.getObjectByKey(finder.getPublicationId(path),
				MultiDimensionalObject.class);
	}

	@Override
	public String updateAssortment(MultiDimensionalObject assortment) {
		MultiDimensionalObject publication = getParentPublication(assortment
				.getPath());
		System.out.println(publication);
		MultiDimensionalObject oldAssortment = finder.find(publication,
				assortment.getName());
		oldAssortment.setProducts(assortment.getProducts());

		return saveToRepository(publication);
	}

}
