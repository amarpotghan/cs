package app.cs.mocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.impl.delegate.factory.DomainFactory;
import app.cs.impl.helper.Finder;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.interfaces.chapter.IChapterRepository;

import com.cs.data.api.core.nosql.mongodb.NoSqlRepository;

/**
 * The Class ChapterRepository. TODO create separate Object for Chapter & Page,
 * Dont use ContentObject(DimensionObject) TODO with separate interface****
 */
@Component
public class ChapterMocks implements IChapterRepository {

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
	public ChapterMocks(NoSqlRepository noSqlRepository, DomainFactory factory,
			Finder finder) {
		this.noSqlRepository = noSqlRepository;
		this.factory = factory;
		this.finder = finder;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cs.data.business.repository.IChapterRepository#save(com.cs.data.business
	 * .model.MultiDimensionalObject)
	 */
	@Override
	public String save(MultiDimensionalObject chapter) {
		return chapter.getId();

	}

	/**
	 * Adds the chapter to publication.
	 * 
	 * @param publication
	 *            the publication
	 * @param chapter
	 *            the chapter
	 */
	private String addChapterToPublication(MultiDimensionalObject publication,
			MultiDimensionalObject chapter) {
		MultiDimensionalObject parent;
		parent = finder
				.find(publication, finder.getParentId(chapter.getPath()));
		parent.addchild(chapter);
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
	public String delete(MultiDimensionalObject chapter) {
		/*
		 * MultiDimensionalObject parentPublication =
		 * getParentPublication(chapter .getPath()); MultiDimensionalObject
		 * parent = finder.find(parentPublication,
		 * finder.getParentId(chapter.getPath())); parent.removeChild(chapter);
		 * saveToMongo(parentPublication); return chapter.getId();
		 */
		return chapter.getId();
	}

	@Override
	public void move(MultiDimensionalObject chapter, String newPath) {
		/*
		 * MultiDimensionalObject parentPublication =
		 * getParentPublication(chapter .getPath()); MultiDimensionalObject
		 * chapterForNewLocation = finder.find( parentPublication,
		 * chapter.getId()); chapterForNewLocation.setPath(newPath);
		 * save(chapterForNewLocation); delete(chapter);
		 */

	}

	public MultiDimensionalObject getParentPublication(String path) {
		return noSqlRepository.getObjectByKey(finder.getPublicationId(path),
				MultiDimensionalObject.class);
	}

}
