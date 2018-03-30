import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * CS 180 - Project 4
 * This program model word
 *
 * @author Lu, JiaWei, lu425@purdue.edu, L14.  Zhang, JunYi, zhan2539@purdue.edu, L14
 * @version 3/25/2017
 */
public class Word extends Object implements Serializable{
   
   private String word;
   ArrayList<Integer> associatedPages;
   private List<Integer> postings;
   public static final long serialVersionUID = -3696191086353573895L;

   public Word(String word, int urlID) {
      this.word = word;
      postings = new ArrayList<>();
      this.postings.add(urlID);
   }

   public void addURLID(int urlID) {
      postings.add(urlID);
   }

   public String getWord() {
      return word;
   }

   public List<Integer> getList() {
      return postings;
   }

   public boolean equals(Object obj) {
      if (obj instanceof Word) {
         if (((Word) obj).getWord().equals(this.getWord())) {
            return true;
         }
      }
      return false;
   }
}