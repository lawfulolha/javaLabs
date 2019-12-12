package main;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.*;

import static java.lang.Math.abs;

public class Game {
    private List<GameCharacter> players;

    @NotEmpty
    private Integer id;
private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public Game() {
        name = "new game";
       players =   new ArrayList<GameCharacter>() ;
    }

    public Game(int id, String name, GameCharacter[] players) {
        this.id = id;
        this.name = name;
        this.players =   new ArrayList<GameCharacter>() ;
        for(int i = 0; i< players.length; ++i){
            this.players.add(players[i]);
        }
    }

    public List<GameCharacter> getPlayers() {
        return players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game newGame = (Game) o;
        for(int i = 0; i<players.size() && i<newGame.players.size();++i)
            if(players.get(i).equals(newGame.players.get(i))) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = players != null ? players.hashCode() : 0;
        for(int i = 0; i<players.size();++i)
            result += players.get(i) != null ? players.get(i).hashCode() : 0;
        return result;
    }
    public  Game(ArrayList<GameCharacter> players) {
        new Game();
        this.players = players;
    }


    public void addPlayer(GameCharacter player) throws IllegalArgumentException {
        for (int i = 0; i < players.size(); ++i)
            if (players.get(i).getName().equals(player.getName())) {
                throw new IllegalArgumentException("PLAYER NAMED " + player.getName() + " ALREADY EXISTS");
            }

        players.add(player);
        setStartPositions();
    }

    public void deletePlayer(GameCharacter player){
        int i = 0;
        while(i<players.size()) {
            if(players.get(i).equals(player)){ players.remove(i);
                System.out.println("REMOVED PLAYER "+player.getName());
                continue;}
            ++i;
        }
    }
    public void setStartPositions() {
        for (int i = 0; i < players.size(); ++i) {
          //  players.get(i).position.setPosition((i + 1) * MAX_X / (players.size() + 1), 200);
            players.get(i).moveTo(i + 13, 200);
        }

    }

    public int getDistanceBetween(GameCharacter player, GameCharacter enemy) {
        int distance = abs(player.getX() - enemy.getX());
        return distance;
    }

    public  int getDirectionTo(GameCharacter player, GameCharacter enemy) {
        int distance = player.getX() - enemy.getX();
        if (distance == 0) return 1;
        return distance/abs(distance);
    }

    public int findNearest(GameCharacter player) {
        int minDistIndex = 0;
        int distance = 0;
        int mindistance = 600;
        for (int i = 0; i <players.size(); ++i) {

            if (!player.getName().equals(players.get(i).getName())) {

                distance = getDistanceBetween(player, players.get(i));

                if (distance < mindistance) {
                    mindistance = distance;
                    minDistIndex = i;
                }
            }
        }
        return minDistIndex;
    }

    public void makeAttack(GameCharacter player, GameCharacter enemy) {
        if (getDistanceBetween(player, enemy) <= player.getShootingRange()) {
            enemy.getInjury(player.getDamage());
            if(enemy.getHealth()<=0) deletePlayer(enemy);
        }
    }

    public GameCharacter getWinner() {
       // while(players.size()>1) {
            for (int i = 0; i< players.size() && players.size()>=1;++i) {
                if(players.size()==1) continue;
                Random random = new Random();
                if (random.nextBoolean()){
                    int k =  findNearest(players.get(i));
                    while(getDistanceBetween(players.get(i),players.get(k)) >= players.get(i).getShootingRange() && random.nextBoolean())
                        players.get(i).moveTo(players.get(i).getX()- getDirectionTo(players.get(i), players.get(k))*50, players.get(k).getY());
                   // makeAttack(players.get(i), players.get(k));
                    makeAttack(players.get(i), players.get(k));
                }
            }
      //  }
        if(players.size() == 0) {
        System.out.println("EMPTY LIST");return null;}
        return players.get(0);
    }

}