package Main.ScoreBoard;

import java.io.*;
import java.text.*;
import java.util.*;

public class ScoreBoard {

    /**
     * Open a txt file and write in it the score of the player after the game
     * and all the scores after writing
     * 
     * @param name  : The name of the player
     * @param score : The score of the player
     */
    public void writeAndOrder(String name, int score) {
        String path = "./scoreboard.txt";

        File myfile = new File(path);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            if (myfile.length() == 0) {

                writer.write(name + " with a score of " + score
                        + ". Date: "
                        + formatter.format(date) + ".");
            } else {
                writer.write(
                        "\n" + name + " with a score of " + score
                                + ". Date: "
                                + formatter.format(date) + ".");
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Map<String, Object>> scoreRecords = readScoreRecords(path);

        scoreRecords.sort(Comparator.comparingInt(record -> {
            Object scoreObject = record.get("score");
            if (scoreObject instanceof Number) {
                return -((Number) scoreObject).intValue();
            } else if (scoreObject instanceof String) {
                return -Integer.parseInt((String) scoreObject);
            } else {
                throw new IllegalArgumentException("Type de score non pris en charge : " + scoreObject.getClass());
            }
        }));

        writeScoreRecords(path, scoreRecords);
    }

    /**
     * Open the file where there is all the records and parse it in order
     * to check the score of the player in another function
     * 
     * @param path : The path of the file
     * @return (List<Map<String, Object>>) : return a map which contains all the
     *         datas kept
     */
    private static List<Map<String, Object>> readScoreRecords(String path) {
        List<Map<String, Object>> records = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                String name = parts[0];
                int score = Integer.parseInt(parts[5].substring(0, parts[5].length() - 1));
                String dateString = parts[7] + " " + parts[8];
                Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(dateString);

                Map<String, Object> record = new HashMap<>();
                record.put("name", name);
                record.put("score", score);
                record.put("date", date);

                records.add(record);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return records;
    }

    /**
     * Open the file where there is all the records and take all the records
     * parsed. Rewrite in the file the correct datas after ordering
     * 
     * @param path    : The path of the file
     * @param records : The records parsed
     */
    private static void writeScoreRecords(String path, List<Map<String, Object>> records) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            int size = records.size();
            for (int i = 0; i < size; i++) {
                Map<String, Object> record = records.get(i);
                String line = record.get("name") + " with a score of " + record.get("score") +
                        ". Date: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(record.get("date"));

                writer.write(line);

                if (i < size - 1) {
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Open a txt file and show in the window the 3 best records,
     * the name of the player and it's score
     * 
     * @param path   : The path of the file
     * @param window : The window of the game
     */
    public ArrayList<String> ShowRecords(String path) {
        int cpt = 0;
        ArrayList<String> scoreboard = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = reader.readLine()) != null && cpt <= 2) {
                String[] parts = line.split(" ");

                scoreboard.add(parts[0] + " : " + parts[5].substring(0, parts[5].length() - 1));

                cpt++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return scoreboard;
    }
}
