package vertx.example.test;

import com.baeldung.VetexLogger;
import com.vertx.example.MyFirstVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.RequestOptions;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
@RunWith(VertxUnitRunner.class)
public class MyFirstExampleTest {

    Vertx vertx;

    @Before
    public void setup(TestContext context){
        vertx = Vertx.vertx();
        vertx.deployVerticle(MyFirstVerticle.class.getName(),context.asyncAssertSuccess());
    }
    @After
    public void tearDown(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }
    @Test
    public void testMyApplication(TestContext context) {
        final Async async = context.async();
        vertx.createHttpClient().getNow(8081, "localhost", "/",
                response -> {
                    response.handler(body -> {
                        context.assertTrue(body.toString().contains("Hello"));
                        async.complete();
                    });
                });
    }


}
