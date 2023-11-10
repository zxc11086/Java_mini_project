import java.util.*;
import java.io.IOException;
import java.nio.file.*;

/*代码逻辑
通过按字节读取来统计字符数目
通过转成字符串再分割来统计单词数目
输入路径为绝对路径*/

public class Main3{
    public static void main(String [] args) throws IOException{
        Scanner in = new Scanner(System.in);
        System.out.println("please enter the route of the file: ");
        String pathString = in.next();
        Path path = Paths.get(pathString);
        int numOfWords = 1;
        int numOfCharacters = 0;
        byte[] bytes = Files.readAllBytes(path);
        //String s = String(bytes);
        String[] words = new String(bytes).split("[\\s,.!?]+");
        numOfWords = words.length;
        numOfCharacters = bytes.length;
        System.out.println("numOfCharacters : " + numOfCharacters);
        System.out.println("numOfWords : " + numOfWords);
    }
}