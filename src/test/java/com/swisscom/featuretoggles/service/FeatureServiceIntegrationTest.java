package com.swisscom.featuretoggles.service;

import com.swisscom.featuretoggles.model.Feature;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.*;
import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FeatureServiceIntegrationTest {
    @Autowired
    private FeatureService featureService;

    @Test
    public void testAddFeatureHappyPath() {
        Feature feature = new Feature("Feature 1", "show_warning", LocalDate.now().plusDays(10), "show warning screen", false);

        Feature newFeature = featureService.add(feature);
        Assert.assertNotNull(newFeature);
        Assert.assertNotNull(newFeature.getId());
        Assert.assertEquals("show_warning", newFeature.getTechnicalName());
    }

    @Test
    public void testEditFeatureHappyPath() {
        Feature feature = featureService.add(new Feature("Feature 1", "show_warning", LocalDate.now().plusDays(10), "show warning screen", false));

        feature.setDisplayName("Feature 1 updated");
        feature.setInverted(true);
        Feature updatedFeature = featureService.edit(feature, feature.getId());
        Assert.assertNotNull(updatedFeature);
        Assert.assertNotNull(updatedFeature.getId());
        Assert.assertEquals("Feature 1 updated", updatedFeature.getDisplayName());
        Assert.assertEquals(true, updatedFeature.isInverted());
    }
}
