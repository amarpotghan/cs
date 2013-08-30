package app.cs.impl.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs.data.api.core.nosql.mongodb.NoSqlRepository;

import app.cs.impl.model.MultiDimensionalObject;
import app.cs.interfaces.chapter.IInMemoryViewStructure;

@Component
public class Finder {

	private IInMemoryViewStructure structure;

	private NoSqlRepository noSqlRepository;

	private final String COMMA = ",";

	private final String HIPHEN = "-";

	@Autowired
	public Finder(NoSqlRepository noSqlRepository,
			IInMemoryViewStructure structure) {

		this.structure = structure;
		this.noSqlRepository = noSqlRepository;
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

		return path.split(COMMA)[lastIndex];

	}

	public int getLastIndexOf(String currentViewStructure) {
		return currentViewStructure.split(HIPHEN).length - 1;

	}

	public String getParentId(String path) {
		String[] paths = path.split(COMMA);
		return paths[paths.length - 1];
	}

}