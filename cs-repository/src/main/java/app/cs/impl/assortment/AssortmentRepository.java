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
	private NoSqlRepository noSqlRepository;

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
		this.noSqlRepository = noSqlRepository;
		this.factory = factory;
		this.finder = finder;

	}

	@Override
	public String save(MultiDimensionalObject assortment) {

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
		return saveToMongo(publication);

	}

	/**
	 * Save given publication to mongoDb database..
	 * 
	 * @param publication
	 *            the publication
	 */
	private String saveToMongo(MultiDimensionalObject publication) {
		return noSqlRepository.save(publication);
	}

	@Override
	public MultiDimensionalObject getDomain(String type) {

		return factory.getDomainObject(type);
	}

	@Override
	public void copy(MultiDimensionalObject assortment, String newPath) {
		MultiDimensionalObject parentPublication = getParentPublication(assortment
				.getPath());
		MultiDimensionalObject assortmentForNewLocation = finder.find(
				parentPublication, assortment.getId());
		assortmentForNewLocation.setPath(newPath);
		save(assortmentForNewLocation);

	}

	public MultiDimensionalObject getParentPublication(String path) {
		return noSqlRepository.getObjectByKey(finder.getPublicationId(path),
				MultiDimensionalObject.class);
	}

	@Override
	public String updateAssortment(MultiDimensionalObject assortment) {
		MultiDimensionalObject publication = getParentPublication(assortment
				.getPath());
		MultiDimensionalObject oldAssortment = finder.find(publication,
				assortment.getId());
		oldAssortment.setProducts(assortment.getProducts());

		saveToMongo(publication);
		return "updated=>" + assortment.getId();
	}

}
