package com.driver.portal.services;


import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.driver.portal.dto.GetBranchCodeResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CustomerService {

    public GetBranchCodeResponse getBranchCode(String acctnum) {

        String apiUrl="http://10.18.9.45:8585/core/banking/0.0.1/enquiry/getcustdetailsbyacct/"+acctnum;
        String web_response=fetchBranchCode(apiUrl);

         ObjectMapper objectMapper = new ObjectMapper();
        try {
            GetBranchCodeResponse response=objectMapper.readValue(web_response,new TypeReference<GetBranchCodeResponse>() {});
            return response;

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String fetchBranchCode(String apiUrl) {
        String responseBody = null;

        try {
            try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
                HttpGet httpGet = new HttpGet(apiUrl);
                httpGet.setHeader("Accept", "application/json");
                

                System.out.println("Executing request " + httpGet.getRequestLine());

                ResponseHandler < String > responseHandler = response -> {
                    int status = response.getStatusLine().getStatusCode();
                    System.out.println(status);
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else if (status == 401) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                };

                responseBody = httpclient.execute(httpGet, responseHandler);
                
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseBody;
    }
    
}
