package com;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DMCCScannerVerticle extends AbstractVerticle {
    private static final Logger logger = LoggerFactory.getLogger(DMCCScannerVerticle.class);
    private final String name;

    public DMCCScannerVerticle(String name) {
        this.name = name;
    }

    @Override
    public void start(Promise<Void> startPromise) {
        logger.info("{} started", name);


        vertx.setPeriodic(10_000, id -> logger.info("[HEARTBEAT] {}: I'm alive!", name));
        vertx.eventBus().consumer("scanner.instruction", message->{
            try {
                InstructionCommand cmd = (InstructionCommand) message.body();
                logger.info("[{}] Received instruction: {}", name, cmd.getCommand());
                message.reply("Instruction '" + cmd.getCommand() + "' received successfully.");
            }
            catch (Exception e) {
                logger.error("[{}] Error processing instruction", name, e);
                message.fail(500, "Failed to process instruction.");
            }

    });
        vertx.eventBus().consumer("scanner.barcode", message -> {
            try {
                ScannerBarcodeCommand cmd = (ScannerBarcodeCommand) message.body();
                logger.info("[{}] Received scan barcode command: {}", name, cmd.getBarcode());
                message.reply("Barcode '" + cmd.getBarcode() + "'processed successfully");
            }
            catch (Exception e) {
                logger.error("[{}] Error processing barcode", name, e);
                message.fail(500, "Failed to process barcode.");
            }
        });

    startPromise.complete();
    }
}