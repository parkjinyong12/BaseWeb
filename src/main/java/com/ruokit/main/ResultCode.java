package com.ruokit.main;

public enum ResultCode {

  SUCCESS(1, "success"), FAIL(0, "fail");

  private final int binary;
  private final String text;

  public int getBinary() {
    return binary;
  }

  public String getText() {
    return text;
  }

  private ResultCode(int binary, String text) {
    this.binary = binary;
    this.text = text;
  }

}

