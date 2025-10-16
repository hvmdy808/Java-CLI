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
    public static void touch(String[] args) {
        if (args.length != 1) {
            System.out.println("Command 'touch' takes exactly one argument.");
            return;
        }

        String filePath = args[0];
        File file;

        // Handle absolute or relative paths
        if (new File(filePath).isAbsolute()) {
            file = new File(filePath);
        } else {
            file = new File(currentDirectory, filePath);
        }

        try {
            // Create parent directories if they don't exist
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            // Create the file
            if (file.exists()) {
                // Update timestamp if file exists (standard touch behavior)
                file.setLastModified(System.currentTimeMillis());
                System.out.println("File timestamp updated: " + file.getName());
            } else if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("Error: Could not create the file.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void cat(String[] args) {
        if (args.length < 1 || args.length > 2) {
            System.out.println("Command 'cat' takes one or two arguments.");
            return;
        }

        StringBuilder content = new StringBuilder();

        // Process each file
        for (String filePath : args) {
            File file;

            // Handle absolute or relative paths
            if (new File(filePath).isAbsolute()) {
                file = new File(filePath);
            } else {
                file = new File(currentDirectory, filePath);
            }

            // Check if file exists
            if (!file.exists() || !file.isFile()) {
                System.out.println("Error: File not found: " + filePath);
                return;
            }

            try {
                // Read file content
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    content.append(scanner.nextLine()).append("\n");

                }
                scanner.close();
            } catch (Exception e) {
                System.out.println("Error reading file: " + e.getMessage());
                return;
            }
        }



        System.out.print(content.toString());

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
                if (parser.getArgs().length != 0) {
                    System.out.println("Command 'pwd' must have no arguments.");
                    continue;
                }
                System.out.println(pwd());
            } else if(parser.getCommandName().equals("cd")){
                if (parser.getArgs().length > 1) {
                    System.out.println("Command 'cd' must have no more than 1 argument.");
                    continue;
                }
                cd(parser.getArgs());
            } else if(parser.getCommandName().equals("ls")){
                if (parser.getArgs().length != 0) {
                    System.out.println("Command 'ls' must have no arguments.");
                    continue;
                }
                ls();
            } else if(parser.getCommandName().equals("mkdir")){
                System.out.println("lol4");
            } else if(parser.getCommandName().equals("rmdir")){
                System.out.println("lol5");
            } else if(parser.getCommandName().equals("touch")){
                touch(parser.getArgs());
            } else if(parser.getCommandName().equals("cp")){
                System.out.println("lol7");
            } else if(parser.getCommandName().equals("cp -r")){
                System.out.println("lol8");
            } else if(parser.getCommandName().equals("rm")){
                System.out.println("lol9");
            } else if(parser.getCommandName().equals("cat")){
                cat(parser.getArgs());
            } else if(parser.getCommandName().equals("wc")){
                System.out.println("lol11");
            } else if(parser.getCommandName().equals("echo")){
                System.out.println("lol12");
            } else if(parser.getCommandName().equals("zip")){
                System.out.println("lol13");
            } else if(parser.getCommandName().equals("unzip")){
                System.out.println("lol14");
            } else if(parser.getCommandName().equals("exit")){
                if (parser.getArgs().length != 0) {
                    System.out.println("Command 'exit' must have no arguments.");
                    continue;
                }
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