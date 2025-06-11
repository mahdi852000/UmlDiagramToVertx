package com.iwu.connector;
import io.vertx.core.Vertx;

public class Main {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();


        vertx.eventBus().registerDefaultCodec(InstructionCommand.class, new InstructionCommandCodec());
        vertx.eventBus().registerDefaultCodec(ScannerBarcodeCommand.class, new ScannerBarcodeCommandCodec());


        vertx.deployVerticle(new DMCCScannerVerticle("DMCCScannerConnector"));


        vertx.setTimer(2000, id -> {
            vertx.eventBus().send("scanner.instruction", new InstructionCommand("Start Scanning"));
            vertx.eventBus().send("scanner.barcode", new ScannerBarcodeCommand("1234567890"));
        });
    }
}
