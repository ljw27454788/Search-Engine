import java.io.File;
import java.util.*;

/**
 * CS 180 - Project 4
 * This program model search
 *
 * @author Lu, JiaWei, lu425@purdue.edu, L14.  Zhang, JunYi, zhan2539@purdue.edu, L14
 * @version 4/7/2017
 */
public class Search extends Object {

    public static List<Page> pageList;
    public static List<Word> wordList;
    public static List<Result> resultSet;
    private String wordListFile;
    private String pageListFile;


    public Search(String wordListFile, String pageListFile) {
        this.wordListFile = wordListFile;
        this.pageListFile = pageListFile;

        FileUtils fu = new FileUtils();
        pageList = fu.getPageList(pageListFile);
        wordList = fu.getWordList(wordListFile);
        resultSet = new ArrayList<Result>();
        resultSet = Collections.synchronizedList(resultSet);
        pageList = Collections.synchronizedList(pageList);
        wordList = Collections.synchronizedList(wordList);
    }

    public void setup(String wordListFile, String pageListFile) {
        FileUtils fu = new FileUtils();

        wordList = fu.getWordList(wordListFile);
        pageList = fu.getPageList(pageListFile);
    }

    public List<Result> executeQuery(String query) {
        query = query.toLowerCase();
        String[] terms = query.split(" ");


        int s = 0;
        int finish = wordList.size() / 5;

        Runnable r = new SearchThread(s, finish, terms);
        Thread[] threads = new Thread[5];
        threads[0] = new Thread(r);
        r = new SearchThread(finish + 1, finish * 2, terms);
        threads[1] = new Thread(r);
        r = new SearchThread((finish * 2) + 1, finish * 3, terms);
        threads[2] = new Thread(r);
        r = new SearchThread((finish * 3) + 1, finish * 4, terms);
        threads[3] = new Thread(r);
        r = new SearchThread((finish * 4) + 1, wordList.size() - 1, terms);
        threads[4] = new Thread(r);



        for (Thread st : threads) {
            st.start();
        }

        for (Thread st : threads) {
            try {
                st.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        SearchThread st = new SearchThread(0, wordList.size() - 1, terms);
        st.run();


        sort();


        return resultSet;
    }

    public void nullCheck() {
        if (pageList == null || wordList == null) {
            setup(wordListFile, pageListFile);
        }
    }

    public void sort() {

        Collections.sort(resultSet);
        Collections.sort(resultSet);
    }


}
