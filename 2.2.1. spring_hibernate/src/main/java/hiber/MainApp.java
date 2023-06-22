package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService= context.getBean(CarService.class);

        carService.addCar(new Car("AUDI", 10));
        carService.addCar(new Car("BMW", 11));
        carService.addCar(new Car("Lada", 12));
        carService.addCar(new Car("Lada", 33));


        List<Car> cars = carService.listCars();

        System.out.println("\n=== Добавление пользователей с машинами в БД");
        userService.addUser(new User("User1", "Lastname1", "user1@mail.ru", cars.get(0)));
        userService.addUser(new User("User2", "Lastname2", "user2@mail.ru", cars.get(1)));
        userService.addUser(new User("User3", "Lastname3", "user3@mail.ru", cars.get(2)));
        userService.addUser(new User("User4", "Lastname4", "user4@mail.ru", cars.get(3)));
        System.out.println("\n=== ok");

        System.out.println("\n=== Select пользователя с машиной");
        User user = userService.getUserByCar("AUDI", 10);
        System.out.println(user);
        System.out.println("\n=== ок");

        context.close();
    }
}