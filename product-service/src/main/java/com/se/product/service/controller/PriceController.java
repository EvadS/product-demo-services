package com.se.product.service.controller;

import com.se.product.service.controller.base.PriceApi;
import com.se.product.service.model.PriceRequest;
import com.se.product.service.model.PriceResponse;
import com.se.product.service.service.PriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


@RestController
@RequestMapping("/price")
public class PriceController implements PriceApi {

    private static Logger logger = LoggerFactory.getLogger(PriceController.class);
    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @Override
    @PostMapping
    public ResponseEntity<PriceResponse> create(@Valid @RequestBody PriceRequest request) {
        logger.debug("handle create price request {}", request);
        PriceResponse priceResponse = priceService.create(request);
        return new ResponseEntity<>(priceResponse, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable(value = "id") @NotNull Long priceId,
            @Valid @RequestBody PriceRequest requestModel) {

        logger.debug("handle update price, id:{}, model: {}",priceId , requestModel );

        PriceResponse articleResponse = priceService.updatePrice(priceId, requestModel);
        return new ResponseEntity<>(articleResponse, HttpStatus.ACCEPTED);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletePrice(
            @PathVariable(value = "id") @NotNull Long id) {
        logger.debug("handle delete price request, id:{}",id);
        priceService.deletePrice(id);
        return ResponseEntity.accepted().build();
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<PriceResponse> getById(
            @PathVariable(value = "id") Long priceId) {
        logger.debug("handle get price by id: {}",priceId);

        logger.info("Handle get price request. Request:{}", priceId);

        PriceResponse priceResponse = priceService.getById(priceId);
        return ResponseEntity.ok(priceResponse);
    }

    @Override
    @GetMapping(value = "/list")
    public ResponseEntity<?> list() {
        logger.debug("handle list of price request");

        List<PriceResponse> priceResponseList = priceService.getAll();
        return ResponseEntity.ok(priceResponseList);
    }


    @Override
    @RequestMapping(value = "/paged", method = RequestMethod.GET)
    public ResponseEntity<Page<PriceResponse>> getPaged(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer count,
            @RequestParam(required = false, defaultValue = "") String filter) {

        logger.debug("handle pagged price request, start:{}, count:{}",page,count);

        Pageable pageable = (filter == null) ? PageRequest.of(page, count) : PageRequest.of(page, count, Sort.by("id"));
        Page<PriceResponse> articleResponsePage = priceService.getPaged(pageable, filter);

        return ResponseEntity.ok(articleResponsePage);
    }
}
