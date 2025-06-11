package com;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

public class ScannerBarcodeCommandCodec implements MessageCodec<ScannerBarcodeCommand,ScannerBarcodeCommand> {
    @Override
    public void encodeToWire(Buffer buffer, ScannerBarcodeCommand cmd) {
        buffer.appendString(cmd.getBarcode());

    }

    @Override
    public ScannerBarcodeCommand decodeFromWire(int pos, Buffer buffer) {
        String barcode = buffer.getString(pos,buffer.length());
        return new ScannerBarcodeCommand(barcode);
    }

    @Override
    public ScannerBarcodeCommand transform(ScannerBarcodeCommand cmd) {
        return cmd;
    }

    @Override
    public String name() {
        return "ScannerBarcodeCommandCodec";
    }

    @Override
    public byte systemCodecID() {
        return -1;
    }
}
