package app.cs.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.cs.data.api.core.nosql.NoSqlRepository;

import app.cs.inmemory.InMemoryViewStructure;
import app.cs.model.HierarchicalObject;
import app.cs.repository.api.IChapterRepository;

/**
 * The Class ChapterRepository.
 * TODO create separate Object for Chapter & Page, Dont use ContentObject(DimensionObject)
 * TODO with separate interface****
 */
@Component
public class ChapterRepository implements IChapterRepository {

	/** The nosql template for mongo. */
	private NoSqlRepository noSqlRepository;

	/** The cache. */
	private InMemoryViewStructure cache;

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
			InMemoryViewStructure cache) {
		this.noSqlRepository = noSqlRepository;
		this.cache = cache;

	}

	/* (non-Javadoc)
	 * @see app.cs.repository.IChapterRepository#save(app.cs.model.ContentObject)
	 */
	@Override
	public String save(HierarchicalObject chapter) {

		HierarchicalObject publication = getParentPublication(chapter.getPath());
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
	private void addChapterToPublication(HierarchicalObject publication,
			HierarchicalObject chapter) {
		HierarchicalObject parent;
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
	private void saveToMongo(HierarchicalObject publication) {
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
	protected HierarchicalObject find(HierarchicalObject publication, String parentId) {
		HierarchicalObject child = null;
		if (publication.getId().equals(parentId)) {
			return publication;

		}

		if (publication.hasChildren()) {
			for (HierarchicalObject chapter : publication.getChildren()) {
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
	protected String getPublicationId(String path) {

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
	protected int getLastIndexOf(String currentViewStructure) {
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
	protected String getParentId(String path) {
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
	protected HierarchicalObject getParentPublication(String path) {
		return noSqlRepository.getObjectByKey(getPublicationId(path),
				HierarchicalObject.class);
	}

	/* (non-Javadoc)
	 * @see app.cs.repository.IChapterRepository#delete(app.cs.model.ContentObject, java.lang.String)
	 */
	@Override
	public String delete(HierarchicalObject chapter, String oldPath) {
		HierarchicalObject parentPublication = getParentPublication(oldPath);
		HierarchicalObject parent = find(parentPublication, getParentId(oldPath));
		chapter.setPath(oldPath);
		parent.removeChild(chapter);
		saveToMongo(parentPublication);
		return chapter.getId();
	}

}
