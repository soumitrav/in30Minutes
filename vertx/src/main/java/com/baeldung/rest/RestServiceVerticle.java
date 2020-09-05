package com.baeldung.rest;

import com.baeldung.HelloVerticle;
import com.baeldung.model.Article;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.Json;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class RestServiceVerticle extends AbstractVerticle {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestServiceVerticle.class);

    @Override
    public void start(Future<Void> future) {

        try {
            Router router = Router.router(vertx);
            router.get("/api/article/:id")
                    .handler(this::getArticles);
            router.get("/api/article/:id")
                    .handler(this::getArticles1);

            vertx.createHttpServer()
                    .requestHandler(router::accept)
                    .listen(config().getInteger("http.port", 8080), result -> {
                        if (result.succeeded()) {
                            future.complete();
                        } else {
                            future.fail(result.cause());
                        }
                    });
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void failurehandler(RoutingContext context){
        LOGGER.info("This is inside error handler");
        LOGGER.error(context.failure());
    }
    private void getArticles(RoutingContext routingContext){
        LOGGER.info("This is inside article");
        String articleId = routingContext.request()
                .getParam("id");
        Article article = new Article(articleId, "This is an intro to vertx", "baeldung", "01-02-2017", 1578);
        //int i = 100/0;
        routingContext.response()
                .putHeader("content-type", "application/json")
                .setStatusCode(201)
                .end(Json.encodePrettily(article));

        routingContext.next();
    }

    private void getArticles1(RoutingContext routingContext) {
        LOGGER.info("This is inside article1");
        String articleId = routingContext.request()
                .getParam("id");
        Article article = new Article("300", "This is an intro to vertx", "baeldung", "01-02-2017", 1578);

        routingContext.response()
                .putHeader("content-type", "application/json")
                .setStatusCode(200)
                .end(Json.encodePrettily(article));
        routingContext.next();
    }

}
