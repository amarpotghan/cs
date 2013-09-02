package app.cs.controller.mocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import app.cs.boundary.delivery.Interactor;
import app.cs.impl.model.Assortment;
import app.cs.model.request.UpdateAssortmentRequest;

@Controller
public class UpdateAssortmentMock {

	private Interactor updateAssortment;
	private UpdateAssortmentRequest request;

	@Autowired
	public UpdateAssortmentMock(Interactor updateAssortment,
			UpdateAssortmentRequest request) {

		this.updateAssortment = updateAssortment;
		this.request = request;
	}

	@RequestMapping(value = "/assortment/update")
	public void execute(@RequestBody Assortment assortment) {

		
	}

}
