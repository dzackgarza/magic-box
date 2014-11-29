import java.lang.String;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;


public class Solution {

	public static void main(String args[] ) {

        Scanner inputScanner = new Scanner(System.in);

        Box box = new Box(inputScanner);

        //System.out.println("M = " + box.rows + ", N = " + box.cols);


        //box.print();
        //box.flipColumn(0);
        //System.out.println("~~~~~~~~~~~~~~~~~~~");
        //box.print();
       // System.out.println("Number of wishes: " + box.getNumberOfWishes());
        String cols = box.determineMax();

        for (int i = 0; i < cols.length(); i++) {
            box.flipColumn(Character.getNumericValue(cols.charAt(i)));
        }

        System.out.println(box.getNumberOfWishes());
    }
}

class Box {

    public int rows, cols;
    public char[][] contents;

    public Box(Scanner s) {
        this.rows = s.nextInt();
        this.cols = s.nextInt();

        this.contents = new char[rows][cols];

        for (int i = 0; i < rows; i++) {

            String thisRow = s.next();

            for (int j = 0; j < cols; j++) {
                this.contents[i][j] = thisRow.charAt(j);
            }
        }
    }

    public Box print() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                System.out.print(this.contents[i][j]);
            }
            System.out.print('\n');
        }

        return this;
    }

    public int getNumberOfWishes() {

        int numWishes = 0;

        for (int i = 0; i < this.rows; i++) {

            boolean rowElementsMatch = true;
            for (int j = 1; j < this.cols; j++) {

                if (this.contents[i][j] != this.contents[i][j-1]) {
                    rowElementsMatch = false;
                }
            }
            if (rowElementsMatch) numWishes++;
        }

        return numWishes;
    }

    /*  Flip all "F"s to "P"s and vice versa in a single column.
     *  Note: Columns are counted from zero.
     */
    public Box flipColumn(int column) {

        for (int i = 0; i < this.rows; i++) {

            if (this.contents[i][column] == 'P') {
                this.contents[i][column] = 'T';
            } else {
                this.contents[i][column] = 'P';
            }
        }

        return this;
    }

    public String determineMax() {

        HashMap<String, Integer> possibleColumnChoices = new HashMap<String, Integer>();

        for (int i = 0; i < this.rows; i++) {

            ArrayList<String> PLocations = new ArrayList<String>();
            ArrayList<String> TLocations = new ArrayList<String>();
            for (int j = 0; j < this.cols; j++) {

                if (this.contents[i][j] == 'P') {
                    PLocations.add(String.valueOf(j));
                } else {
                    TLocations.add(String.valueOf(j));
                }
            }

            String cols1 = "";
            for (String P : PLocations) {
                cols1 += P;
            }

            String cols2 = "";
            for (String P : PLocations) {
                cols2 += P;
            }

            if (possibleColumnChoices.containsKey(cols1)) {
                int c = possibleColumnChoices.get(cols1);
                c++;
                possibleColumnChoices.put(cols1, c);
            } else {
                possibleColumnChoices.put(cols1, 1);
            }

            if (possibleColumnChoices.containsKey(cols2)) {
                int c = possibleColumnChoices.get(cols2);
                c++;
                possibleColumnChoices.put(cols2, c);
            } else {
                possibleColumnChoices.put(cols2, 1);
            }

        }

        String maxKey = "";
        int max = 0;
        for (String s : possibleColumnChoices.keySet()) {
            if (possibleColumnChoices.get(s) > max) {
                maxKey = s;
                max = possibleColumnChoices.get(s);
            }
        }

        //System.out.println("Max key: " + maxKey);
        return maxKey;
    }
}

