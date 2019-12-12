package DAO;

import main.Game;

import java.sql.PreparedStatement;
import javax.validation.constraints.NotNull;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
public class GameDAO implements DAO<Game, Integer>{

        /**
         * SQL queries for games table.
         * CREATE TABLE IF NOT EXISTS games (id INT, name VARCHAR(20));
         */
        enum GameSQL {
            GET("SELECT * FROM games  WHERE games.id = (?)"),
            INSERT("INSERT INTO games (id, name) VALUES ((?), (?)) RETURNING id"),
            DELETE("DELETE FROM games WHERE id = (?) RETURNING id"),
            UPDATE("UPDATE games SET name = (?) WHERE id = (?) RETURNING id");

            String QUERY;

            GameSQL(String QUERY) {
                this.QUERY = QUERY;
            }
        }

        /**
         * Connection to database
         */
        private final Connection connection;

        /**
         *  Init DB connection
         *
         * @param connection of DB
         */
        public GameDAO(@NotNull final Connection connection) {
            this.connection = connection;
        }

        /**
         * Create game in DB
         *
         * @param game for create
         * @return false if GameCharacter already exist, true if creating success
         */
        @Override
        public boolean create(Game game) {
            boolean result = false;

            try (PreparedStatement statement = connection.prepareStatement(GameSQL.INSERT.QUERY)) {
                statement.setInt(1,game.getID());
                statement.setString(2, game.getName());
                result = statement.executeQuery().next();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return result;
        }

        /**
         * Select game by id.
         *
         * @param id for select.
         * @return return valid entity if he exist. If entity does not exist return empty game with id = A.
         */
        @Override
        public Game read(Integer id) {
            Game result = new Game();
            result.setName("game");
            result.setID(-1);

            try (PreparedStatement statement = connection.prepareStatement(GameSQL.GET.QUERY)) {
                statement.setInt(1, id);
                final ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    result.setID(resultSet.getInt("id"));
                    result.setName(resultSet.getString("name"));

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return result;
        }

        /**
         * Update game  by id
         *
         * @param game new game state
         * @return true if success, false if fail
         */
        @Override
        public boolean update(Game game) {
            boolean result = false;
            try(PreparedStatement statement = connection.prepareStatement(GameSQL.UPDATE.QUERY)) {
                statement.setString(2, game.getName());
                statement.setInt(1, game.getID());
                result = statement.executeQuery().next();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return result;
        }

        /**
         * Delete game by id
         *
         * @param game for delete
         * @return true if employee was deleted, false if person not exist
         */
        @Override
        public boolean delete(Game game) {
            boolean result = false;

            try(PreparedStatement statement = connection.prepareStatement(GameSQL.DELETE.QUERY)) {
                statement.setInt(1, game.getID());
                statement.setString(2, game.getName());
                result = statement.executeQuery().next();
            }catch (SQLException e) {
                e.printStackTrace();
            }
            return result;
        }

        /**
         * Convert ResultSet into Game
         *
         * @param rs ResultSet to convert
         * @return Game object
         * @throws SQLException
         */
        @Override
        public Game resultSetToObj(ResultSet rs) throws SQLException {
            Game game = new Game();

            if(rs.next()) {
                game.setID(rs.getInt("id"));
                game.setName(rs.getString("name"));
            }

            return game;
        }
    }
