import java.util.Arrays;

public class Parser {
    private String commandName;
    private String[] args;
    public void parse(String input) {
        input = input.trim();
        if (input.isEmpty()) {
            commandName = "";
            args = new String[0];
            return;
        }
        String [] arr = input.split(" +");
        int i = 1;
        commandName = arr[0];
        if (commandName.equals("cp") && arr.length > 1 && arr[1].equals("-r")) {
            commandName+=' '+arr[1];
            i = 2;
        }
        int j = 0;
        args = new String[arr.length - i];
        for (; i < arr.length; i++) {
            args[j] = arr[i];
            j++;
        }
    }
    public String getCommandName() {
        return commandName;
    }
    public String[] getArgs() {
        return args;
    }
}
