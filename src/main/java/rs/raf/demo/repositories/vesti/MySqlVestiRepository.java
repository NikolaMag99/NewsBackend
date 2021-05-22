package rs.raf.demo.repositories.vesti;

import rs.raf.demo.entities.Kategorija;
import rs.raf.demo.entities.User;
import rs.raf.demo.entities.Vesti;
import rs.raf.demo.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlVestiRepository extends MySqlAbstractRepository implements VestiRepository {

    @Override
    public Vesti addNews(Vesti vesti) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ResultSet resultSetCategory = null;

        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("select * from kategorija as k where name equals ", generatedColumns);

            preparedStatement = connection.prepareStatement("INSERT INTO vest (title, content, createdAt) VALUES(?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, vesti.getTitle());
            preparedStatement.setString(2, vesti.getContent());
            preparedStatement.setDate(3, (Date) vesti.getCreatedAt());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                vesti.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return vesti;
    }

    @Override
    public List<Vesti> allNews() {
        List<Vesti> vestiList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;

        ResultSet resultSet = null;
        ResultSet resultSetUser = null;
        ResultSet resultSetCategory = null;

        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from vest order by createdAt desc");

            while (resultSet.next()){
                Vesti vesti = new Vesti(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("content"), resultSet.getDate("createdAt"));
                vesti.setVisits(resultSet.getInt("visits"));

                preparedStatement = connection.prepareStatement("select * from users where email = ?");
                preparedStatement.setString(1, resultSet.getString("author"));
                resultSetUser = preparedStatement.executeQuery();

                while (resultSetUser.next()){
                    User user = new User(resultSetUser.getString("email"), resultSetUser.getString("first_name"), resultSetUser.getString("last_name"), resultSetUser.getString("password"));
                    user.setStatus(resultSetUser.getInt("status"));
                    user.setType(resultSetUser.getInt("type"));

                    synchronized (this) {
                        vesti.setAuthor(user);
                    }
                }

                preparedStatement = connection.prepareStatement("select * from kategorija where name = ?");
                preparedStatement.setString(1, resultSet.getString("kategorija"));
                resultSetCategory = preparedStatement.executeQuery();

                while (resultSetCategory.next()){
                     Kategorija category = new Kategorija(resultSetCategory.getString("name"), resultSetCategory.getString("description"));

                    synchronized (this) {
                        vesti.setKategorija(category);
                    }
                }

                vestiList.add(vesti);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return vestiList;
    }


//     resultSet = statement.executeQuery("select * from vest order by visits desc ");
    @Override
    public Vesti findNews(Integer id) {
        Vesti vesti = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM vest where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                Date createdAt = resultSet.getDate("createdAt");
                Integer visits = resultSet.getInt("visits");
                vesti = new Vesti(id, title, content, createdAt,visits);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return vesti;
    }

    @Override
    public void deleteNews(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM vest where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }
}
