package book.manage;

import book.manage.Utils.SqlFactory;
import book.manage.entity.Book;
import book.manage.entity.Borrow;
import book.manage.entity.Student;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.apache.ibatis.io.Resources;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.LogManager;

/**
 * 正在努力学习Java的小白
 *
 * @author Serendipity
 * @date 2026/9/11
 */
@Log
public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        LogManager manager = LogManager.getLogManager();
        manager.readConfiguration(Resources.getResourceAsStream("logging.properties"));
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=================图书管理系统信息控制台===============");
            System.out.println("1.录入学生信息");
            System.out.println("2.录入书籍信息");
            System.out.println("3.学生添加借阅信息");
            System.out.println("4.查询学生信息");
            System.out.println("5.查询图书信息");
            System.out.println("6.查询借阅信息");
            System.out.print("输入想要执行的操作,按其他任意键退出:");
            int input;
            try {
                input = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("请输入数字");
                scanner.nextLine();
                continue;
            }
            switch (input) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    addBook(scanner);
                    break;
                case 3:
                    addBorrow(scanner);
                    break;
                case 4:
                    showStudent(scanner);
                    break;
                case 5:
                    showBook(scanner);
                    break;
                case 6:
                    showBorrow(scanner);
                    break;
                default:
                    System.out.println("退出程序");
                    return;
            }
        }

    }

    private static void addStudent(Scanner scanner) {
        System.out.print("请输入学生名字: ");
        String name = scanner.nextLine();
        System.out.print("请输入学生性别(男/女): ");
        String sex = scanner.nextLine();
        System.out.print("请输入学生年级: ");
        int grade = Integer.parseInt(scanner.nextLine());
        Student student = new Student(0, name, sex, grade);  // sid 是自增列, 传 0 占位
        SqlFactory.doSqlWork(mapper -> {
            int result = mapper.insertStudent(student);
            System.out.println(result > 0 ? "学生信息录入成功" : "学生信息录入失败,请重试");
            log.info("新添加了一条学生信息" + student);
        });
    }

    private static void addBook(Scanner scanner) {
        System.out.print("请输入书籍名字: ");
        String name = scanner.nextLine();
        System.out.print("请输书籍作者: ");
        String author = scanner.nextLine();
        System.out.print("请输入书籍价格: ");
        int price = Integer.parseInt(scanner.nextLine());
        System.out.println("请输入书籍简介(不超过150字)");
        String description = scanner.nextLine();
        Book book = new Book(0, name, author, price, description);  // bid 是自增列, 传 0 占位
        SqlFactory.doSqlWork(mapper -> {
            int result = mapper.insertBook(book);
            System.out.println(result > 0 ? "书籍信息录入成功" : "书籍信息录入失败,请重试");
            log.info("新添加了一条书籍信息" + book);
        });
    }

    private static void addBorrow(Scanner scanner) {
        System.out.print("请输入借阅id: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("请输入书籍号: ");
        int bid = Integer.parseInt(scanner.nextLine());
        System.out.print("请输学号: ");
        int sid = Integer.parseInt(scanner.nextLine());
        Borrow borrow = new Borrow(id, sid, bid);  // 顺序是 id, sid, bid (原来写成了 id, bid, sid)
        SqlFactory.doSqlWork(mapper -> {
            int result = mapper.insertBorrow(borrow);
            System.out.println(result > 0 ? "书籍信息录入成功" : "书籍信息录入失败,请重试");
            log.info("新添加了一条书籍信息" + borrow);
        });
    }

    private static void showBook(Scanner scanner) {
        System.out.println("图书信息列表: ");
        SqlFactory.doSqlWork(mapper -> {
            List<Book> books = mapper.getBook();
            System.out.println("书籍信息查询成功");
            for (Book book : books) {
                System.out.println("书籍名称: "+book.getTitle()+" 书籍作者: "+book.getAuthor()+" 书籍简介: "+book.getDescription()+" 书籍价格: "+book.getPrice());
            }
            log.info("成功查询了一次图书信息");
        });
    }

    private static void showStudent(Scanner scanner) {
        System.out.println("学生信息列表: ");
        SqlFactory.doSqlWork(mapper -> {
            List<Student> students = mapper.getStudent();
            System.out.println("学生信息查询成功");
            for (Student student : students) {
                System.out.println("学生姓名: "+student.getName()+" 学生性别: "+student.getSex()+" 学生年级 "+student.getGrade());
            }
            log.info("成功查询了一次学生信息");
        });
    }

    private static void showBorrow(Scanner scanner) {
        System.out.print("展示所有借阅信息");
        SqlFactory.doSqlWork(mapper -> {
            List<Borrow> borrows = mapper.selectBorrow();
            for (Borrow borrow : borrows) {
                System.out.println(borrow.getStudent().getName()+"→"+borrow.getBook());
            }
            log.info("成功查询了一次借阅信息");
        });
    }
}
