package lazy.tests.db;

import com.augment.dao.GoalsDao;
import com.augment.dao.LoginDao;
import com.augment.spring.config.RootContextConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertTrue;

class JdbcTest {
    private static final String USER = "mango";
    private static final String PASSWD = "apple";
    private static final String USER_QUOTED = "'mango'";
    private static final String PASSWD_QUOTED = "'apple'";

    public static void main(String[] args) throws Exception {
        final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(RootContextConfiguration.class);
        final DataSource ds = ctx.getBean(DataSource.class);
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

        try {
            jdbcTemplate.execute("insert into users values (" + USER_QUOTED + ", " + PASSWD_QUOTED + ")");

            testloginDao(jdbcTemplate, ctx);
            testDayTable(jdbcTemplate);
            testGoalsDao(jdbcTemplate, ctx);

        } finally {
            jdbcTemplate.execute("delete from users where username=" + USER_QUOTED);
            assertTrue(ctx.getBean(LoginDao.class).getUser(USER, PASSWD).size() == 0);
        }
    }

    private static void testGoalsDao(final JdbcTemplate jdbcTemplate, final AnnotationConfigApplicationContext ctx) {
        System.out.println("now running test testGoalsDao");
        final String TITLE = "some goal";
        final String TITLE_QUOTED = "'some goal'";
        final GoalsDao goalsDao = ctx.getBean(GoalsDao.class);
        try {
            jdbcTemplate.execute("insert into goal (username, title, start_date, target_date) " +
                    "values (" + USER_QUOTED + ", " + TITLE_QUOTED + ", '" + LocalDate.now() + "', '" + LocalDate.of(2018, Month.JANUARY, 1) + "')");
            try {
                jdbcTemplate.execute("insert into goal (username, title, start_date, target_date) " +
                        "values (" + USER_QUOTED + ", " + TITLE_QUOTED + ", '" + LocalDate.now() + "', '" + LocalDate.of(2018, Month.JANUARY, 1) + "')");
            } catch (final Exception e) {}
            assertTrue(goalsDao.getGoal(USER, TITLE).size() == 1);
            System.out.println(goalsDao.getGoal(USER, TITLE));
            System.out.println("passed...");
        } finally {
            jdbcTemplate.execute("delete from goal where username=" + USER_QUOTED + " and title=" + TITLE_QUOTED);
            assertTrue(goalsDao.getGoal(USER,  TITLE).size() == 0);
        }
    }

    private static void testloginDao(final JdbcTemplate jdbcTemplate, final AnnotationConfigApplicationContext ctx) {
        System.out.println("now running testloginDao------------------------------------");
        final LoginDao loginDao = ctx.getBean(LoginDao.class);
        assertTrue(loginDao.getUser(USER, PASSWD).size() == 1);
        System.out.println("passed...");
    }

    private static void testDayTable(final JdbcTemplate jdbcTemplate) throws Exception {
        System.out.println("now running testDayTable------------------------------------");
        try {
            jdbcTemplate.execute("insert into users values ('virga8', 'pass')");
            boolean flag = false;
            try {
                jdbcTemplate.execute("insert into day values ('nonexist')");
                flag = true;
            } catch (Exception e) {
            }
            if (flag) {
                throw new Exception("inserting into day a username that doesn't exist in users should fail....");
            }
            jdbcTemplate.execute("insert into day (username) values ('virga8')");
            assertTrue(jdbcTemplate.queryForList("select * from day where username='virga8'").size() == 1);
        } finally {
            jdbcTemplate.execute("delete from users where username='virga8'");
            System.out.println("passed...");
            assertTrue(jdbcTemplate.queryForList("select * from day where username='virga8'").size() == 0);
        }
    }
}
