package infrastructure;

import domain.Cat;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CatRepositoryImpl implements CrudRepository<Cat> {
    private final JdbcTemplate jdbcTemplate;

    public CatRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Cat save(Cat cat) {

        String sql = "INSERT INTO cats (name, age, race_id, owner_id) VALUES (?,?,?,?)";

        jdbcTemplate.update(sql, cat.getName(), cat.getAge(), cat.getRace(), cat.getOwner());
        return cat;
    }

    @Override
    public List<Cat> findAll(){
        String sql = "SELECT * cats";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Cat.class));
    }

    @Override
    public void update(Cat cat){
        String sql = "UPDATE cats SET name=?, SET age=?, SET race_id=?, SET owner_id=? where id=?";
        jdbcTemplate.update(sql, cat.getName(), cat.getAge(), cat.getRace(), cat.getOwner(), cat.getId());
    }

    @Override
    public void delete(int id){
        String sql = "DELETE from cats WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<Cat> findCatsByOwner(int id){
        String sql = "SELECT * cats where owner_id=?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Cat.class), id);
    }
    public Cat getCatById(int id){
        String sql = "SELECT * FROM cats WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Cat.class), id);
    }

}
