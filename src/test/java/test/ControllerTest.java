package test;

import static org.jboss.arquillian.graphene.Graphene.guardHttp;
import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.PomEquippedResolveStage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import controller.*;
import facade.*;
import model.*;
import util.*;

@RunWith(Arquillian.class)
public class ControllerTest {
    private static final String WEBAPP_SRC = "src/main/webapp";
    
    @Deployment(testable = false)
    public static Archive<?> createTestArchive() {

        final PomEquippedResolveStage pom = Maven.resolver().loadPomFromFile("pom.xml");
        
        return ShrinkWrap.create(WebArchive.class, "javaee-security.war")
            .addAsLibraries(
                pom.resolve("org.webjars:bootstrap:3.3.5").withoutTransitivity().as(JavaArchive.class)
            )
            .addAsLibraries(
                pom.resolve("org.webjars:jquery:1.11.3").withoutTransitivity().as(JavaArchive.class)
            )
            .addClasses(
                    User.class, 
                    Role.class, 
                    UserFacade.class, 
                    RoleFacade.class, 
                    Resources.class, 
                    Admin.class, 
                    ChangePassword.class, 
                    Index.class, 
                    Login.class, 
                    Logout.class, 
                    Principal.class, 
                    Register.class, 
                    UserDetail.class
            )
            .addAsResource(new File("src/main/resources/META-INF/persistence.xml"), "META-INF/persistence.xml")
            .addAsResource(new File("src/main/resources/META-INF/create-script.sql"), "META-INF/create-script.sql")
            .addAsResource(new File("src/main/resources/META-INF/drop-script.sql"), "META-INF/drop-script.sql")
            .addAsResource(new File("src/main/resources/META-INF/load-script.sql"), "META-INF/load-script.sql")
            .merge(ShrinkWrap.create(GenericArchive.class).as(ExplodedImporter.class)
                    .importDirectory(WEBAPP_SRC).as(GenericArchive.class),
                    "/", Filters.include(".*\\.xhtml$"))
            .addAsWebResource(new File(WEBAPP_SRC, "resources/img/javaee.png"), "resources/img/javaee.png")
            .addAsWebInfResource(new File(WEBAPP_SRC, "WEB-INF/beans.xml"))
            .addAsWebInfResource(new File(WEBAPP_SRC, "WEB-INF/faces-config.xml"))
            .addAsWebInfResource(new File(WEBAPP_SRC, "WEB-INF/jboss-web.xml"))
            .setWebXML(new File(WEBAPP_SRC, "WEB-INF/web.xml"))
            ;
    }

    @Inject
    Logger log;
    
    @Drone
    private WebDriver browser;

    @ArquillianResource
    private URL deploymentUrl;

    @FindBy(id = "registerForm:email")
    private WebElement email;

    @FindBy(id = "registerForm:password")
    private WebElement password;

    @FindBy(id = "registerForm:firstName")
    private WebElement firstName;

    @FindBy(id = "registerForm:lastName")
    private WebElement lastName;

    @FindBy(id = "registerForm:register")
    private WebElement registerButton;
    
    @FindBy(name = "messages")        // find last occurrence of name="messages" 
    private WebElement facesMessage;
    
    @Test
    public void testRegister() throws Exception {
       browser.get(deploymentUrl.toExternalForm() + "views/register.xhtml");
       
       email.sendKeys("karl@karl.com");
       password.sendKeys("1234");
       firstName.sendKeys("Karl");
       lastName.sendKeys("Nicholas");

       guardHttp(registerButton).click();
       
       assertEquals("Registration Successful!", facesMessage.getText().trim());

/*        
        whoAmI.click();
        waitAjax().until().element(signedAs).is().present();
        assertTrue(signedAs.getText().contains("demo"));
*/        
    }


}
