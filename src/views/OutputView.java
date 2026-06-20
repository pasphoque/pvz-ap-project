package views;

public interface OutputView {
    void displayMessage(String message);
    void displayError(String error);
    void displaySuccess(String success);
}