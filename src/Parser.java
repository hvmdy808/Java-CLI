import java.util.Arrays;

public class Parser {
    private String commandName;
    private String[] args = new String[4];
//    public Parser(String commandName, String[] args) {
//        this.commandName = commandName;
//        this.args = args;
//    }
    public void parse(String input) {
        String [] arr = input.split(" ");
        int i = 1;
        commandName = arr[0];
        if (commandName.equals("cp") && arr[1].equals("-r")) {
            commandName+=' '+arr[1];
            i = 2;
        }
        int j = 0;
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





//    public void parse(String input) {
//        StringBuilder name = new StringBuilder();
//        int i = 0;
//        for (i=0; i<input.length(); i++) {
//            char c = input.charAt(i);
//            if(c != ' ') {
//                name.append(c);
//            }else{
//                i++;
//                break;
//            }
//        }
//        if(name.equals("cp")){
//            StringBuilder word = new StringBuilder();
//            for (; i < input.length(); i++) {
//                char c = input.charAt(i);
//                if(c != ' ') {
//                    word.append(c);
//                }else{
////                    i++;
//                    break;
//                }
//            }
//            if(word.toString().equals("-r")){
//                name.append(' ').append(word.toString());
//            }else{
//                args[0] = word.toString();
//            }
//            commandName = name.toString();
//        }
//
//    }