import java.io.Serializable;

/**
 * CS 180 - Project 4
 * This program model result
 *
 * @author Lu, JiaWei, lu425@purdue.edu, L14.  Zhang, JunYi, zhan2539@purdue.edu, L14
 * @version 4/7/2017
 */
public class Result extends Object implements Serializable, Comparable<Result> {
    public int score;
    public static final long serialVersionUID = -938761094876384658L;
    public String url;
    public int urlID;

    public Result(String url, int urlID) {
        this.url = url;
        this.urlID = urlID;
        score = 1;
    }

    public void updateScore(int score) {
        this.score = this.score + score;
    }

    public void incrementScore() {
        score++;
    }

    public int getScore() {
        return score;
    }

    public String getURL() {
        return url;
    }

    public int getURLID() {
        return urlID;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Result) {
            if (((Result) obj).getURLID() == this.getURLID()) {
                return true;
            }
            if (((Result) obj).getURL().equals(this.getURL())) {
                return true;
            }
        }
        return false;
    }

    public int compareTo(Result candidate) {
        if (this.score > candidate.getScore()) {
            return -1;
        }
        if (this.score == candidate.getScore()) {
            return 0;
        }
        return 1;
    }
}
