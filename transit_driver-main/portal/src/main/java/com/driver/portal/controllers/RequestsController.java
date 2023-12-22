package com.driver.portal.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.driver.portal.dto.NewRequests;
import com.driver.portal.dto.RequestList;
import com.driver.portal.models.Requests;
import com.driver.portal.services.RequestsService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("requests")
@RequiredArgsConstructor
public class RequestsController {
    
    private final RequestsService requestsService;

    @GetMapping(value="getRequests")
    public List<RequestList> getRequests() {        
        return requestsService.getRequests();
    }

    @PostMapping(value="newrequest")
    @ResponseStatus(HttpStatus.CREATED)
    public Requests newRequest(@RequestBody NewRequests request) {        
        return requestsService.newRequests(request);
    }
    

}
