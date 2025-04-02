package infrastructure;

import domain.Cat;
import domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CatRepository {
    private final JdbcTemplate jdbcTemplate;

    public CatRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public Cat createCat(Cat cat) {

        String sql = "INSERT INTO cats (name, age, race_id, owner_id) VALUES (?,?,?,?)";

        jdbcTemplate.update(sql, cat.getName(), cat.getAge(), cat.getRace(), cat.getOwner());
        return cat;
    }
    public List<Cat> findAllCats(){
        String sql = "SELECT * cats";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Cat.class));
    }
    public void updateCat(Cat cat){
        String sql = "UPDATE cats SET name=?, SET age=?, SET race_id=?, SET owner_id=? where id=?";
        jdbcTemplate.update(sql, cat.getName(), cat.getAge(), cat.getRace(), cat.getOwner(), cat.getId());
    }
    public void deleteCat(int id){
        String sql = "DELETE from cats WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

}
