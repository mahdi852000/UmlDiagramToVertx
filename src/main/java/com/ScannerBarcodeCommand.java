package com;

public class ScannerBarcodeCommand {

    private final String barcode;

    public String getBarcode() {
        return barcode;
    }

    public ScannerBarcodeCommand(String barcode) {
        this.barcode = barcode;
    }
}
