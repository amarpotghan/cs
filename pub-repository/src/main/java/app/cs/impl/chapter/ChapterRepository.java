package app.cs.impl.chapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.impl.delegate.factory.DomainFactory;
import app.cs.interfaces.chapter.IChapterRepository;
import app.cs.interfaces.chapter.IInMemoryViewStructure;
import app.cs.interfaces.model.MultiDimensionalObject;

import com.cs.data.api.core.GenericDomain;
import com.cs.data.api.core.nosql.mongodb.NoSqlRepository;

/**
 * The Class ChapterRepository. TODO create separate Object for Chapter & Page,
 * Dont use ContentObject(DimensionObject) TODO with separate interface****
 */
@Component
public class ChapterRepository implements IChapterRepository {

	/** The nosql template for mongo. */
	private NoSqlRepository noSqlRepository;

	/** The cache. */
	private IInMemoryViewStructure inMemoryViewStructure;

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
	public ChapterRepository(NoSqlRepository noSqlRepository,
			IInMemoryViewStructure cache, DomainFactory factory) {
		this.noSqlRepository = noSqlRepository;
		this.inMemoryViewStructure = cache;
		this.factory = factory;

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

		MultiDimensionalObject publication = getParentPublication(chapter
				.getPath());
		addChapterToPublication(publication, chapter);

		return noSqlRepository.save(chapter);
	}

	/**
	 * Adds the chapter to publication.
	 * 
	 * @param publication
	 *            the publication
	 * @param chapter
	 *            the chapter
	 */
	private void addChapterToPublication(MultiDimensionalObject publication,
			MultiDimensionalObject chapter) {
		MultiDimensionalObject parent;
		parent = find(publication, getParentId(chapter.getPath()));
		parent.addchild(chapter);
		saveToMongo(publication);

	}

	/**
	 * Save given publication to mongoDb database..
	 * 
	 * @param publication
	 *            the publication
	 */
	private void saveToMongo(MultiDimensionalObject publication) {
		noSqlRepository.save(publication);
	}

	@Override
	public GenericDomain getDomain(String type) {

		return factory.getDomainObject(type);
	}

	/**
	 * Find given parent id in given publication..
	 * 
	 * @param publication
	 *            the publication
	 * @param parentId
	 *            the parent id
	 * @return the content object
	 */
	public MultiDimensionalObject find(MultiDimensionalObject publication,
			String parentId) {
		MultiDimensionalObject child = null;
		if (publication.getId().equals(parentId)) {
			return publication;

		}

		if (publication.hasChildren()) {
			for (MultiDimensionalObject chapter : publication.getChildren()) {
				if (child != null)
					break;
				if (chapter.getId().equals(parentId)) {
					return chapter;

				} else {
					child = find(chapter, parentId);

				}

			}
		}
		return child;
	}

	/**
	 * Gets the publication id.
	 * 
	 * @param path
	 *            the path
	 * @return the publication id
	 */
	public String getPublicationId(String path) {

		String currentViewStructure = inMemoryViewStructure
				.getCurrentViewStructure();
		int lastIndex = getLastIndexOf(currentViewStructure);

		return path.split(COMMA)[lastIndex];

	}

	/**
	 * Gets the last index of current view structure.
	 * 
	 * @param currentViewStructure
	 *            the current view structure
	 * @return the last index of
	 */
	public int getLastIndexOf(String currentViewStructure) {
		// TODO Auto-generated method stub
		return currentViewStructure.split(HIPHEN).length - 1;

	}

	/**
	 * Gets the parent id for given path.
	 * 
	 * @param path
	 *            the path
	 * @return the parent id
	 */
	public String getParentId(String path) {
		String[] paths = path.split(COMMA);
		return paths[paths.length - 1];
	}

	/**
	 * Gets the parent publication.
	 * 
	 * @param path
	 *            the path
	 * @return the parent publication
	 */
	protected MultiDimensionalObject getParentPublication(String path) {
		return noSqlRepository.getObjectByKey(getPublicationId(path),
				MultiDimensionalObject.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cs.data.business.repository.IChapterRepository#delete(com.cs.data
	 * .business.api.model.MultiDimensionalObject, java.lang.String)
	 */
	@Override
	public String delete(MultiDimensionalObject chapter) {
		MultiDimensionalObject parentPublication = getParentPublication(chapter
				.getPath());
		MultiDimensionalObject parent = find(parentPublication,
				getParentId(chapter.getPath()));
		parent.removeChild(chapter);
		saveToMongo(parentPublication);
		return chapter.getId();
	}

	@Override
	public void move(MultiDimensionalObject chapter, String newPath) {
		MultiDimensionalObject parentPublication = getParentPublication(chapter.getPath());
		MultiDimensionalObject chapterForNewLocation = find(parentPublication,
				chapter.getId());
		chapterForNewLocation.setPath(newPath);
		save(chapterForNewLocation);
		delete(chapter);

	}

}
