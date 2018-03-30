/**
 * CS 180 - Project 4
 * This program model Page
 *
 * @author Lu, JiaWei, lu425@purdue.edu, L14.  Zhang, JunYi, zhan2539@purdue.edu, L14
 * @version 3/25/2017
 */
import java.io.Serializable;

public class Page extends Object implements Serializable, Comparable<Page>{
   
   public String url;
   private int urlID;
   public static final long serialVersionUID = -1827677255104766839L;


   Page (String url, int urlID) {
      this.url = url;
      this.urlID = urlID;
   }

   public boolean equals(Object obj) {
      if (obj instanceof Page) {
         if (((Page) obj).getURLID() == this.getURLID()) {
            return true;
         }
         if (((Page) obj).getURL().equals(this.getURL())) {
            return true;
         }
      }
      return false;
   }
   public int compareTo(Page candidate) {
      if (this.urlID < candidate.getURLID()) {
         return -1;
      }
      if (this.urlID == candidate.getURLID()) {
         return 0;
      }
      return 1;
   }
   String getURL () {
      return url;
   }

   int getURLID () {
      return urlID;
   }

}