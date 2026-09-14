package book.manage.Utils;

import book.manage.mapper.BookMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.util.function.Consumer;

/**
 * 正在努力学习Java的小白
 *
 * @author Serendipity
 * @date 2026/9/11
 */
public class SqlFactory {
    private static SqlSessionFactory factory;

    static {
        try {
            factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doSqlWork(Consumer<BookMapper> consumer) {
        try (SqlSession sqlSession = factory.openSession(true)) {
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            consumer.accept(mapper);
        }
    }
}
