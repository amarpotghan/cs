package app.cs.helper;

import com.cs.data.api.core.nosql.mongodb.NoSqlRepository;

import app.cs.interfaces.chapter.IInMemoryViewStructure;
import app.cs.interfaces.model.MultiDimensionalObject;

public class Finder {

	private IInMemoryViewStructure structure;

	private NoSqlRepository noSqlRepository;

	private final String COMMA = ",";

	private final String HIPHEN = "-";

	public Finder(NoSqlRepository noSqlRepository,
			IInMemoryViewStructure structure) {

		this.structure = structure;
		this.noSqlRepository = noSqlRepository;
	}

	public MultiDimensionalObject findGivenIdInPublication(
			MultiDimensionalObject publication, String parentId) {
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
					child = findGivenIdInPublication(chapter, parentId);

				}

			}
		}
		return child;
	}

	public MultiDimensionalObject find(MultiDimensionalObject publication,
			String parentId) {
		MultiDimensionalObject child = null;
		if (publication.getId().equals(parentId)) {
			return publication;

		}

		if (publication.hasChildren()) {
			for (MultiDimensionalObject chapter : publication.getChildren()) {
				if (child != null) {
					break;
				}
				if (chapter.getId().equals(parentId)) {
					return chapter;

				} else {
					child = find(chapter, parentId);

				}

			}
		}
		return child;
	}

	public String getPublicationId(String path) {

		String currentViewStructure = structure.getCurrentViewStructure();
		int lastIndex = getLastIndexOf(currentViewStructure);

		System.out.println(currentViewStructure);
		return path.split(COMMA)[lastIndex];

	}

	public MultiDimensionalObject getParentPublication(String path) {
		return noSqlRepository.getObjectByKey(getPublicationId(path),
				MultiDimensionalObject.class);
	}

	public int getLastIndexOf(String currentViewStructure) {
		return currentViewStructure.split(HIPHEN).length - 1;

	}

	public String getParentId(String path) {
		String[] paths = path.split(COMMA);
		return paths[paths.length - 1];
	}

}