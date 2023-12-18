package logic;

public class PresenterSystem implements iPresenter {
    public void presentResult(String data) {
        System.out.println("Result: " + data);
    }
}
