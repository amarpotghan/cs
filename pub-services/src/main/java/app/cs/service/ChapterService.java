package app.cs.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.factory.DomainFactory;
import app.cs.model.HierarchicalObject;
import app.cs.repository.api.IChapterRepository;

/**
 * The Class ChapterService.
 */
@Component
public class ChapterService implements Service {

	/** The factory. */
	private DomainFactory factory;

	/** The contentobject. */
	private final String CONTENTOBJECT = "ContentObject";

	/** The chapter repository. */
	private IChapterRepository chapterRepository;

	/**
	 * Instantiates a new chapter service.
	 * 
	 * @param chapterRepository
	 *            the chapter repository
	 */
	@Autowired
	public ChapterService(IChapterRepository chapterRepository) {
		// TODO Auto-generated constructor stub
		this.chapterRepository = chapterRepository;
	}

	/*
	 * Get all chapters.
	 * 
	 * @see com.cs.service.IService#getAll()
	 */
	@Override
	public String getAll() throws IOException, URISyntaxException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * Creates new chapter.
	 * 
	 * @see com.cs.service.IService#create(com.cs.model.ContentObject)
	 */

	/*
	 * Get all chapters for given structure.
	 * 
	 * @see com.cs.service.IService#getAllBy(java.lang.String)
	 */
	@Override
	public List<HierarchicalObject> getAllBy(String structure) {
		return null;
	}

	/*
	 * Delets old chapter and creats new one.
	 * 
	 * @see com.cs.service.IService#move(com.cs.model.ContentObject,
	 * java.lang.String)
	 */
	@Override
	public void move(String type, String name, String path, String isFolder,
			String newPath) {
		HierarchicalObject chapter = (HierarchicalObject) factory
				.getDomainObject(CONTENTOBJECT);
		setChapterAtrributes(chapter, type, name, newPath, isFolder);
		// move=delete+create
		delete(chapter, path);
		create(type, name, newPath, isFolder);

	}

	/*
	 * Delete chapter from given path.
	 * 
	 * @see com.cs.service.IService#delete(com.cs.model.ContentObject,
	 * java.lang.String)
	 */
	@Override
	public void delete(HierarchicalObject chapter, String path) {

		chapterRepository.delete(chapter, path);

	}

	@Override
	public String create(String type, String name, String path, String isFolder) {

		HierarchicalObject chapter = (HierarchicalObject) factory
				.getDomainObject(CONTENTOBJECT);
		setChapterAtrributes(chapter, type, name, path, isFolder);
		return chapterRepository.save(chapter);
	}

	/**
	 * Sets the chapter atrributes.
	 * 
	 * @param chapter
	 *            the chapter
	 * @param type
	 *            the type
	 * @param name
	 *            the name
	 * @param path
	 *            the path
	 * @param isFolder
	 *            the is folder
	 */
	private void setChapterAtrributes(HierarchicalObject chapter, String type,
			String name, String path, String isFolder) {
		chapter.setId(name);
		chapter.setTitle(name);
		chapter.setIsFolder(isFolder);
		chapter.setPath(path);
		chapter.setName(name);
		chapter.setType(type);

	}

}
