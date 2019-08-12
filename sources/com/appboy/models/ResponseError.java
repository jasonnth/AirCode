package com.appboy.models;

import com.appboy.enums.ErrorType;
import org.json.JSONObject;
import p004bo.app.C0460ds;

public final class ResponseError {

    /* renamed from: a */
    private final ErrorType f2841a;

    /* renamed from: b */
    private final String f2842b;

    public ResponseError(JSONObject object) {
        this.f2841a = (ErrorType) C0460ds.m527a(object, "type", ErrorType.class);
        this.f2842b = object.getString("message");
    }

    public ResponseError(ErrorType errorType, String message) {
        this.f2841a = errorType;
        this.f2842b = message;
    }

    public ErrorType getType() {
        return this.f2841a;
    }

    public String getMessage() {
        return this.f2842b;
    }
}
