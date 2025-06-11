package com.iwu.connector;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DMCCScannerVerticle extends AbstractVerticle {
    private final Logger logger = LoggerFactory.getLogger(DMCCScannerVerticle.class);
    private static final Logger log = LoggerFactory.getLogger(DMCCScannerVerticle.class);
    private final String name;

    public DMCCScannerVerticle(String name) {
        this.name = name;
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        logger.info("{} started", name);


    vertx.setPeriodic(10_000,id->{
        logger.info("[HEARTBEAT] {}: I'm alive!", name);
    });
    vertx.eventBus().consumer("scanner.barcode", message->{
        InstructionCommand cmd = (InstructionCommand) message.body();
        logger.info("[{}] Received instruction: {}", name, cmd.getCommand());
    });
        vertx.eventBus().consumer("scanner.barcode", message -> {
            ScannerBarcodeCommand cmd = (ScannerBarcodeCommand) message.body();
            logger.info("[{}] Received scan barcode command: {}", name, cmd.getBarcode());
        });

    startPromise.complete();
    }
}
