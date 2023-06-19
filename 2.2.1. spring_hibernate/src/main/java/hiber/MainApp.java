package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new Car("AUDI", 10));
        userService.add(new Car("BMW", 11));
        userService.add(new Car("Lada", 12));
        userService.add(new Car("Lada", 33));


        List<Car> cars = userService.listCars();

        System.out.println("\n=== Добавление пользователей с машинами в БД");
        userService.add(new User("User1", "Lastname1", "user1@mail.ru", cars.get(0)));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", cars.get(1)));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", cars.get(2)));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", cars.get(3)));
        System.out.println("\n=== ok");

        System.out.println("\n=== Select пользователя с машиной");
        User user = userService.findOwner("AUDI", 10);
        System.out.println(user);
        System.out.println("\n=== ок");

        context.close();
    }
}