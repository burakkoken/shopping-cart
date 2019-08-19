package com.trendyol.shoppingcart.discount;

import com.trendyol.shoppingcart.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Burak Köken on 16.8.2019.
 */
public class CampaignTest extends BaseTest {

    @Override
    protected Class<?> getPojoClass() {
        return Campaign.class;
    }

    @Test
    public void givenProductQuantityGreaterThanCampaignMinQuantity_WhenIsApplicableMethodIsCalled_ForRateCampaign_ThenReturnsTrue() {
        Campaign campaign = new Campaign(null, 100, 5, DiscountType.Rate);
        Assert.assertTrue(campaign.isApplicable(0, 10));
    }

    @Test
    public void givenProductQuantityLessThanCampaignMinQuantity_WhenIsApplicableMethodIsCalled_ForRateCampaign_ThenReturnsFalse() {
        Campaign campaign = new Campaign(null, 100, 5, DiscountType.Rate);
        Assert.assertFalse(campaign.isApplicable(0, 4));
    }

    @Test
    public void givenProductQuantityGreaterThanCampaignMinQuantity_WhenIsApplicableMethodIsCalled_ForAmountCampaign_ThenReturnsTrue() {
        Campaign campaign = new Campaign(null, 100, 5, DiscountType.Amount);
        Assert.assertTrue(campaign.isApplicable(0, 10));
    }

    @Test
    public void givenProductQuantityLessThanCampaignMinQuantity_WhenIsApplicableMethodIsCalled_ForAmountCampaign_ThenReturnsFalse() {
        Campaign campaign = new Campaign(null, 100, 5, DiscountType.Amount);
        Assert.assertFalse(campaign.isApplicable(0, 4));
    }

}