package by.demoqa.web;

import by.demoqa.util.FileUtil;
import by.demoqa.web.pages.UploadDownloadPage;
import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.util.Hashtable;
import java.util.Map;

public class PageUploadDownloadTest extends AbstractTest {
    private static final Path path = Path.of(R.CONFIG.get("download_path") + "sampleFile.jpeg");
    private FileUtil fileUtil;

    @BeforeClass
    @AfterTest
    public void deleteFileIfExists() {
        fileUtil = new FileUtil();
        fileUtil.deleteFileIfExists(path);
    }

    @Test(dependsOnMethods = "testDownloading", enabled = false)
    @MethodOwner(owner = "Andrew")
    public void testUploading() {
        String expecteduploadPath = "C:\\fakepath\\sampleFile.jpeg";
        UploadDownloadPage uploadDownloadPage = new UploadDownloadPage(getDriver());
        uploadDownloadPage.open();
        Assert.assertTrue(uploadDownloadPage.isPageOpened(), "Link page isn't opened");

        uploadDownloadPage.uploadFile(path.toString());
        Assert.assertEquals(uploadDownloadPage.getUploadFilePathText(), expecteduploadPath, "File isn't uploaded");
    }

    @Test
    @MethodOwner(owner = "Andrew")
    public void testDownloading() {
        String downloadFilepath = R.CONFIG.get("download_path");

        Map<String, Object> preferences = new Hashtable<String, Object>();
        preferences.put("profile.default_content_settings.popups", 0);
        preferences.put("download.prompt_for_download", "false");
        preferences.put("download.default_directory", downloadFilepath);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", preferences);

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        UploadDownloadPage uploadDownloadPage = new UploadDownloadPage(getDriver("chrome", capabilities));
        uploadDownloadPage.open();
        Assert.assertTrue(uploadDownloadPage.isPageOpened(), "Link page isn't opened");

        uploadDownloadPage.clickDownloadButton();
        Assert.assertTrue(fileUtil.isFilePresent(path), "File isn't existed");
    }
}
