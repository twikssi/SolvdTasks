package by.demoqa.email;

import by.demoqa.util.email.EmailUtil;
import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.crypto.CryptoConsole;
import com.qaprosoft.carina.core.foundation.crypto.CryptoTool;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.mail.MessagingException;

public class EmailTest extends AbstractTest {

    @Test
    @MethodOwner(owner = "Andrew")
    public void testEmail() {
        String expectedContent = "Hi, fat Homie";
        String expectedSubject = "fatpositive";
        EmailUtil emailUtil = new EmailUtil();
        int expectedSize = emailUtil.getNumberOfMessage() + 1;
        emailUtil.sendMessage("limpbiskit15@gmail.com", expectedContent, expectedSubject);
        Assert.assertEquals(emailUtil.getNumberOfMessage(), expectedSize, "Number of messages don't match");
        Assert.assertEquals(emailUtil.getContentMessage(EmailUtil.LAST_MESSAGE), expectedContent, "Contents don't match");
        Assert.assertEquals(emailUtil.getSubjectMessage(EmailUtil.LAST_MESSAGE), expectedSubject, "Subject don't match");

        for (int i = expectedSize - 5; i <= expectedSize; i++) {
            if (emailUtil.getContentMessage(i).equals(expectedContent)) {
                Assert.assertEquals(emailUtil.getContentMessage(expectedSize), expectedContent, "Contents don't match");
                break;
            }
        }

        emailUtil.deleteMessage(EmailUtil.LAST_MESSAGE);
        Assert.assertEquals(emailUtil.getNumberOfMessage(), expectedSize - 1, "Number of messages don't match");

    }
}
