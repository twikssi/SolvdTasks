package by.demoqa.web;

import by.demoqa.web.pages.DynamicPropertiesPage;
import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PageDynamicPropertiesTests extends AbstractTest {

    @Test
    @MethodOwner(owner = "Andrew")
    public void testDynamicButtons() {
        int expectedStandBy = 5;
        DynamicPropertiesPage dynamicPropertiesPage = new DynamicPropertiesPage(getDriver());
        dynamicPropertiesPage.open();

        Assert.assertTrue(dynamicPropertiesPage.isPageOpened(), "Page isn't opened");
        Assert.assertFalse(dynamicPropertiesPage.isEnableButtonEnable(), "Enable button is enable");
        Assert.assertFalse(dynamicPropertiesPage.isColorButtonChangeColor(), "Color is changed");
        Assert.assertFalse(dynamicPropertiesPage.isInvisibleButtonVisible(), "Visible button is visible");
        dynamicPropertiesPage.waitObjectAppears(expectedStandBy);
        Assert.assertTrue(dynamicPropertiesPage.isEnableButtonEnable(), "Enable button is disable after standby");
        Assert.assertTrue(dynamicPropertiesPage.isColorButtonChangeColor(), "Color isn't changed");
        Assert.assertTrue(dynamicPropertiesPage.isInvisibleButtonVisible(), "Visible button is invisible");
    }
}
