package org.example;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Player implements Serializable {
    private String name;
    private int chips;
    private int wins;
    private int draws;
    private int losses;
    private static final String playerDataFile = "resources/players.json";

    Player(String name) {
        this.name = name;
        chips = 100;
        wins = 0;
        draws = 0;
        losses = 0;
    }
    Player(String name, int chips, int wins, int draws, int losses) {
        this.name = name;
        this.chips = chips;
        this.wins = wins;
        this.draws = draws;
        this.losses = losses;
    }
    public String getName() {
        return name;
    }
    public int getChips() {
        return chips;
    }
    public void setChips(int chips) {
        this.chips = chips;
    }
    public int getWins() {
        return wins;
    }
    public void setWins(int wins) {
        this.wins = wins;
    }
    public int getDraws() {
        return draws;
    }
    public void setDraws(int draws) {
        this.draws = draws;
    }
    public int getLosses() {
        return losses;
    }
    public void setLosses(int losses) {
        this.losses = losses;
    }

    public static void serializePlayers(Map<String, Player> players) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(playerDataFile)){
            gson.toJson(players,writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static HashMap<String, Player> deSerializePlayers() {
        File file = new File(playerDataFile);
        if (!file.exists()) {
            return new HashMap<>();
        }
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(file)){
            Type userMapType = new TypeToken<HashMap<String, Player>>(){}.getType();
            HashMap<String, Player> userMap = gson.fromJson(reader,userMapType);
            return userMap != null ? userMap : new HashMap<>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return name + "chips: " + chips + " wins: " + wins + " draws: " + draws + " losses: " + losses;
    }
}
