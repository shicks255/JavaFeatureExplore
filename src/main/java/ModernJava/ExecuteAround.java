package ModernJava;

import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Consumer;

public class ExecuteAround
{
    public static void main(String[] args) {
        //This way was cleaner, because handled the try/catch in the functional interface
        consumeFile("myFile.txt", (file) -> {
            file.append("This is functional java! :)");
        });

        consumeFile2("myConsumedFile.txt", fileWriter -> {
            try{
                fileWriter.write("Something from the other side");
            } catch (IOException e) {
                System.out.println(e);
            }
        });
    }

    public static void consumeFile2(String fileName, Consumer<FileWriter> action) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            action.accept(fileWriter);
            System.out.println("File written successfully");
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void consumeFile(String fileName, FileConsumer action)
    {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            action.fileAction(fileWriter);
            System.out.println("File successfully written");
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    @FunctionalInterface
    public interface FileConsumer {
        void fileAction(FileWriter fileWriter) throws IOException;
    }

}
