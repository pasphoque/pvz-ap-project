package views;

public class ConsoleView implements OutputView {
    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void displayError(String error) {
        System.out.println("[ERROR]: " + error);
    }

    @Override
    public void displaySuccess(String success) {
        System.out.println("[SUCCESS]: " + success);
    }
}
