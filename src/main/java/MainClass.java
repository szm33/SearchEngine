import com.findwise.SearchEngine;
import com.findwise.SearchEngineImpl;

import java.util.Scanner;

public class MainClass {

    public static void main(String[] args){
        SearchEngine searchEngine = new SearchEngineImpl();
        searchEngine.indexDocument("1", "the brown fox jumped over the brown dog");
        searchEngine.indexDocument("2", "the lazy brown dog sat in the corner");
        searchEngine.indexDocument("3", "the red fox bit the lazy dog");

        while(true){
            System.out.println("Enter term for search");
            Scanner input = new Scanner(System.in);
            String term = input.nextLine();

            if(term.equals("0")) {
                break;
            }
            System.out.println("Result:");
            searchEngine.search(term).forEach(result -> System.out.println("Document with id: " + result.getId()));
        }
    }
}
