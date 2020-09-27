package com.edencity.store.alipay;

public interface AliPayResultListener {
  void onPaySuccess();

  void onPaying();

  void onPayFail();

  void onPayCancel();

  void onPayConnectError();
}
