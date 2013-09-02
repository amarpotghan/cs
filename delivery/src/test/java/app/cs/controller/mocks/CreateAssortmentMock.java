package app.cs.controller.mocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import app.cs.boundary.delivery.Interactor;
import app.cs.impl.model.Assortment;
import app.cs.model.request.CreateAssortmentRequest;

@Controller
public class CreateAssortmentMock {

	private Interactor createAssortment;

	private CreateAssortmentRequest request;

	@Autowired
	public CreateAssortmentMock(Interactor createAssortment,
			CreateAssortmentRequest request) {

		this.createAssortment = createAssortment;
		this.request = request;
	}

	@RequestMapping(value = "/assortment/{name}/create/{path}")
	public void create(@RequestBody Assortment assortment, 
			@PathVariable String name,
			@PathVariable String path) {
		

	}
}
