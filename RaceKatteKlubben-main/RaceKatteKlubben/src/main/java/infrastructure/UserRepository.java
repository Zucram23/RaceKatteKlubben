package infrastructure;

import domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    public UserRepository(JdbcTemplate jdbcTemplate, DataSource dataSource){
        this.jdbcTemplate=jdbcTemplate;
        this.dataSource = dataSource;
    }
    public User createUser(User user){

        String sql = "INSERT INTO users (name, email, password) VALUES (?,?,?)";

        jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPassword());
        return user;

    }

//    public User createUser(User user){
//
//        String sql = "INSERT INTO users (name, email, password) VALUES (?,?,?)";
//
//       try(Connection connection = dataSource.getConnection();
//        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//
//           String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
//           preparedStatement.setString(1, user.getName());
//           preparedStatement.setString(2, user.getEmail());
//           preparedStatement.setString(3, hashed);
//
//           int rowsInserted = preparedStatement.executeUpdate();
//           if(rowsInserted>0){
//               logger.info("User created successfully");
//               return user;
//           }
//           else logger.error("Error happened while creating user");
//
//
//
//
//       } catch(SQLException e) {
//           logger.error("Error happened while creating user");
//       }
//       return null;
//
//    }

    public int findIdByEmail(String email){
        String sql = "SELECT id from users WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, email);
    }

    public List<User> findAllUsers(){
        String sql = "SELECT * users";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public User findUserById(int id){
        String sql = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
    }

    public void updateUser(int id, User user){
        String sql = "UPDATE users SET name=?, email=?, password=? where id=?";
        jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPassword(), id);
    }

    public void deleteUser(int id){
        String sql = "DELETE from users WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public boolean emailExists(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }

    public User authenticateUser(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{email, password}, (rs, rowNum) -> {

            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));

            return user;
        });

        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
