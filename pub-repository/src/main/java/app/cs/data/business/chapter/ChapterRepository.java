package app.cs.data.business.chapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.data.business.api.chapter.IChapterRepository;
import app.cs.data.business.api.chapter.IInMemoryViewStructure;
import app.cs.data.business.api.dimension.IMultiDimensionalObject;
import app.cs.data.business.dimension.MultiDimensionalObject;

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
	private IInMemoryViewStructure cache;

	/** The comma. */
	private final String COMMA = ",";

	/** The hiphen. */
	private final String HIPHEN = "-";

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
			IInMemoryViewStructure cache) {
		this.noSqlRepository = noSqlRepository;
		this.cache = cache;

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
		IMultiDimensionalObject parent;
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

	/**
	 * Find given parent id in given publication..
	 * 
	 * @param publication
	 *            the publication
	 * @param parentId
	 *            the parent id
	 * @return the content object
	 */
	public IMultiDimensionalObject find(IMultiDimensionalObject publication,
			String parentId) {
		IMultiDimensionalObject child = null;
		if (publication.getId().equals(parentId)) {
			return publication;

		}

		if (publication.hasChildren()) {
			for (IMultiDimensionalObject chapter : publication.getChildren()) {
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

		String currentViewStructure = cache.getCurrentViewStructure();
		System.out.println(currentViewStructure);
		System.out.println(path);
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
	 * .business.api.model.IMultiDimensionalObject, java.lang.String)
	 */
	@Override
	public String delete(IMultiDimensionalObject chapter, String oldPath) {
		MultiDimensionalObject parentPublication = getParentPublication(oldPath);
		IMultiDimensionalObject parent = find(parentPublication,
				getParentId(oldPath));
		chapter.setPath(oldPath);
		parent.removeChild(chapter);
		saveToMongo(parentPublication);
		return chapter.getId();
	}

}
