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
@AllArgsConstructor  // 全参构造器: new Borrow(id, sid, bid) —— 注意顺序是 id, sid, bid
public class Borrow {
    private int id;
    private int sid;
    private int bid;
    private Student student;
    private Book book;
    public Borrow(int id, int sid, int bid) {
        this.id = id;
        this.sid = sid;
        this.bid = bid;
    }
}
