package com.example.bombcodecracker;

import java.sql.ResultSet;
import java.util.*;

class Puzzle {
    private List<List<String>> puzzle;
    private List<String> values;
    public static String[] DIFFICULTIES = {"easy", "medium", "hard", "treasure"};

    Puzzle(String difficulty) {
        prepareBluePrint(difficulty);
    }

    @SuppressWarnings("SqlResolve")
    private void prepareBluePrint(String difficulty) {
        puzzle = new ArrayList<>();

        try {
            DatabaseHelper.openConnection();
            ResultSet rs = DatabaseHelper.STATEMENT.executeQuery("" +
                    "SELECT \n" +
                    "    puzzle_rows.id_puzzle, puzzle_rows.values, clues.clue\n" +
                    "FROM\n" +
                    "    puzzle_rows,\n" +
                    "    clues\n" +
                    "WHERE\n" +
                    "    clues.id_clue = puzzle_rows.id_clue\n" +
                    "        AND puzzle_rows.id_puzzle = (SELECT \n" + difficulty + "_puzzles.id_puzzle\n" +
                    "        FROM\n" + difficulty + "_puzzles\n" +
                    "        LIMIT 1)\n" +
                    "ORDER BY RAND()"
            );

            while (rs.next()) {
                StringTokenizer stringTokenizer = new StringTokenizer(rs.getString(2));
                List<String> row = new ArrayList<>();
                while (stringTokenizer.hasMoreTokens())
                    row.add(stringTokenizer.nextToken());
                row.add(rs.getString(3));
                puzzle.add(row);
            }

            DatabaseHelper.closeConnection();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public void build() {
        prepareValues();
        for (List<String> row : puzzle) {
            for (int i = 0; i < row.size() - 1; i++) {
                switch (row.get(i)) {
                    case "X":
                        row.set(i, values.get(0));
                        break;
                    case "Y":
                        row.set(i, values.get(1));
                        break;
                    case "Z":
                        row.set(i, values.get(2));
                        break;
                    case "A":
                        row.set(i, values.get(3));
                        break;
                    case "B":
                        row.set(i, values.get(4));
                        break;
                    case "C":
                        row.set(i, values.get(5));
                        break;
                    case "D":
                        row.set(i, values.get(6));
                        break;
                    case "E":
                        row.set(i, values.get(7));
                        break;
                    case "F":
                        row.set(i, values.get(8));
                        break;
                    case "G":
                        row.set(i, values.get(9));
                        break;
                    default:
                        row.set(i, null);
                }
            }
        }
    }

    private void prepareValues() {
        values = new ArrayList<>(Arrays.asList("", "", "", "", "", "", "", "", "", ""));

        // Prepare 10 unique random values between 0 and 9:
        Random random = new Random();
        for (int i = 0; i < values.size(); i++) {
            String newValue;
            do {
                newValue = random.nextInt(10) + "";
            } while (values.contains(newValue));
            values.set(i, newValue);
        }
    }

    @Override
    public String toString() {
        for (List<String> row : puzzle) {
            System.out.println(Arrays.toString(row.toArray()));
        }
        return "";
    }
}