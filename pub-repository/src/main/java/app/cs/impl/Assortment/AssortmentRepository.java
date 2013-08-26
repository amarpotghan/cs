package app.cs.impl.assortment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.helper.Finder;
import app.cs.interfaces.model.Assortment;
import app.cs.interfaces.model.MultiDimensionalObject;

import com.cs.data.core.nosql.mongodb.MongoRepository;

@Component
public class AssortmentRepository {
	private MongoRepository mongoRepository;

	private Finder finder;

	@Autowired
	public AssortmentRepository(MongoRepository mongoRepository, Finder finder) {
		this.mongoRepository = mongoRepository;
		this.finder = finder;
	}

	public void save(Assortment assortment, String path) {

		MultiDimensionalObject publication = finder.getParentPublication(path);
		String parentId = finder.getParentId(path);
		MultiDimensionalObject parent = finder.find(publication, parentId);
		parent.addAssortment(assortment);
		mongoRepository.save(parent);

	}

	public Assortment getAssortmentObject() {
		return new Assortment();
	}

	public MultiDimensionalObject getPublication(String path) {

		MultiDimensionalObject Pub = finder.getParentPublication(path);
		return Pub;

	}

}
