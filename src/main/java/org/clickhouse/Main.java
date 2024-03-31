package org.clickhouse;

import org.clickhouse.services.ClickHouseService;
import org.clickhouse.utils.DataSourceConnection;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        DataSource dataSource = DataSourceConnection.createDataSource();

        ClickHouseService service = new ClickHouseService( dataSource);
        Scanner input = new Scanner(System.in);

        System.out.println("Вас приветствует консольный клиент для работы с базой данных ClickHouse!");
        printMainMenu();
        int choice;

        do {
            choice = input.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.println("Введите название нового спорта: ");
                    String name = input.next();

                    if (service.createSport(name) == 1)
                        System.out.printf("Создан новый вид спорта: %s\n", name);
                    else
                        System.out.println("Что-то пошло не так");
                }
                case 2 -> {
                    System.out.println("Введите название вида спорта, который хотите удалить: ");
                    String name = input.next();

                    if (service.deleteSport(name))
                        System.out.println("Удаление успешно");
                    else System.out.println("Вида спорта не существует");
                }
                case 3 -> {
                    System.out.println("Введите название команды, которую хотите отредактировать: ");
                    String name = input.next();

                    System.out.println("Введите новое название вида спорта: ");
                    String newName = input.next();

                    System.out.println("Введите количество участников: ");
                    int count = input.nextInt();

                    if (service.editTeam(name, newName, count) != 0)
                        System.out.println("Вид спорта отредактирован. Новое название: " + newName);
                    else System.out.println("Редактирование завершилось неудачей");
                }
                case 4 -> {
                    System.out.println("Введите количество участников: ");
                    int count = input.nextInt();

                    service.selectByCount(count);
                }
                case 5 -> {
                    int additionalChoice;
                    do {
                        printAdditionalMenu();
                        additionalChoice = input.nextInt();

                        switch (additionalChoice) {
                            case 1 -> service.selectWithReplacing();
                            case 2 -> service.selectWithCollapsing();
                        }
                    } while (additionalChoice != 0);
                }
                default -> {
                    continue;
                }
            }
            printMainMenu();
        } while (choice != 0);

        System.exit(0);
    }

    private static void printMainMenu() {
        System.out.println("Меню: ");
        System.out.println("1 - Создать запись");
        System.out.println("2 - Удалить запись");
        System.out.println("3 - Редактировать запись");
        System.out.println("4 - Показать все команды, где количество участников больше заданного числа");
        System.out.println("5 - Другие движки");
        System.out.println("0 - Выход");
    }

    private static void printAdditionalMenu() {
        System.out.println("Пункт 5: Другие движки");
        System.out.println("1 - ReplacingMergeTree");
        System.out.println("2 - CollapsingMergeTree");
        System.out.println("0 - Вернуться в главное меню");
    }
}