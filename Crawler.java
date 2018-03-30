import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * CS 180 - Project 4
 * This program model crawler
 *
 * @author Lu, JiaWei, lu425@purdue.edu, L14.  Zhang, JunYi, zhan2539@purdue.edu, L14
 * @version 4/7/2017
 */
public class Crawler extends Object {
    public MyQueue toParse;
    public static List<Page> parsed;
    public static List<String> visited;
    public static List<Word> words;
    public static Parser parser;
    public static int currentID;
    public static int totalURLs;
    public static int limit;
    public static String domain;

    public Crawler(String seed, String domain, int limit) {
        toParse = new MyQueue();
        toParse.add(seed);
        this.domain = domain;
        this.limit = limit;
        totalURLs = 0;

        parsed = new ArrayList<Page>();
        visited = new ArrayList<String>();
        words = new ArrayList<Word>();
        parser = new Parser();

    }

    public void crawl() {
        currentID = 0;

        while (!toParse.isEmpty() && currentID < limit) {
            String url = null;
            Object urldata = toParse.remove().getData();

            Document d = null;

            if (urldata instanceof String) {
                url = (String) urldata;
                try {
                    currentID++;
                    d = parser.getDocument(url);



                } catch (ParseException e) {
                    currentID--;
                    totalURLs--;
                    continue;
                }

                totalURLs--;
                parse(d, currentID);
                addPageToList(new Page(url, currentID));
            }
        }
    }

    public boolean parse(Document doc, int id) {
        parseLinks(doc);
        parseText(doc, id);
        return true;
    }

    public void parseLinks(Document doc) {
        try {
            Elements e = parser.getLinks(doc);
            for (Element link : e) {
                String li = link.attr("abs:href");
                boolean nohave = true;
                for (int i = 0; i < visited.size(); i++) {
                    if (visited.get(i).equals(li)) {
                        nohave = false;
                    }
                }

                if (nohave && isInDomain(li) && isValidURL(li) && totalURLs < limit) {
                    addToQueue(li);
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void parseText(Document doc, int id) {
        try {
            String body = parser.getBody(doc);

            if (body == null) {
                return;
            }
            String[] wordlist = body.split(" ");
            for (int i = 0; i < wordlist.length; i++) {
                addWordToList(wordlist[i].toLowerCase(), id);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void addWordToList(String word, int id) {

        boolean added = true;
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).getWord().equals(word)) {
                words.get(i).getList().add(id);
                added = false;
            }
        }
        if (added) {
            words.add(new Word(word, id));
        }
    }

    public void addToQueue(String url) {
        toParse.add(url);
        visited.add(url);

        totalURLs++;
    }

    public void addPageToList(Page p) {
        parsed.add(p);
    }

    public boolean isInDomain(String url) {
        if (url.contains(domain)) {
            return true;
        }
        return false;
    }

    public boolean isValidURL(String url) {
        if (url.startsWith("http://") || url.startsWith("https://")) {
            return true;
        }
        return false;
    }
}
