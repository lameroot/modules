package db;

import junit.framework.TestCase;
import modules.config.db.CacheConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;

/**
 * User: lameroot
 * Date: 02.02.13
 * Time: 11:58
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,classes = {
        CacheConfig.class
})
public class AbstractConfigTest extends TestCase {

    @Resource
    protected ApplicationContext applicationContext;
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testExistsApplicationContext() {
        assertNotNull(applicationContext);
    }
}
