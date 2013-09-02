package app.cs.impl.assortment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.impl.delegate.factory.DomainFactory;
import app.cs.impl.helper.Finder;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.interfaces.assortment.IAssortmentRepository;
import app.cs.interfaces.chapter.IChapterRepository;

import com.cs.data.api.core.GenericDomain;
import com.cs.data.api.core.nosql.mongodb.NoSqlRepository;

/**
 * The Class ChapterRepository. TODO create separate Object for Chapter & Page,
 * Dont use ContentObject(DimensionObject) TODO with separate interface****
 */
@Component
public class AssortmentRepository implements IAssortmentRepository {

	/** The nosql template for mongo. */
	private NoSqlRepository noSqlRepository;

	/** The finder. */
	private Finder finder;

	/** The comma. */
	private final String COMMA = ",";

	/** The hiphen. */
	private final String HIPHEN = "-";

	private DomainFactory factory;

	/**
	 * Instantiates a new chapter repository.
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
	 * Adds the chapter to publication.
	 * 
	 * @param publication
	 *            the publication
	 * @param assortment
	 *            the chapter
	 */
	private String addAssortmentToPublication(MultiDimensionalObject publication,
			MultiDimensionalObject assortment) {
		MultiDimensionalObject parent;
		parent = finder
				.find(publication, finder.getParentId(assortment.getPath()));
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
	public GenericDomain getDomain(String type) {

		return factory.getDomainObject(type);
	}


	@Override
	public void move(MultiDimensionalObject assortment, String newPath) {
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

}
