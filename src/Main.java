import exception.IncorrectArgumentException;
import exception.TaskNotFoundException;
import service.TaskService;
import tasks.DailyTask;
import tasks.MonthlyTask;
import tasks.OneTimeTask;
import tasks.Task;
import tasks.WeeklyTask;
import tasks.YearlyTask;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Scanner;

public class Main {
    private static TaskService taskService;

    public static void main(String[] args) {
        taskService = new TaskService();
        try (Scanner scanner = new Scanner(System.in)) {
            boolean shouldContinue = true;
            while (shouldContinue) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner);
                            break;
                        case 2:
                            removeTask(scanner);
                            break;
                        case 3:
                            getTasksForDay(scanner);
                            break;
                        case 0:
                            shouldContinue = false;
                            break;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }

    private static void inputTask(Scanner scanner) {
        // title input
        scanner.nextLine();
        System.out.println("Введите название задачи:");
        String title = scanner.nextLine();
        // description input
        System.out.println("Введите описание задачи:");
        String description = scanner.nextLine();
        // isWork input
        Task.Type type;
        System.out.println("Эта задача рабочая?");
        switch (scanner.nextLine()) {
            case "1":
            case "да":
            case "д":
            case "y":
            case "yes":
            case "Y":
            case "YES":
                type = Task.Type.WORK;
                break;
            default:
                type = Task.Type.PERSONAL;
        }
        // date input
        LocalDateTime dateTime = null;
        System.out.println("Введите дату и время задачи (01.01.1970 00:00:00):");
        boolean shouldEnterAgain = true;
        while (shouldEnterAgain) {
            try {
                dateTime = LocalDateTime.parse(scanner.nextLine(), Task.DATE_TIME_FORMATTER);
                shouldEnterAgain = false;
            } catch (DateTimeParseException e) {
                System.out.println("Wrong format, enter again");
            }
        }
        // creation of task
        Task task;
        System.out.println("Повторяемость задания:");
        System.out.println("\t • 0 – не повторяется (default)");
        System.out.println("\t • 1 – ежедневно");
        System.out.println("\t • 2 – еженедельно");
        System.out.println("\t • 3 – ежемесячно");
        System.out.println("\t • 4 – ежегодно");
        try {
            switch (scanner.next()) {
                case "1":
                    task = new DailyTask(title, type, dateTime, description);
                    break;
                case "2":
                    task = new WeeklyTask(title, type, dateTime, description);
                    break;
                case "3":
                    task = new MonthlyTask(title, type, dateTime, description);
                    break;
                case "4":
                    task = new YearlyTask(title, type, dateTime, description);
                    break;
                default:
                    task = new OneTimeTask(title, type, dateTime, description);
            }
            taskService.add(task);
        } catch (IncorrectArgumentException e) {
            System.out.println(e);
        }

    }

    public static void getTasksForDay(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Введите дату (01.01.1970):");
        LocalDate date = null;
        boolean shouldEnterAgain = true;
        while (shouldEnterAgain) {
            try {
                date = LocalDate.parse(scanner.nextLine(), Task.DATE_FORMATTER);
                shouldEnterAgain = false;
            } catch (DateTimeParseException e) {
                System.out.println("Wrong format, enter again");
            }
        }
        printTasks(taskService.getAllByDate(date));
    }

    public static void removeTask(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Введите ID задачи:");
        int id = 0;
        boolean shouldEnterAgain = true;
        while (shouldEnterAgain) {
            try {
                id = Integer.parseInt(scanner.nextLine());
                shouldEnterAgain = false;
            } catch (NumberFormatException e) {
                System.out.println("Wrong format, enter again");
            }
        }
        try {
            Task removedTask = taskService.remove(id);
            System.out.println("Задача успешно удалена!");
            System.out.println(removedTask);
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printMenu() {
        System.out.println(
                "1. Добавить задачу\n" +
                        "2. Удалить задачу\n" +
                        "3. Получить задачу на указанный день\n" +
                        "0. Выход"
        );
    }

    private static void printTasks(Collection<Task> tasks) {
        if (tasks.isEmpty()) System.out.println("Список задач пуст!");
        for (Task task : tasks) {
            System.out.println(task);
            System.out.println();
        }
    }
}