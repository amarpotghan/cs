package app.cs.factory;

import org.springframework.stereotype.Component;

import com.cs.data.core.GenericDomain;
import app.cs.model.ContentObject;


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
			return new ContentObject();

		return null;
	}

}
