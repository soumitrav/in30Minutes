package com.baeldung.rest;

import com.baeldung.model.Article;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.Json;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

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
            router.get("/api/qrcode").handler(this :: codeGen);
            router.get("/api/qrlogo").handler(this :: generateQRWithLogo);

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

    private void codeGen(RoutingContext routingContext) {
        //final Buffer[] buffer = new Buffer[1];
        ByteOutputStream byteOutputStream = getQRCodeStream();
        final ByteOutputStream logo = new ByteOutputStream();
        String resource = getClass().getResource("/twitter.png").getFile();
       File file = new File(resource);
       vertx.fileSystem().readFile(resource,result ->{
          //System.out.println(result.result().toString());
           logo.write(result.result().getBytes());
           //routingContext.response().end(Buffer.buffer(result.result().getBytes()));
       });

        routingContext.response().end(Buffer.buffer(logo.getBytes()));

        /*vertx.fileSystem().readFile("C:\\QRCodes\\NewQRCodeGen.png", result ->{
            if(result.succeeded()){
                buffer[0] = result.result();
                routingContext.response().end(result.result());
            }*/
        //});
        //Buffer buffer = Buffer.buffer(byteArray);///This give compilation error. No Suitable method found.
        /*routingContext.response()

                .putHeader("Content-Type","multipart/form-data")
        .setStatusCode(200)
                //.putHeader("Content-Length", String.valueOf(buffer[0].length()))
                //.write(buffer[0])
                //.putHeader("content-type", "application/json")
                //.end("Hello");
        .end(buffer[0]);*/
    }


    private void generateQRWithLogo(RoutingContext routingContext) {
        ByteOutputStream outputStream = getQRCodeStream();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        String LOGO = getClass().getResource("/twitter.png").getFile();
        try {
            BufferedImage qrImage = getOverlyFromStream(inputStream);
            BufferedImage overly = getOverlyFromFile(LOGO);
            routingContext.response().end(Buffer.buffer(getQRWithLogo(qrImage, overly).toByteArray()));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public ByteArrayOutputStream getQRWithLogo(BufferedImage qrImage, BufferedImage overly ) throws IOException {
        int deltaHeight = qrImage.getHeight() - overly.getHeight();
        int deltaWidth = qrImage.getWidth() - overly.getWidth();

        // Initialize combined image
        BufferedImage combined = new BufferedImage(qrImage.getHeight(), qrImage.getWidth(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) combined.getGraphics();

        // Write QR code to new image at position 0/0
        g.drawImage(qrImage, 0, 0, null);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        // Write logo into combine image at position (deltaWidth / 2) and
        // (deltaHeight / 2). Background: Left/Right and Top/Bottom must be
        // the same space for the logo to be centered
        g.drawImage(overly, (int) Math.round(deltaWidth / 2), (int) Math.round(deltaHeight / 2), null);

        // Write combined image as PNG to OutputStream
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(combined, "png", os);

        return os;
    }
    private BufferedImage getOverlyFromFile(String LOGO) throws IOException {
        return ImageIO.read(new File(LOGO));
    }
    private BufferedImage getOverlyFromStream(ByteArrayInputStream stream) throws IOException {
        return ImageIO.read(stream);
    }
    private ByteOutputStream getQRCodeStream() {
        ByteOutputStream byteOutputStream = new ByteOutputStream();
        QRCode.from("http://amex.ShortURLQRCodeGen").to(ImageType.PNG)
                .withSize(200, 200).
                writeTo(byteOutputStream);
        return byteOutputStream;
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
        routingContext.response().closeHandler(event->{
            System.out.print("Wrror");
        });

        routingContext.response()
                .putHeader("content-type", "application/json")
                .setStatusCode(200)
                .end(Json.encodePrettily(article));
        routingContext.next();
    }

}
