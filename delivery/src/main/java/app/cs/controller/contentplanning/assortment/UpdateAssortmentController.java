package app.cs.controller.contentplanning.assortment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import app.cs.boundary.delivery.Interactor;
import app.cs.impl.model.Assortment;
import app.cs.model.request.UpdateAssortmentRequest;

@Controller
public class UpdateAssortmentController {

	private Interactor updateAssortment;
	private UpdateAssortmentRequest request;

	@Autowired
	public UpdateAssortmentController(Interactor updateAssortment,
			UpdateAssortmentRequest request) {

		this.updateAssortment = updateAssortment;
		this.request = request;
	}

	@RequestMapping(value = "/assortment/update/{path}")
	public void execute(@RequestBody Assortment assortment) {

		request.setAssortment(assortment);
		updateAssortment.execute(request);
	}

}
