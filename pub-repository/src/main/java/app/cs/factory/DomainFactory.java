package app.cs.factory;

import org.springframework.stereotype.Component;

import app.cs.model.HierarchicalObject;

import com.cs.data.api.core.GenericDomain;


/**
 * A factory for creating Domain objects.
 */
@Component
public class DomainFactory {

	/**
	 * Gets the domain object.
	 *
	 * @param type the type
	 * @return the domain object
	 */
	public GenericDomain getDomainObject(String type) {
		if (type.equals("ContentObject"))
			return new HierarchicalObject();

		return null;
	}

}
