package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Prompter implements iPrompter {

    public String promptData(String promptText) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(promptText);
        return scanner.nextLine();

    }

    public Commands promptCommand() {
        StringBuilder sb = new StringBuilder();
        sb.append("Commands: \n");
        sb.append("(");
        for (Commands command : Commands.values()) {
            sb.append(command).append(", ");
        }
        sb.replace(sb.length() - 2, sb.length() - 1, ")");
        sb.append("\n");
        sb.append("Input command: ");
        System.out.print(sb);
        Scanner scanner = new Scanner(System.in);
        String data = scanner.nextLine();
        try {
            return Commands.valueOf(data.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong command!");
            return null;
        }
    }
}
