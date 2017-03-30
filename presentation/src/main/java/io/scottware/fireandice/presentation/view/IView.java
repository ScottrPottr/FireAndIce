package io.scottware.fireandice.presentation.view;

public interface IView {

    String getInitialData(String key);

    void end();

    void toast(String toastMessage);

}
