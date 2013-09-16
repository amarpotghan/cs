package app.cs.actions.contentplanning.mam;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.impl.mam.MamRepository;
import app.cs.model.request.StringRequest;
import app.cs.model.response.ResponseModel;
import app.cs.model.response.StringResponse;

@RunWith(MockitoJUnitRunner.class)
public class GetMAMAssetsUnitTests {

	private GetMAMAssets getMAMAssets;

	@Mock
	private MamRepository mamRepository;
	
	private String id = "62";

	@Before
	public void setUp() {
		getMAMAssets = new GetMAMAssets(mamRepository);
	}

	@Test
	public void itShouldGetAssetsFromMAMRepository() {

		String expectedAssetList = "test assets";
		
		StringRequest stringRequest= new StringRequest(id);
		// when
		when(mamRepository.getAssetsFor(id)).thenReturn(expectedAssetList);
		ResponseModel assetList = getMAMAssets.execute(stringRequest);

		StringResponse response = (StringResponse) assetList;
	
		// then
		verify(mamRepository).getAssetsFor(id);
		assertThat(response.getResponseString()).isEqualTo(expectedAssetList);

	}
}