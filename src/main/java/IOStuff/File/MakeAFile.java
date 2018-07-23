package IOStuff.File;

import java.io.File;

public class MakeAFile
{

    public static void main(String[] args)
    {

        File file = new File("C:\\IdeaProjects\\JavaFeatureExplore\\src\\main\\java\\IOStuff\\File\\amazon.csv");

        System.out.println(file.isFile());
    }


}
