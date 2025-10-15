import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
public class Terminal {
   static Parser parser = new Parser();

    static File currentDirectory = new File(System.getProperty("user.dir"));

    public static String pwd() {
        return currentDirectory.getAbsolutePath();
    }
    public static void cd(String[] args) {

        if (args.length == 0) {
            currentDirectory = new File(System.getProperty("user.home"));
            return;
        }
        String fullPath = String.join(" ", args).replace("\"", "").trim();

        if (fullPath.equals("..")) {
            File parent = currentDirectory.getParentFile();
            if (parent != null) {
                currentDirectory = parent;
            } else {
                System.out.println("Already at root directory.");
            }
            return;
        }
        File newDir = new File(fullPath);
        if (!newDir.isAbsolute()) {
            newDir = new File(currentDirectory, fullPath);
        }
        if (newDir.exists() && newDir.isDirectory()) {
            currentDirectory = newDir;
        } else {
            System.out.println("Error: Directory not found.");
        }
    }

  public static String[] ls() {
      File[] files = currentDirectory.listFiles();

      if (files == null || files.length == 0) {
          System.out.println("Directory is empty.");
          return new String[0];
      }
      Arrays.sort(files, (a, b) -> a.getName().compareToIgnoreCase(b.getName()));
      String[] fileNames = new String[files.length];
      for (int i = 0; i < files.length; i++) {
          fileNames[i] = files[i].getName();
      }

      for (String name : fileNames) {
          System.out.println(name);
      }

      return fileNames;
  }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.print("> ");
            String input = scanner.nextLine();
            input = input.trim();
            parser.parse(input);
            if (parser.getCommandName().isEmpty()) continue;
            if(parser.getCommandName().equals("pwd")){
                System.out.println(pwd());
            } else if(parser.getCommandName().equals("cd")){
                cd(parser.getArgs());
            } else if(parser.getCommandName().equals("ls")){
                ls();
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
            } else if(parser.getCommandName().equals(">")){
                System.out.println("lol12");
            } else if(parser.getCommandName().equals(">>")){
                System.out.println("lol13");
            } else if(parser.getCommandName().equals("zip")){
                System.out.println("lol14");
            } else if(parser.getCommandName().equals("unzip")){
                System.out.println("lol15");
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