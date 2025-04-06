package infrastructure;

import domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements CrudRepository<User> {

    private final JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate, DataSource dataSource){this.jdbcTemplate=jdbcTemplate;}

    @Override
    public User save(User user){

        String sql = "INSERT INTO users (name, email, password) VALUES (?,?,?)";

        jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPassword());
        return user;
    }

    @Override
    public List<User> findAll(){
        String sql = "SELECT * users";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void update(User user){
        String sql = "UPDATE users SET name=?, email=?, password=? where id=?";
        jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPassword(), user.getId());
    }

    @Override
    public void delete(int id){
        String sql = "DELETE from users WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public boolean emailExists(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }

    public Optional<User> authenticateUser(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{email, password}, (rs, rowNum) -> {

            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));

            return user;
        }));

        }catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public int findIdByEmail(String email){
        String sql = "SELECT id from users WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, email);
    }

    public User findUserById(int id){
        String sql = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
    }
}
