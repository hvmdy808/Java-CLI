import java.util.Scanner;

public class Terminal {
    static Parser parser = new Parser();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.print("> ");
            String input = scanner.nextLine();
            input = input.trim();
            parser.parse(input);
            if (parser.getCommandName().isEmpty()) continue;
            if(parser.getCommandName().equals("pwd")){
                System.out.println("lol1");
            } else if(parser.getCommandName().equals("cd")){
                System.out.println("lol2");
            } else if(parser.getCommandName().equals("ls")){
                System.out.println("lol3");
            } else if(parser.getCommandName().equals("mkdir")){
                System.out.println("lol4");
            } else if(parser.getCommandName().equals("rmdir")){
                System.out.println("lol5");
            } else if(parser.getCommandName().equals("touch")){
                System.out.println("lol6");
            } else if(parser.getCommandName().equals("cp")){
                System.out.println("lol7");
            } else if(parser.getCommandName().equals("cp -r")){
                System.out.println("lol8");
            } else if(parser.getCommandName().equals("rm")){
                System.out.println("lol9");
            } else if(parser.getCommandName().equals("cat")){
                System.out.println("lol10");
            } else if(parser.getCommandName().equals("wc")){
                System.out.println("lol11");
            } else if(parser.getCommandName().equals("echo")){
                System.out.println("lol12");
            } else if(parser.getCommandName().equals("zip")){
                System.out.println("lol13");
            } else if(parser.getCommandName().equals("unzip")){
                System.out.println("lol14");
            } else if(parser.getCommandName().equals("exit")){
                break;
            }
            else {
                System.out.println("Wrong command. Please try again.");
            }
            System.out.println(parser.getCommandName());
            for (int i = 0; i < parser.getArgs().length; i++){
                if (parser.getArgs()[i] == null){
                    break;
                }
                System.out.println(parser.getArgs()[i]);
            }
        }
    }
}