package main;

import java.util.*;

import static java.lang.Math.abs;

public class Game {
    private List<GameCharacter> players;
    private static int MAX_X = 800;
    private static int MAX_Y = 400;

    public Game() {
       players =   new ArrayList<GameCharacter>() ;
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
        int distance = abs(player.getPositionX() - enemy.getPositionX());
        return distance;
    }

    public  int getDirectionTo(GameCharacter player, GameCharacter enemy) {
        int distance = player.getPositionX() - enemy.getPositionX();
        if (distance == 0) return 1;
        return distance/abs(distance);
    }

    public int findNearest(GameCharacter player) {
        int minDistIndex = 0;
        int distance = 0;
        int mindistance = MAX_X;
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
                        players.get(i).moveTo(players.get(i).getPositionX()- getDirectionTo(players.get(i), players.get(k))*50, players.get(k).getPositionY());
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