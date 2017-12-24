package lazy.tests.db;

import com.augment.dao.LoginDao;
import com.augment.spring.config.RootContextConfiguration;
import org.apache.commons.logging.Log;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class JdbcTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(RootContextConfiguration.class);
        DataSource ds = ctx.getBean(DataSource.class);
        JdbcTemplate jt = new JdbcTemplate(ds);
        jt.execute("create table employee (id int, name varchar)");
        jt.execute("insert into employee (id, name) values (1, 'A')");
        jt.execute("insert into employee (id, name) values (2, 'B')");

        System.out.println(jt.queryForList("select * from employee where id=? and name=?", new Object[] {1, "A"}));

        jt.execute("drop table employee");

        LoginDao loginDao = ctx.getBean(LoginDao.class);
        System.out.println(loginDao.validate("virgax7", "passwd"));
    }
}
