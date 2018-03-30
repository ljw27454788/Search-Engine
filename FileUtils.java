import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CS 180 - Project 4
 * This program model file utils
 *
 * @author Lu, JiaWei, lu425@purdue.edu, L14.  Zhang, JunYi, zhan2539@purdue.edu, L14
 * @version 4/7/2017
 */
public class FileUtils extends Object {

    public FileUtils() {
    }

    public boolean saveWordTable(List<Word> wordTable, String filePath) {
        if (filePath == null || wordTable == null) {
            return false;
        }
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {

            fos = new FileOutputStream(filePath);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(wordTable);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                fos.close();
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public boolean savePageTable(List<Page> pageTable, String filePath) {
        if (filePath == null || pageTable == null) {
            return false;
        }
        FileOutputStream fos;
        ObjectOutputStream oos;
        try {
            fos = new FileOutputStream(filePath);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(pageTable);

            fos.close();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Word> getWordList(String filePath) {
        if (filePath == null) {
            return null;
        }
        List<Word> w = new ArrayList<Word>();

        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream(filePath);
            ois = new ObjectInputStream(fis);

            w = (List<Word>) ois.readObject();
            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        return w;
    }

    public List<Page> getPageList(String filePath) {
        if (filePath == null) {
            return null;
        }
        List<Page> p = new ArrayList<Page>();

        FileInputStream fis;
        ObjectInputStream ois;

        try {
            fis = new FileInputStream(filePath);
            ois = new ObjectInputStream(fis);

            p = (List<Page>) ois.readObject();
            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        return p;
    }


}
