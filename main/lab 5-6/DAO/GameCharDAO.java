package DAO;
import main.GameCharacter;
import javax.validation.constraints.NotNull;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameCharDAO implements DAO<GameCharacter, Integer> {
    /**
     * SQL queries for persons table.
     * CREATE TABLE IF NOT EXISTS players (id INT PRIMARY KEY, name VARCHAR(20), health INT, armor INT, damage INT, shooting_range INT, x INT, y INT, game_id INT, FOREIGN KEY (game_id) REFERENCES games(id));
     */
    enum GameCharSQL {
        GET("SELECT * FROM players  WHERE players.id = (?);"),
        INSERT("INSERT INTO players (id, name, health, armor, damage, shooting_range, x,y, game_id) VALUES ((?), (?), (?), (?),(?), (?),(?), (?),(?)) RETURNING id"),
        DELETE("DELETE FROM players WHERE id = (?) RETURNING id;"),
        UPDATE("UPDATE players SET name = (?) WHERE id = (?) RETURNING id");

        String QUERY;

        GameCharSQL(String QUERY) {
            this.QUERY = QUERY;
        }
    }

    /**
     * Connection to database
     */
    private final Connection connection;

    /**
     * Init DB connection
     *
     * @param connection of DB
     */
    public GameCharDAO(@NotNull final Connection connection) {
        this.connection = connection;
    }

    /**
     * Create GameCharacter in DB
     *
     *
     * @return false if Employee already exist, true if creating success
     */
    @Override
    public boolean create(GameCharacter player){
        boolean result = false;
try(PreparedStatement statement = connection.prepareStatement(GameCharSQL.INSERT.QUERY)){
            statement.setInt(1, player.getId());
            statement.setString(2, player.getName());
            statement.setInt(3, player.getHealth());
            statement.setInt(4, player.getArmor());
            statement.setInt(5, player.getDamage());
            statement.setInt(6, player.getShootingRange());
            statement.setInt(7, player.getX());
            statement.setInt(8, player.getY());
            statement.setInt(9, 2);
            result =  statement.executeQuery().next();
        } catch (SQLException e) {
        e.printStackTrace();
    }
        return result;
    }

    /**
     * Select GameCharacter by id.
     *
     * @param id for select.
     * @return return valid entity if he exist. If entity does not exist return empty Person with id = A.
     */
    @Override
    public GameCharacter read(Integer id) {
        GameCharacter result = new GameCharacter();
        result.setId(1);
        try (PreparedStatement statement = connection.prepareStatement(GameCharSQL.GET.QUERY)) {
            statement.setInt(1, id);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = GameCharacter.newBuilder()
                        .setId(Integer.valueOf(resultSet.getInt("id")))
                        .setName(resultSet.getString("name"))
                        .setHealth(resultSet.getInt("health"))
                        .setArmor(resultSet.getInt("armor"))
                        .setDamage(resultSet.getInt("damage"))
                        .setShootingRange(resultSet.getInt("shooting_range"))
                        .setPosition(resultSet.getInt("x"),resultSet.getInt("y")).build();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param
     * @return true if success, false if fail
     */
    @Override
    public boolean update(GameCharacter player) {
        boolean result = false;
        create(player);
        try (PreparedStatement statement = connection.prepareStatement(GameCharSQL.UPDATE.QUERY)) {
            statement.setInt(2, player.getId());
            statement.setString(1, player.getName()+"the Great");
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Delete GameCharacter by id
     *
     * @param player for delete
     * @return true if employee was deleted, false if person not exist
     */
    @Override
    public boolean delete(GameCharacter player) {
        boolean result = false;

        try (PreparedStatement statement = connection.prepareStatement(GameCharSQL.DELETE.QUERY)) {
            statement.setInt(1, player.getId());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Convert ResultSet into GameCharacter
     *
     * @param rs ResultSet to convert
     * @return GameCharacter object
     * @throws SQLException
     */
    @Override
    public GameCharacter resultSetToObj(ResultSet rs) throws SQLException {

        if (rs.next()) {
            GameCharacter player = GameCharacter.newBuilder().setId(rs.getInt("id"))
                    .setName(rs.getString("name")).setHealth(rs.getInt("health"))
                    .setArmor(rs.getInt("armor"))
                    .setDamage(rs.getInt("damage"))
                    .setShootingRange(rs.getInt("shooting_range"))
                    .setPosition(rs.getInt("x"), rs.getInt("y"))
                    .build();
             return player;
        }
      else return null;
    }
}