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
@AllArgsConstructor  // 全参构造器: new Student(sid, name, sex, grade)
public class Student {
    private int sid;
    private String name;
    private String sex;
    private int grade;
}
