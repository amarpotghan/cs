package app.cs.repository.api;

import app.cs.model.HierarchicalObject;

public interface IChapterRepository {

	/**
	 * Save given chapter.
	 * 
	 * @param chapter
	 *            the chapter
	 * @return the string
	 */
	public abstract String save(HierarchicalObject chapter);

	/**
	 * Deletes given chapter for given old path.
	 * 
	 * @param chapter
	 *            the chapter
	 * @param oldPath
	 *            the old path
	 * @return the string
	 */
	public abstract String delete(HierarchicalObject chapter, String oldPath);

}