package com;
import io.vertx.core.Vertx;

public class Main {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.eventBus().registerDefaultCodec(InstructionCommand.class, new InstructionCommandCodec());
        vertx.eventBus().registerDefaultCodec(ScannerBarcodeCommand.class, new ScannerBarcodeCommandCodec());


        vertx.deployVerticle(new DMCCScannerVerticle("DMCCScannerConnector"));


        vertx.setTimer(2000, id -> {
            vertx.eventBus().request("scanner.instruction", new InstructionCommand("Start Scanning"),
                    reply->{
                        if(reply.succeeded()){
                            System.out.println("✅ Instruction Reply: " + reply.result().body());
                        } else {System.out.println("❌ Instruction Error: " + reply.cause().getMessage());
                    }
                    });

            vertx.eventBus().request("scanner.barcode", new ScannerBarcodeCommand("1234567890"),
                    reply-> {
                if(reply.succeeded()) {
                    System.out.println("✅ Barcode Reply: " + reply.result().body());
                } else {
                    System.out.println("❌ Barcode Error: " + reply.cause().getMessage());
                }
                    });
        });
    }
}
