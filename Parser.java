import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


/**
 * CS 180 - Project 4
 * This program model parser
 *
 * @author Lu, JiaWei, lu425@purdue.edu, L14.  Zhang, JunYi, zhan2539@purdue.edu, L14
 * @version 3/25/2017
 */
public class Parser extends Object {
    public Parser () {

    }

    public Document getDocument (String url) throws ParseException {
        if (url == null) {
            throw new ParseException("getDocument() failed. String url is null.");
        }
        if (url.equals("")) {
            throw new ParseException("getDocument() failed. String url is empty.");
        }
        Document d = null;

        try {
            d = Jsoup.connect(url).get();
        } catch (Exception e) {
            throw new ParseException("getDocument() failed. Connection failed.");
        }

        if (d == null) {
            throw new ParseException("getDocument() failed: Document is null.");
        }
        return d;
    }

    public Elements getLinks(Document doc) throws ParseException {
        if (doc == null) {
            throw new ParseException("getLinks() failed. Document parameter is null.");
        }

        Elements e = doc.select("a[href]");
        return e;
    }

    public String getBody(Document doc) throws ParseException {
        if (doc == null) {
            throw new ParseException("getBody() failed. Document parameter is null.");
        }

        Element body = doc.body();

        if (body == null) {
            return null;
        }

        String b = body.text();
//        b = b.toLowerCase();
        return b;
    }

    public static void main(String[] args) {
        Parser p = new Parser();
        try {
            Elements links = p.getLinks(p.getDocument("http://www.purdue.edu"));
            System.out.println(links);
            for(Element link : links)
            {
            /* Links in HTML are structured as <a href = "link" >. We want to access the contents
             * of that 'href' piece for all of our link Element objects. Looking at the documentation
             *  for Element, we can do that like below;
             *
             *  https://jsoup.org/apidocs/org/jsoup/nodes/Element.html#attr-java.lang.String-java.lang.String-
             */
                System.out.println(link.attr("abs:href"));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
