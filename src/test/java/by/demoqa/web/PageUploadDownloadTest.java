package by.demoqa.web;

import by.demoqa.util.FileUtil;
import by.demoqa.web.pages.UploadDownloadPage;
import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.file.Path;

public class PageUploadDownloadTest extends AbstractTest {
    private static final Path path = Path.of(R.CONFIG.get("download_path") + "sampleFile.jpeg");
    private FileUtil fileUtil;

    @BeforeClass
    @AfterTest
    public void deleteFileIfExists() {
        fileUtil = new FileUtil();
        fileUtil.deleteFileIfExists(path);
    }

    @Test(dependsOnMethods = "testDownloading")
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
        UploadDownloadPage uploadDownloadPage = new UploadDownloadPage(getDriver());
        uploadDownloadPage.open();
        Assert.assertTrue(uploadDownloadPage.isPageOpened(), "Link page isn't opened");

        uploadDownloadPage.clickDownloadButton();
        Assert.assertTrue(fileUtil.isFilePresent(path), "File isn't existed");
    }
}
