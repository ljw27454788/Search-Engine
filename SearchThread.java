/**
 * CS 180 - Project 4
 * This program model search thread
 *
 * @author Lu, JiaWei, lu425@purdue.edu, L14.  Zhang, JunYi, zhan2539@purdue.edu, L14
 * @version 4/7/2017
 */
public class SearchThread extends Object implements Runnable{
    public int start;
    public int finish;
    public String[] terms;

    public SearchThread(int start, int finish, String[] terms) {
        this.start = start;
        this.finish = finish;
        this.terms = terms;
    }

    public void run() {
        String url = null;
        for (String term : terms) {
            Word word = findTerm(term);

            if (word != null) {

                for (int id : word.getList()) {
                    boolean isincre = true;
                    for (int i = 0; i < Search.resultSet.size(); i++) {
                        if (id == Search.resultSet.get(i).getURLID()) {
                            Search.resultSet.get(i).incrementScore();

                            isincre = false;
                        }
                    }
                    if (isincre) {
                        for (int j = 0 ; j < Search.pageList.size(); j++) {
                            if (Search.pageList.get(j).getURLID() == id) {
                                url = Search.pageList.get(j).getURL();
                            }
                        }
                        Search.resultSet.add(new Result(url, id));
                    }
                }
            }
        }
    }

    public Word findTerm(String term) {
//        if (term == null) {
//            return null;
//        }
        Word w = null;
        for (int i = start; i < finish; i++) {
            if (Search.wordList.get(i).getWord().equals(term)) {
                w = Search.wordList.get(i);
            }
        }
        return w;
    }


}
