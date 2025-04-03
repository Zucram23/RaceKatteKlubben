package infrastructure;

import domain.Event;
import domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventRepository {

    private final JdbcTemplate jdbcTemplate;

    public EventRepository(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    public Event createEvent(Event event) {
        String sql = "INSERT INTO events (eventName, description, location, admin_id, date, price) VALUES (?, ?, ?, ?, ?, ?)";
    jdbcTemplate.update(sql, event.getEventName(), event.getDescription(), event.getLocation(), event.getAdmin_id(), event.getDate(), event.getPrice());
        return event;
    }

    public void updateEvent(Event event) {
        String sql = "UPDATE events SET eventName=?, description=?, location=?, date=?, price=? WHERE id = ?";
        jdbcTemplate.update(sql, event.getEventName(), event.getDescription(), event.getLocation(), event.getDate(), event.getPrice(), event.getId());
    }

    public List<Event> getAllEvents() {
        String sql = "SELECT * FROM events";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Event event = new Event();
            event.setId(rs.getInt("id"));
            event.setEventName(rs.getString("eventName"));
            event.setDescription(rs.getString("description"));
            event.setLocation(rs.getString("location"));
            event.setAdmin_id(rs.getInt("admin_id"));
            event.setDate(rs.getDate("date"));  // This maps the SQL date to Java Date
            event.setPrice(rs.getDouble("price"));
            return event;
        });
    }

    public void deleteEvent(int id) {
    String sql = "DELETE FROM events WHERE id = ?";
    jdbcTemplate.update(sql, id);
    }

    public List<Event> getEventsByUser(User user) {
        String sql = "SELECT * FROM events WHERE admin_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Event.class), user.getId());
    }

    public Event getEventById(int id) {
        String sql = "SELECT * FROM events WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Event.class), id);
    }
















    public User findAdminById(int adminId) {
        String sql = "SELECT * FROM users WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), adminId);
    }


}
