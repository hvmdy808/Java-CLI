import java.util.Arrays;

public class Parser {
    private String commandName;
    private String[] args;
    private String redirectOp;
    private String redirectFile;
    public boolean parse(String input) {
        redirectOp = null;
        redirectFile = null;
        try{
            input = input.trim();
            if (input.isEmpty()) {
                commandName = "";
                args = new String[0];
                return false;
            }
            String [] arr = input.split(" +");
            int i = 1;
            commandName = arr[0];
            if (commandName.equals("cp") && arr.length > 1 && arr[1].equals("-r")) {
                commandName = commandName + " " + arr[1];
                i = 2;
            }
            if (commandName.equals("cp -r") && arr.length <= 2) {
                return false;
            }
            int j = 0;
            args = new String[arr.length - i];
            for (; i < arr.length; i++) {
                args[j] = arr[i];
                j++;
            }


            for (int k = 0; k < args.length; k++) {
                if (args[k].equals(">") || args[k].equals(">>")) {
                    redirectOp = args[k];
                    if (k + 1 < args.length) {
                        redirectFile = args[k + 1];
                    } else {
                        return false;
                    }
                    args = Arrays.copyOfRange(args, 0, k);
                    break;
                }
            }

            return true;
        }
        catch (Exception e) {
            commandName = "";
            args = new String[0];
            return false;
        }
    }
    public String getCommandName() {return commandName;}
    public String[] getArgs() {return args;}
    public String getRedirectOp() { return redirectOp; }
    public String getRedirectFile() { return redirectFile; }

}
