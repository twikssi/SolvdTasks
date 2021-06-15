package by.demoqa.web;

import by.demoqa.web.pages.LinksPage;
import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class PageLinksTests extends AbstractTest {

    @Test
    @MethodOwner(owner = "Andrew")
    public void testLinksOpenNewTab() {
        int expectedWindow = 2;
        String expectedLink = "https://demoqa.com/";
        LinksPage linksPage = new LinksPage(getDriver());
        linksPage.open();
        String firstWindow = getDriver().getWindowHandle();
        Assert.assertTrue(linksPage.isPageOpened(), "Link page isn't opened");

        linksPage.clickLinkHome();
        linksPage.clickLinkHomeDYHmW();
        List<String> linksList = getDriver().getWindowHandles().stream().filter(a -> !a.equals(firstWindow)).collect(Collectors.toList());
        Assert.assertEquals(linksList.size(), expectedWindow, "Number of windows don't equal 2");

        getDriver().switchTo().window(linksList.get(0));
        Assert.assertEquals(getDriver().getCurrentUrl(), expectedLink, "Links don't equal");
        getDriver().close();

        getDriver().switchTo().window(linksList.get(1));
        Assert.assertEquals(getDriver().getCurrentUrl(), expectedLink, "Links don't equal");
        getDriver().close();

        getDriver().switchTo().window(firstWindow);
        Assert.assertEquals(getDriver().getWindowHandle(), firstWindow, "Link doesn't equal first link");
    }

    @Test
    @MethodOwner(owner = "Andrew")
    public void testLinksSentApiCall() {
        String expectedCreateLink = "Link has responded with staus 201 and status text Created";
        String expectedNoContentLink = "Link has responded with staus 204 and status text No Content";
        String expectedMoveLink = "Link has responded with staus 301 and status text Moved Permanently";
        String expectedBadRequestLink = "Link has responded with staus 400 and status text Bad Request";
        String expectedUnauthorizedLink = "Link has responded with staus 401 and status text Unauthorized";
        String expectedForbiddenLink = "Link has responded with staus 403 and status text Forbidden";
        String expectedNotFoundLink = "Link has responded with staus 404 and status text Not Found";

        LinksPage linksPage = new LinksPage(getDriver());
        linksPage.open();
        Assert.assertTrue(linksPage.isPageOpened(), "Link page isn't opened");

        linksPage.clickCreateLink();
        pause(1);
        Assert.assertEquals(linksPage.getLinkResponseText(), expectedCreateLink, "Links don't equal");

        linksPage.clickNoContentLink();
        pause(1);
        Assert.assertEquals(linksPage.getLinkResponseText(), expectedNoContentLink, "Links don't equal");

        linksPage.clickMovedLink();
        pause(1);
        Assert.assertEquals(linksPage.getLinkResponseText(), expectedMoveLink, "Links don't equal");

        linksPage.clickBadRequestLink();
        pause(1);
        Assert.assertEquals(linksPage.getLinkResponseText(), expectedBadRequestLink, "Links don't equal");

        linksPage.clickUnauthorizedLink();
        pause(1);
        Assert.assertEquals(linksPage.getLinkResponseText(), expectedUnauthorizedLink, "Links don't equal");

        linksPage.clickForbiddenLink();
        pause(1);
        Assert.assertEquals(linksPage.getLinkResponseText(), expectedForbiddenLink, "Links don't equal");

        linksPage.clickNotFoundLink();
        pause(1);
        Assert.assertEquals(linksPage.getLinkResponseText(), expectedNotFoundLink, "Links don't equal");
    }
}
