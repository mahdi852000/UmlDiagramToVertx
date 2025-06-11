package com.iwu.connector;

public class ScannerBarcodeCommand {

    private final String barcode;

    public String getBarcode() {
        return barcode;
    }

    public ScannerBarcodeCommand(String barcode) {
        this.barcode = barcode;
    }
}
