package service;

import exception.ConvertException;
import main.GameCharacter;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TextConverter {
    private final Integer FIELDS_COUNT =  7;
    private final String FIELDS_SEPARATOR = "---";

    private Object[] getGameCharFields(GameCharacter player) {
        return new Object[] {
                player.getName(), player.getHealth(),player.getArmor(),  player.getDamage(),
                player.getShootingRange(),player.getX(),player.getY()
        };
    }

    public String serializeToString(GameCharacter player) throws ConvertException {
        try {
            Object[] fields = getGameCharFields(player);
            List<String> stringList = Arrays.stream(fields)
                    .map(Object::toString)
                    .collect(Collectors.toList());
            return String.join(FIELDS_SEPARATOR, stringList);
        } catch (Exception ex) {
            throw new ConvertException(ex.getMessage());
        }
    }

    public GameCharacter deserializeFromString(String string) throws ConvertException {
        try {
            String[] fields = string.split(FIELDS_SEPARATOR, FIELDS_COUNT);
            return GameCharacter.newBuilder().setName(fields[0])
                    .setHealth(Integer.parseInt(fields[1]))
                    .setArmor(Integer.parseInt(fields[2]))
                    .setDamage(Integer.parseInt(fields[3]))
                    .setShootingRange(Integer.parseInt(fields[4]))
                    .setPosition(Integer.parseInt(fields[5]),Integer.parseInt(fields[6]))
                    .build();

        } catch (Exception ex) {
            throw new ConvertException(ex.getMessage());
        }
    }

    public void toFile(GameCharacter player, File file) {
        try(FileWriter writer = new FileWriter(file)) {
            writer.write(serializeToString(player));
            writer.flush();
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    public GameCharacter fromFile(File file) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            int character;
            StringBuilder sb = new StringBuilder();
            while ((character = br.read()) != -1) {
                char ch = (char) character;
                if (character == '\n') break;
                sb.append(ch);
            }
            return  deserializeFromString(sb.toString());

        } catch (IOException | ConvertException ex) {
            ex.getMessage();
        }
        return null;
    }

}