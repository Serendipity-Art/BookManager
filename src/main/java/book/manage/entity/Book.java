package book.manage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 正在努力学习Java的小白
 *
 * @author Serendipity
 * @date 2026/9/11
 */
@Data
@NoArgsConstructor   // MyBatis 反射创建对象必须有【无参构造器】
@AllArgsConstructor  // 全参构造器: new Book(bid, title, author, price, description)
public class Book {
    private int bid;
    private String title;
    private String author;
    private int price;
    private String description;
}
