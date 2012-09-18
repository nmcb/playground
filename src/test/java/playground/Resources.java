package playground;

import nl.loxia.railcloud.InfraResource;
import nl.loxia.railcloud.core.Link;
import nl.loxia.railcloud.playground.ISandbox;
import nl.loxia.railcloud.server.LocalCloud;
import org.jboss.resteasy.client.ProxyFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URL;
import java.util.List;

public class Resources
{
    private static final String PATH_SEP = "/";
    private static final String URL_CLOUD = "http://localhost:8666";
    private static final String URL_VALUES = PATH_SEP + "values";

    private static LocalCloud cloud = LocalCloud.getInstance();

    private static InfraResource resource = ProxyFactory.create( InfraResource.class, URL_CLOUD );

    @BeforeClass
    public static void init()
    {
        cloud.start();
    }

    @AfterClass
    public static void cleanup()
    {
        cloud.stop();
    }

    @Before
    public void dropDatabase()
    {
        cloud.dropDatabase("playground");
    }

    @Test
    @SuppressWarnings("unchecked")
    public void pgTestRegistreerSandbox()
    {
        ISandbox sandbox = new Sandbox();
        sandbox.setName( "Da Big One!" );
        URL url = resource.registreer( sandbox );
        Assert.assertNotNull( url );
        Assert.assertEquals( URL_CLOUD + URL_VALUES + PATH_SEP + sandbox.getMetaInfo().getUUID(), url.toString() );
    }

    @Test
    public void pgTestRegistreerSand()
    {
        Sand sand = new Sand();
        sand.setColor( "White as Snow" );
        URL url = resource.registreer( sand );
        Assert.assertNotNull( url );
        Assert.assertEquals( URL_CLOUD + URL_VALUES + PATH_SEP + sand.getMetaInfo().getUUID().toString(), url.toString() );
    }

    /**
     * TODO Move to railcloud integration tests.
     */
    @Test
    public void rcTestListValues()
    {
        Sand sand = new Sand();
        sand.setColor( "White as Snow" );
        ISandbox sandbox = new Sandbox();
        sandbox.setName( "Da Big One!" );
        URL urlSand = resource.registreer( sand );
        URL urlSandbox = resource.registreer( sandbox );
        String sandClassTypeName = sandbox.getClassType().getName();
        String sandboxClassTypeName = sandbox.getClassType().getName();

        List<Link> links = resource.list();

        Assert.assertNotNull( links );
        Assert.assertEquals( 2, links.size() );
        Assert.assertEquals( urlSand, links.get( 0 ).getURL() );
        Assert.assertEquals( sandClassTypeName, links.get( 0 ).getValueTypeName() );
        Assert.assertEquals( urlSandbox, links.get( 1 ).getURL() );
        Assert.assertEquals( sandboxClassTypeName, links.get( 1 ).getValueTypeName() );
    }
}
