package app.cs.controller.contentplanning.assortment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import app.cs.boundary.delivery.Interactor;
import app.cs.impl.model.Assortment;
import app.cs.model.request.CopyAssortmentRequest;

@Controller
public class CopyAssortmentController {

	private Interactor copyAssortment;
	private CopyAssortmentRequest request;

	@Autowired
	public CopyAssortmentController(Interactor copyAssortment,
			CopyAssortmentRequest request) {

		this.copyAssortment = copyAssortment;
		this.request = request;
	}

	@RequestMapping(value = "/assortment/copy/path/{path}/newPath/{newPath}")
	public void copy(@RequestBody Assortment assortment,
			@PathVariable String newPath) {
		request.setAssortment(assortment);
		request.setNewPath(newPath);

		copyAssortment.execute(request);

	}
}
