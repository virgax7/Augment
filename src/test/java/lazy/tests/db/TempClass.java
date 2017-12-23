package lazy.tests.db;

import com.augment.spring.config.RootContextConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class TempClass {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(RootContextConfiguration.class);
        DataSource ds = ctx.getBean(DataSource.class);
        JdbcTemplate jt = new JdbcTemplate(ds);
        jt.execute("create table employee (id int, name varchar)");
        jt.execute("insert into employee (id, name) values (1, 'A')");
        jt.execute("insert into employee (id, name) values (2, 'B')");

        System.out.println(jt.queryForList("select * from employee"));

        jt.execute("drop table employee");
    }
}
