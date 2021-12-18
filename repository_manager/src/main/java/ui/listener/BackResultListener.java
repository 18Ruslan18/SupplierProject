package ui.listener;

public interface BackResultListener <T> {

    void onSuccess(T data);

    void onFailed();
}
