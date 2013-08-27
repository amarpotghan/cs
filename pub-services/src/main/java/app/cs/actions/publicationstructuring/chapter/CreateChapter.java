package app.cs.actions.publicationstructuring.chapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.interfaces.chapter.IChapterRepository;
import app.cs.interfaces.dimension.IMultiDimensionalObject;
import app.cs.model.MultiDimensionalObject;
import app.cs.model.response.ResponseModel;
import app.cs.model.response.StringResponse;

/**
 * The Class ChapterService.
 */
@Component
public class CreateChapter {

	/** The contentobject. */
	private final String CONTENTOBJECT = "MultiDimensionalObject";

	/** The chapter repository. */
	private IChapterRepository chapterRepository;

	/**
	 * Instantiates a new chapter service.
	 * 
	 * @param chapterRepository
	 *            the chapter repository
	 * @param factory
	 */
	@Autowired
	public CreateChapter(IChapterRepository chapterRepository) {
		// TODO Auto-generated constructor stub
		this.chapterRepository = chapterRepository;
	}

	public ResponseModel execute(String type, String name, String path,
			boolean isFolder) {

		MultiDimensionalObject chapter = (MultiDimensionalObject) chapterRepository
				.getDomain(CONTENTOBJECT);
		setChapterAtrributes(chapter, type, name, path, isFolder);
		return new StringResponse(chapterRepository.save(chapter));
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
	private void setChapterAtrributes(IMultiDimensionalObject chapter,
			String type, String name, String path, boolean isFolder) {
		chapter.setId(name);
		chapter.setTitle(name);
		chapter.setIsFolder(isFolder);
		chapter.setPath(path);
		chapter.setName(name);
		chapter.setType(type);

	}

}
