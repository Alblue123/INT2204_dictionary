package application.Dictionary;

import application.backCode.Dictionary;
import application.backCode.Trie;
import application.backCode.Word;

import java.sql.*;
import java.util.*;

public class OnlineDictionary extends Dictionary {
    private static final String url = "jdbc:sqlite:src/main/resources/data/dictionary.db";
    private static Connection connection = null;

  /** connect to the database. */
  public static void connect() throws SQLException{
        System.out.println("Connecting SQLite database...");
        connection = DriverManager.getConnection(url);
        System.out.println("SQLite is connected!");
    }

    /** close the connection. */
    public static void close(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
            System.out.println("SQLite connection is disconnected!");
        }
    }

    /** close prepared statement. */
    public static void close(PreparedStatement ps) throws SQLException {
        if (ps != null) {
            ps.close();
        }
    }

    /** close result set. */
    public static void close(ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }
    }

    public void init() throws SQLException {
        connect();
        ArrayList<String> targets = getTargetWords();
        for (String word : targets) {
            Trie.add(word);
        }
    }

    @Override
    public void updateFavorite(String target, boolean favorite) {
        final String query = "UPDATE dict SET favorite = ? WHERE word = ?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, favorite ? 1 : 0);
            ps.setString(2, target);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public boolean checkFavorite(String target) {
        final String query = "SELECT favorite FROM dict WHERE word = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, target);
            rs = ps.executeQuery();
            if (rs.next()) {
                int favorite = rs.getInt(1);
                return favorite != 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Set<String> getFavoriteWords() {
        Set<String> favoriteWords = new HashSet<>();
        final String query = "SELECT word FROM dict WHERE favorite = 1";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                favoriteWords.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return favoriteWords;
    }



    @Override
    public String search(String target) {
        final String query = "SELECT html FROM dict WHERE word = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, target);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            } else {
                return "Error: Word not found";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "Error: Word not found";
    }

    @Override
    public boolean insert(String wordTarget, String wordExplain) {
        final String query = "INSERT INTO dict(word, html) VALUES(?, ?)";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, wordTarget);
            ps.setString(2, wordExplain);
            try {
                ps.executeUpdate();
            } catch (SQLIntegrityConstraintViolationException e) {
                return false;
            } finally {
                close(ps);
            }
            Trie.add(wordTarget);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void delete(String wordTarget) {
        final String query = "DELETE FROM dict WHERE word = ?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, wordTarget);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ArrayList<String> getTargetWords() {
        final String query = "SELECT * FROM dict";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            ArrayList<String> targets = new ArrayList<>();
            while (rs.next()) {
                targets.add(rs.getString(1));
            }
            return targets;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

    private ArrayList<Word> getWordsFromResultSet(PreparedStatement ps) throws SQLException {
        try {
            ResultSet rs = ps.executeQuery();
            try {
                ArrayList<Word> words = new ArrayList<>();
                while (rs.next()) {
                    words.add(new Word(rs.getString(1), rs.getString(2)));
                }
                return words;

            } finally {
                close(rs);
            }
        } finally {
            close(ps);
        }
    }

    @Override
    public ArrayList<Word> getWords() {
        final String query = "SELECT * FROM dict";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            return getWordsFromResultSet(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public boolean edit(String target, String definition) {
        final String query = "UPDATE dict SET html = ? WHERE word = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, definition);
            ps.setString(2, target);
            try {
                int up = ps.executeUpdate();
                if (up == 0) {
                    return false;
                }
            } finally {
                close(ps);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

}
