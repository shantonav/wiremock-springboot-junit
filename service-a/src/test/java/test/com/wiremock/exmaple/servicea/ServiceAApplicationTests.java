package test.com.wiremock.exmaple.servicea;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

import test.com.wiremock.exmaple.servicea.domain.MyDomainObject;
import test.com.wiremock.exmaple.servicea.domain.OperationAResponse;
import test.com.wiremock.exmaple.servicea.domain.ServiceBResponse;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
public class ServiceAApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(12111);

	@Test
	public void testServiceBCallThroughAWireMock() {

		MyDomainObject domainObject = new MyDomainObject();
		domainObject.setSomeInteger(Integer.valueOf(800));
		domainObject.setSomeText("Some text for Service A");

		ServiceBResponse mockServiceBResponse = new ServiceBResponse("I am [MOCKED] service B response for "+domainObject.getSomeText(),
				Integer.valueOf(99999));

		ObjectMapper objectMapper = new ObjectMapper();
		String returnJSON = null;
		try {
			returnJSON = objectMapper.writeValueAsString(mockServiceBResponse);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}


		stubFor(post(urlEqualTo("/serviceb/operationb"))
				.willReturn(aResponse()
						.withStatus(201)
						.withHeader("Content-Type", javax.ws.rs.core.MediaType.APPLICATION_JSON)
						.withBody(returnJSON)));
		MvcResult result = null;
		try {
			result =  mockMvc.perform(MockMvcRequestBuilders.post("/servicea/operationa")
						.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(domainObject)))
					    .andDo(MockMvcResultHandlers.print())
						.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
						.andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}

		verify(postRequestedFor(urlEqualTo("/serviceb/operationb"))
				.withHeader("Content-Type", equalTo(javax.ws.rs.core.MediaType.APPLICATION_JSON)));

		OperationAResponse response = null;
		try {
			response = objectMapper.readValue(result.getResponse().getContentAsString(),OperationAResponse.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Assert.assertEquals(mockServiceBResponse.getSomeTextForB(),response.getSomeResponseText());
		Assert.assertEquals(mockServiceBResponse.getSomeIntForB(),response.getSomeResponseCode());


	}

}

