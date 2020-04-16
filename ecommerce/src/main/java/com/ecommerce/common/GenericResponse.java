package com.ecommerce.common;

public class GenericResponse {

  private int errorCode;
  private Object data;
 
  public GenericResponse() {}

  public GenericResponse(int errorCode) {
    this.errorCode = errorCode;
  }

  public GenericResponse(int errorCode, Object data) {
    this(errorCode);
    this.data = data;
  }

  public int getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(int errorCode) {
    this.errorCode = errorCode;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

}
