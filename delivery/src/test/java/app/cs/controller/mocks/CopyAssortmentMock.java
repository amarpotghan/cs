package app.cs.controller.mocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import app.cs.boundary.delivery.Interactor;
import app.cs.model.request.CopyAssortmentRequest;
import app.cs.impl.model.Assortment;

@Controller
public class CopyAssortmentMock {

	private Interactor copyAssortment;
	private CopyAssortmentRequest request;

	@Autowired
	public CopyAssortmentMock(Interactor copyAssortment,
			CopyAssortmentRequest request) {

		this.copyAssortment = copyAssortment;
		this.request = request;
	}

	@RequestMapping(value = "/assortment/copy/path/{path}/newPath/{newPath}")
	public void copy(@RequestBody Assortment assortment,
			@PathVariable String newPath) {
		
		

	}
}
