package infrastructure;

import domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

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

    public void updateUser(User user){
        String sql = "UPDATE users SET name=?, SET email=?, SET password=? where id=?";
        jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPassword(), user.getId());
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
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), email, password);
    }


}
