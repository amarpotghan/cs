package app.cs.impl.mam;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.impl.mam.MAMRepository;
import app.cs.interfaces.mam.AssetsRepository;

import com.cs.data.webservices.rest.RestClient;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MAMRepositoryUnitTests{
	
	private AssetsRepository mamRepository;
	
	@Mock
	private RestClient restClient;
	
	private final String BASE_URL = "http://192.168.135.108/CS13.0/admin/rest/mam/list/";
	
	@Before
	public void setup(){
		mamRepository = new MAMRepository(restClient);
	}
	
	@Test
	public void itShouldRetrieveAllMediaAssetsFromGivenSourceUrlIfIdIsProvided() {
		
		// given
				String id = "62";
				Map<String, String> headerParameters = new HashMap<String, String>();
				String result = "result";

				headerParameters.put("Accept-Language", "en-US,en;q=0.8");
				headerParameters.put("Host", "192.168.135.108");
				headerParameters
						.put("User-Agent",
								"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.63 Safari/537.31");
				headerParameters.put("X-Requested-With", "XMLHttpRequest");
				headerParameters.put("Accept", "*/*");
				headerParameters
						.put("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3");
				String url = BASE_URL + id;

				// when
				when(restClient.get(url, headerParameters)).thenReturn(result);
				String assets = mamRepository.getAssetsFor(id);

				// then

				verify(restClient).get(url, headerParameters);
				assertThat(assets).isEqualTo(result);
	}
	
	@Test
	public void itShouldRetrieveAllAssetsFromGivenSourceUrlIfIdIsNotProvided() {
		// given
		String id =null;
		Map<String, String> headerParameters = new HashMap<String, String>();
		String result = "result";

		headerParameters.put("Accept-Language", "en-US,en;q=0.8");
		headerParameters.put("Host", "192.168.135.108");
		headerParameters
				.put("User-Agent",
						"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.63 Safari/537.31");
		headerParameters.put("X-Requested-With", "XMLHttpRequest");
		headerParameters.put("Accept", "*/*");
		headerParameters
				.put("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3");
		String url = BASE_URL + id;

		// when
		when(restClient.get(BASE_URL, headerParameters)).thenReturn(result);
		String assets = mamRepository.getAssetsFor(id);

		// then

		verify(restClient).get(BASE_URL, headerParameters);
		assertThat(assets).isEqualTo(result);

	}
	
}