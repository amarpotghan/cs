package app.cs.inmemory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs.data.api.core.nosql.InMemoryNoSqlRepository;


/**
 * The Class ViewStructureCache.
 * TODO move this complete package to data package*** data.cache
 */
@Component
public class InMemoryViewStructure {

	/** The no sql template for redis. */
	private InMemoryNoSqlRepository noSqlTemplateForRedis;
	
	/** The key. */
	private final String KEY = "view";

	/**
	 * Instantiates a new view structure cache.
	 *
	 * @param noSqlTemplateForRedis the no sql template for redis
	 */
	@Autowired
	public InMemoryViewStructure(InMemoryNoSqlRepository noSqlTemplateForRedis) {
		this.noSqlTemplateForRedis = noSqlTemplateForRedis;

	}

	/**
	 * Gets the current view structure.
	 *
	 * @return the current view structure
	 */
	public String getCurrentViewStructure() {

		return noSqlTemplateForRedis.get(KEY);
	}

	/**
	 * Sets the current view structure.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public void setCurrentViewStructure(String key, String value) {
		// TODO Auto-generated method stub
		noSqlTemplateForRedis.set(key, value);
	}

}
