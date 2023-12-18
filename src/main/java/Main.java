import logic.*;

public class Main {
    public static void main(String[] args) {
        iPresenter presenterSystem = new PresenterSystem();
        iPrompter prompter = new Prompter();
        Controller controller = new Controller(presenterSystem, prompter);
        controller.run();
    }
}
