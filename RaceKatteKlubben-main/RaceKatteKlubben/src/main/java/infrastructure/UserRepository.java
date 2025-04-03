package infrastructure;

import domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }


    public User createUser(User user){

        String sql = "INSERT INTO users (name, email, password) VALUES (?,?,?)";

        jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPassword());
        return user;

    }

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

    public boolean doesEmailExists(String email) {
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
