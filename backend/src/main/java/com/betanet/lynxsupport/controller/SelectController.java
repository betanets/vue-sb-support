package com.betanet.lynxsupport.controller;

import com.betanet.lynxsupport.domain.Engineer;
import com.betanet.lynxsupport.domain.EventType;
import com.betanet.lynxsupport.domain.Place;
import com.betanet.lynxsupport.domain.bean.EngineerSimpleSelectBean;
import com.betanet.lynxsupport.domain.bean.EventTypeSimpleSelectBean;
import com.betanet.lynxsupport.domain.bean.PlaceSimpleSelectBean;
import com.betanet.lynxsupport.service.api.EngineerService;
import com.betanet.lynxsupport.service.api.EventService;
import com.betanet.lynxsupport.service.api.EventTypeService;
import com.betanet.lynxsupport.service.api.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SelectController {

    @Autowired
    private EngineerService engineerService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private EventTypeService eventTypeService;
    
    @Autowired
    private EventService eventService;

    private final int REQUEST_LIST_MAX_SIZE = 10000;

    @GetMapping(path = "/select/engineers")
    public ResponseEntity<?> getEngineers() {
        Pageable pageable = PageRequest.of(0, REQUEST_LIST_MAX_SIZE);
        Page<Engineer> engineers = engineerService.getEngineers(pageable);

        Map result = new HashMap<>();
        result.put("engineers", engineers.getContent()
                .stream()
                .map(item -> new EngineerSimpleSelectBean(item.getId(), item.getFio()))
                .collect(Collectors.toList()));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path = "/select/event_types")
    public ResponseEntity<?> getEventTypes() {
        Pageable pageable = PageRequest.of(0, REQUEST_LIST_MAX_SIZE);
        Page<EventType> eventTypes = eventTypeService.getEventTypes(pageable);

        Map result = new HashMap<>();
        result.put("eventTypes", eventTypes.getContent()
                .stream()
                .map(item -> new EventTypeSimpleSelectBean(item.getId(), item.getEventType()))
                .collect(Collectors.toList()));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path = "/select/places")
    public ResponseEntity<?> getPlaces() {
        Pageable pageable = PageRequest.of(0, REQUEST_LIST_MAX_SIZE);
        Page<Place> places = placeService.getPlaces(pageable);

        Map result = new HashMap<>();
        result.put("places", places.getContent()
                .stream()
                .map(item -> new PlaceSimpleSelectBean(item.getId(), item.getPlaceName()))
                .collect(Collectors.toList()));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path = "/select/years")
    public ResponseEntity<?> getYears() {
        Map result = new HashMap<>();
        result.put("years", eventService.getEventYears());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
