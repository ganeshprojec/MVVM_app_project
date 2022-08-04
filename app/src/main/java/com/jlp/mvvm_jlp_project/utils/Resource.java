package com.jlp.mvvm_jlp_project.utils;/*
 * Created by Sandeep(Techno Learning) on 01,July,2022
 */

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Resource<T> {
    @NonNull
    public final Status status;
    @Nullable
    public final T data;
    @Nullable public final String message;
    @Nullable public final String code;
    public Resource(@NonNull Status status, @Nullable T data,
                    @Nullable String message, @Nullable String code) {
        this.status = status;
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(Status.SUCCESS, data, null, null);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(Status.ERROR, data, msg, null);
    }

    public static <T> Resource<T> error(String errorMessage, String errorCode, @Nullable T data) {
        return new Resource<>(Status.ERROR, data, errorMessage, errorCode);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(Status.LOADING, data, null, null);
    }

    public enum Status { SUCCESS, ERROR, LOADING }
}
