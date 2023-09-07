package com.example.mycurrencycomparator.service;

import com.example.mycurrencycomparator.dto.currencyrate.CompareCurrencyResponseDto;
import com.example.mycurrencycomparator.dto.currencyrate.ExchApiResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@SpringBootTest
@ActiveProfiles(profiles = "profile1")
@TestPropertySource("classpath:/application.properties")
@ExtendWith(MockitoExtension.class)
class CurrencyServiceImplTest2 {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CurrencyServiceImpl currencyService;

    private MockRestServiceServer mockServer;
    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Value("${service.currency.url}")
    String url;

    @Value("${service.currency.apiKey}")
    String apiKey;

    @Value("${service.currency.baseCurrency}")
    String baseCurrency;

    @Test
    void getCompareResult() throws URISyntaxException, JsonProcessingException {


        //given

        Map<String, Double> histRates = new LinkedHashMap<>();
        histRates.put("RUB", 95.01);

        Map<String, Double> latestRates = new LinkedHashMap<>();
        latestRates.put("RUB", 95.02);

        ExchApiResponseDto histResp = new ExchApiResponseDto();
        histResp.setDisclaimer("disclaimer");
        histResp.setLicense("license");
        histResp.setTimestamp(Long.valueOf(1234567890));
        histResp.setBase(baseCurrency);
        histResp.setRates(histRates);

        ExchApiResponseDto latestResp = new ExchApiResponseDto();
        latestResp.setDisclaimer("disclaimer");
        latestResp.setLicense("license");
        latestResp.setTimestamp(Long.valueOf(1234567899));
        latestResp.setBase(baseCurrency);
        latestResp.setRates(latestRates);


        LocalDate dateToday = LocalDate.now();
        LocalDate dateYesterday = dateToday.minusDays(1);

        String p1 = "app_id=" + apiKey;
        String p2 = "base=" + baseCurrency;
        String p3 = "symbols=" + "RUB";
        String histRequest = url + "/historical/" + dateYesterday.toString() + ".json?" + p1 + "&" + p2 + "&" + p3;
        String latestRequest = url + "/latest.json?" + p1 + "&" + p2 + "&" + p3;


        mockServer.expect(ExpectedCount.once(), requestTo(new URI(histRequest)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(histResp))
                );


        mockServer.expect(ExpectedCount.once(), requestTo(new URI(latestRequest)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(latestResp))
                );


        // then

        ResponseEntity<CompareCurrencyResponseDto> respEntity = currencyService.getCompareResult("RUB");


        //when

        assertEquals(baseCurrency, respEntity.getBody().getBase_currency());
        assertEquals("RUB", respEntity.getBody().getCompared_currency());
        assertEquals("broke", respEntity.getBody().getCompare_result());
        assertEquals(dateToday.toString(), respEntity.getBody().getRate_data().get(0).getDate());
        assertEquals(95.02, respEntity.getBody().getRate_data().get(0).getRate());
        assertEquals(dateYesterday.toString(), respEntity.getBody().getRate_data().get(1).getDate());
        assertEquals(95.01, respEntity.getBody().getRate_data().get(1).getRate());
    }
}



//@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = SpringTestConfig.class)
//public class EmployeeServiceMockRestServiceServerUnitTest {
//
//    @Autowired
//    private EmployeeService empService;
//    @Autowired
//    private RestTemplate restTemplate;
//
//    private MockRestServiceServer mockServer;
//    private ObjectMapper mapper = new ObjectMapper();
//
//    @BeforeEach
//    public void init() {
//        mockServer = MockRestServiceServer.createServer(restTemplate);
//    }
//
//    @Test
//    public void givenMockingIsDoneByMockRestServiceServer_whenGetIsCalled_thenReturnsMockedObject()() {
//        Employee emp = new Employee("E001", "Eric Simmons");
//        mockServer.expect(ExpectedCount.once(),
//                requestTo(new URI("http://localhost:8080/employee/E001")))
//                .andExpect(method(HttpMethod.GET))
//                .andRespond(withStatus(HttpStatus.OK)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .body(mapper.writeValueAsString(emp))
//                );
//
//        Employee employee = empService.getEmployee(id);
//        mockServer.verify();
//        Assertions.assertEquals(emp, employee);
//    }
//}
