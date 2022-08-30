package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import javax.persistence.NoResultException;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Sergey", "Petrov", "Pettrov@hogwarts.com");
      User user2 = new User("Anna", "Sidorova", "Sidorova@hogwarts.com");
      User user3 = new User("Roman", "Kovolev", "Kowolev@hogwarts.com");
      User user4 = new User("Liza", "Ivanova", "Ivanova@hogwarts.com");

      Car car1 = new Car("BMW", 5);
      Car car2 = new Car("AUDI", 6);
      Car car3 = new Car("OPEL", 72);
      Car car4 = new Car("FORD", 2);

      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));
      userService.add(user4.setCar(car4).setUser(user4));

      for (User user : userService.listUsers()) {
         System.out.println("1. Пользователь с машиной");
         System.out.println(user + " " + user.getCar());
         System.out.println(" ");
      }

      System.out.println("2. пользователь, владеющей машиной по ее модели и серии");
      System.out.println(userService.getUserByCar("BMW", 5));
      System.out.println(" ");

      try {
         System.out.println("3. Такого пользователя не найдено");
         User notFoundUser = userService.getUserByCar("WV", 9);
      } catch (NoResultException e) {

      }

      context.close();
   }
}