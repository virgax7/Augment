package lazy.tests.db;

import com.augment.dao.LoginDao;
import com.augment.spring.config.RootContextConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class JdbcTest {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(RootContextConfiguration.class);
        DataSource ds = ctx.getBean(DataSource.class);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

        testloginDao(ctx);
        testDayTable(jdbcTemplate);
    }

    private static void testloginDao(AnnotationConfigApplicationContext ctx) {
        System.out.println("now running testloginDao------------------------------------");
        LoginDao loginDao = ctx.getBean(LoginDao.class);
        System.out.println(loginDao.getUser("virgax7", "passwd").size() == 1);
    }

    public static void testDayTable(final JdbcTemplate jdbcTemplate) throws Exception {
        System.out.println("now running testDayTable------------------------------------");
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
        jdbcTemplate.execute("insert into day values ('virga8')");
        System.out.println(jdbcTemplate.queryForList("select * from day where username='virga8'").size() == 1);
        jdbcTemplate.execute("delete from users where username='virga8'");
        System.out.println(jdbcTemplate.queryForList("select * from day where username='virga8'").size() == 0);
    }
}
