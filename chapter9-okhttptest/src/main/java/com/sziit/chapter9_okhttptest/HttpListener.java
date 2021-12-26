package com.sziit.chapter9_okhttptest;

interface HttpListener {
    void onSuccess(final String strResponse);
    void onFailed(final String strResponse);
}
