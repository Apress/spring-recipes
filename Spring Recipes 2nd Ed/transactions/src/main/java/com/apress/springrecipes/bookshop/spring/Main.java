package com.apress.springrecipes.bookshop.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
* TO RUN:
*
* You need the Spring AspectJ load time weaver, spring-instrument-3.0.0.x.jar. If you built the source tree with Maven,
* there will be a target/lib/ folder in your 'transactions' folder.
*
* Thus, you can add this to your invocation of the java command:
*
* -javaagent:target/lib/spring-instrument-3.0.0.x.jar
*
*/

public class Main {
    public static void main(String[] args) throws Throwable {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml");

        //        BookShop bookShop = (BookShop) context.getBean("bookShop");
        //        bookShop.purchase("0001", "user1");

        //        Cashier cashier = (Cashier) context.getBean("cashier");
        //        List<String> isbnList =
        //                Arrays.asList(new String[] { "0001", "0002" });
        //        cashier.checkout(isbnList, "user1");
        final BookShop bookShop = (BookShop) context.getBean("bookShop");

        Thread thread1 = new Thread(new Runnable() {
                    public void run() {
                        bookShop.checkStock("0001");
                    }
                }, "Thread 1");

        Thread thread2 = new Thread(new Runnable() {
                    public void run() {
                        try {
                            bookShop.increaseStock("0001", 5);
                        } catch (RuntimeException e) {
                        }
                    }
                }, "Thread 2");

        thread1.start();
        Thread.sleep(5000);
        thread2.start();
    }
}
