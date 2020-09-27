package com.edencity.store.service;

public interface PhotoResultListener {
    void onPhotoSuccess(String path);
    void onPhotoFailed(String error);
}
