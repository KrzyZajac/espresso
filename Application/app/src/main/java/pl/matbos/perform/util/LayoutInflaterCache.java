package pl.matbos.perform.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;

public class LayoutInflaterCache {

    private final LayoutInflater inflater;

    public LayoutInflaterCache(@NonNull Context context) {
        //noinspection ConstantConditions
        if (context == null) {
            throw new IllegalArgumentException("Context passed to the LayoutInflaterCache must not be null.");
        }
        inflater = LayoutInflater.from(context);
    }

    public LayoutInflater getInflater() {
        return inflater;
    }
}