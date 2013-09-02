package app.cs.actions.contentplanning.mam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.boundary.delivery.Interactor;
import app.cs.impl.mam.MAMRepository;
import app.cs.model.request.RequestModel;
import app.cs.model.request.StringRequest;
import app.cs.model.response.ResponseModel;
import app.cs.model.response.StringResponse;

@Component
public class MAMSearch implements Interactor{

	private MAMRepository mamRepository;

	@Autowired
	public MAMSearch(MAMRepository mamRepository) {
		this.mamRepository = mamRepository;
	}

	public ResponseModel execute(RequestModel model) {
		StringRequest request = (StringRequest)model;
		return new StringResponse(mamRepository.getSearchResults(request.getStringRequest()));

	}

}