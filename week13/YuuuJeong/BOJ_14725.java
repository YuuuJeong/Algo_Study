import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14725 {

    static class Chamber {
        Map<String, Chamber> nextRooms = new TreeMap<>();  
    }

    static Chamber nestRoot = new Chamber();

    public static void addPath(String[] foodPath) {
        Chamber current = nestRoot;
        for (String food : foodPath) {
            current.nextRooms.putIfAbsent(food, new Chamber());
            current = current.nextRooms.get(food);
        }
    }

    public static void displayNest(Chamber chamber, int depth) {
        for (Map.Entry<String, Chamber> entry : chamber.nextRooms.entrySet()) {
            for (int i = 0; i < depth; i++) {
                System.out.print("--");
            }
            System.out.println(entry.getKey());
            displayNest(entry.getValue(), depth + 1);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numPaths = Integer.parseInt(reader.readLine());

        for (int i = 0; i < numPaths; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int numRooms = Integer.parseInt(tokenizer.nextToken());
            String[] foodPath = new String[numRooms];
            for (int j = 0; j < numRooms; j++) {
                foodPath[j] = tokenizer.nextToken();
            }
            addPath(foodPath);
        }
        displayNest(nestRoot, 0);
    }
}

