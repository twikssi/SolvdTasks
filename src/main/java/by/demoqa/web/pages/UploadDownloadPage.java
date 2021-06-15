package by.demoqa.web.pages;

import com.qaprosoft.carina.core.foundation.utils.Configuration;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class UploadDownloadPage extends AbstractPage {

    @FindBy(linkText = "Download")
    private ExtendedWebElement downloadButton;

    @FindBy(id = "uploadFile")
    private ExtendedWebElement uploadButton;

    @FindBy(id = "uploadedFilePath")
    private ExtendedWebElement uploadFilePath;

    public UploadDownloadPage(WebDriver driver) {
        super(driver);
        setPageURL(R.CONFIG.get(Configuration.get(Configuration.Parameter.URL)) + "/upload-download");
    }

    public String getUploadFilePathText() {
        return uploadFilePath.getText();
    }

    public void clickDownloadButton() {
        downloadButton.click();
    }

    public void uploadFile(String path) {
        uploadButton.attachFile(path);
    }
}
