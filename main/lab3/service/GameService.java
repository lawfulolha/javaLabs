package service;

import main.Game;
import main.GameCharacter;

import java.util.*;
import java.util.stream.Collectors;

public class GameService {
    private Game game;

    public GameService(){
        game = new Game();
        }
    public GameService(Game g){
        game = g;
    }

    public long countPlayersWDamageMoreThan(int damage) {
        return game.getPlayers().stream()
                .filter(c -> c.getDamage()>damage)
                .count();
    }
    public SortedSet<GameCharacter> sortPlayersByName() {
        SortedSet<GameCharacter> sortedPlayers = new TreeSet<>((a, b) -> b.getName().compareTo(a.getName()));
        sortedPlayers.addAll(game.getPlayers());
        return sortedPlayers;
    }

    public SortedSet<GameCharacter> sortPlayersByDamage() {
        SortedSet<GameCharacter> sortedPlayers = new TreeSet<>(Comparator.comparing(GameCharacter::getDamage));
        sortedPlayers.addAll(game.getPlayers());
        return sortedPlayers;
    }
    public boolean ifExistsByName(String name) {
        Game result = new Game();
        game.getPlayers().stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .forEach(result::addPlayer);

        return !result.getPlayers().isEmpty();
    }
    private List<GameCharacter> getListOfPlayersWithoutArmor() {
        return game.getPlayers()
                .stream()
                .filter(c -> (c.getArmor() == 0))
                .collect(Collectors.toList());
    }

    public boolean removePlayersWithoutArmor() {
        return game.getPlayers().removeAll(getListOfPlayersWithoutArmor());
    }
}
