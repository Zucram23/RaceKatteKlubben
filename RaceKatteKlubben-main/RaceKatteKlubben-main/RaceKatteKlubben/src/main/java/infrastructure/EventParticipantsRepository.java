package infrastructure;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class EventParticipantsRepository {

    private final JdbcTemplate jdbcTemplate;

    public EventParticipantsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void enterEvent(int userId, int eventId, List<Integer> catIds) {
        String sql = "INSERT INTO event_user_cats (event_id, user_id, cat_id) VALUES (?, ?, ?)";

        if (catIds == null || catIds.isEmpty()) {
            jdbcTemplate.update(sql, eventId, userId, null);
        } else {
            jdbcTemplate.batchUpdate(sql, catIds, catIds.size(), (ps, catId) -> {
                ps.setInt(1, eventId);
                ps.setInt(2, userId);
                ps.setInt(3, catId);
            });
        }
    }

    public List<String> getEventParticipantsWithCats(int eventId) {
        String sql = """
            SELECT u.name AS user_name, GROUP_CONCAT(c.name ORDER BY c.name SEPARATOR ', ') AS cats_entered
            FROM event_user_cats euc
            JOIN users u ON euc.user_id = u.id
            LEFT JOIN cats c ON euc.cat_id = c.id
            WHERE euc.event_id = ?
            GROUP BY u.id
        """;
        List<Map<String, Object>> participants = jdbcTemplate.queryForList(sql, eventId);

        List<String> formattedResults = new ArrayList<>();

        // Format the results into the desired format
        for (Map<String, Object> row : participants) {
            String userName = (String) row.get("user_name");
            String catsEntered = (String) row.get("cats_entered");

            if (catsEntered == null || catsEntered.isEmpty()) {
                formattedResults.add(userName + " - Cats entered: None");
            } else {
                formattedResults.add(userName + " - Cats entered: " + catsEntered);
            }

        }
        return formattedResults;

    }

    public void removeCatFromEvent(int eventId,int userId, int catId) {

        String sql = "DELETE FROM event_user_cats WHERE event_id = ? AND user_id = ? AND cat_id = ?";

        jdbcTemplate.update(sql, eventId, userId, catId);

    }

    public void deleteAllEventParticipants(int eventId){
        String sql = "DELETE FROM event_user_cats WHERE event_id = ?";
        jdbcTemplate.update(sql, eventId);
    }
}

