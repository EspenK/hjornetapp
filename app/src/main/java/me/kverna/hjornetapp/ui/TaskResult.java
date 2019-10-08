package me.kverna.hjornetapp.ui;

import androidx.annotation.Nullable;

/**
 * Authentication result : success (user details) or error message.
 */
public class TaskResult<T> {
    @Nullable
    private T success;
    @Nullable
    private Integer error;

    public void setSuccess(@Nullable T success) {
        this.success = success;
    }

    public void setError(@Nullable Integer error) {
        this.error = error;
    }

    @Nullable
    public T getSuccess() {
        return success;
    }

    @Nullable
    public Integer getError() {
        return error;
    }

    public boolean isSuccess() {
        return success != null && error == null;
    }
}
