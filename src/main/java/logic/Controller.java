package logic;

public class Controller implements Runnable {
    private final iPresenter presenter;
    private final iPrompter prompter;

    public Controller(iPresenter presenter, iPrompter prompter) {
        this.presenter = presenter;
        this.prompter = prompter;
    }

    @Override
    public void run() {
        Commands command;
        while ((command = prompter.promptCommand()) == null);
        switch (command) {
            case RUN:
                String data1 = prompter.promptData("Input numbers for first list with space between them: ");
                String data2 = prompter.promptData("Input numbers for second list with space between them: ");
                try {
                    presenter.presentResult(ListMiddleChecker.compareListsMiddles(StringParser.parseStringToList(data1),
                            StringParser.parseStringToList(data2)));
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case EXIT:
                System.out.println("Program switched off. Goodbye!");
                return;
        }
        run();
    }
}
