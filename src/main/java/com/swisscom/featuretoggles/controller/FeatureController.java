package com.swisscom.featuretoggles.controller;

import com.swisscom.featuretoggles.model.Customer;
import com.swisscom.featuretoggles.model.CustomerFeature;
import com.swisscom.featuretoggles.model.Feature;
import com.swisscom.featuretoggles.service.FeatureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"Feature API"})
@RestController
@RequestMapping(value = "/api/v1")
public class FeatureController {
    private FeatureService featureService;

    @Autowired
    public FeatureController(FeatureService featureService) {
        this.featureService = featureService;
    }

    @ApiOperation(value = "Create Feature")
    @PostMapping(value = "/features")
    Feature addFeature(@ApiParam(value = "Feature object store in database table") @RequestBody Feature feature){
        return featureService.add(feature);
    }

    @ApiOperation(value = "Update Feature")
    @PutMapping(value = "/features/{id}")
    Feature edit(
            @ApiParam(value = "Updated Feature object",required = true) @RequestBody Feature updatedFeature,
            @ApiParam(value = "Feature Id to update Feature object", required = true, example = "123") @PathVariable Long id) {
        return featureService.edit(updatedFeature, id);
    }

    @ApiOperation(value = "List All Features")
    @GetMapping(value = "/features")
    List<Feature> getAll(){
        return featureService.getAll();
    }

    @ApiOperation(value = "List Features for Customer")
    @GetMapping(value = "/customer/{id}/features")
    List<CustomerFeature> getAllFeaturesByCustomerId(@ApiParam(value = "", required = true, example = "1") @PathVariable long id){
        return featureService.getAllFeaturesByCustomerId(id);
    }

    @ApiOperation(value = "List Unassigned Features for Customer")
    @GetMapping(value = "/customer/{id}/features/unassigned")
    List<Feature> getUnassignedFeaturesByCustomerId(@ApiParam(value = "", required = true, example = "1") @PathVariable long id){
        return featureService.getUnassignedFeaturesByCustomerId(id);
    }

    @ApiOperation(value = "Assign Feature to Customer")
    @PostMapping(value = "/customers/{customerId}/features/{featureId}/assign")
    CustomerFeature assignFeature(@ApiParam(required = true, example = "1") @PathVariable long customerId,
                                  @ApiParam(required = true, example = "1") @PathVariable long featureId){
        return featureService.assignFeature(customerId, featureId);
    }


}
