package com.hs.tht.contacts.client.command;

import com.hs.tht.contacts.client.enums.CommandType;
import com.hs.tht.contacts.service.ContactsService;
import com.hs.tht.contacts.service.context.ApplicationContext;

import java.util.Scanner;

/**
 * Processes the application startup
 *
 * @author abhishek
 * @version 1.0, 5/3/17
 */
public class AppProcessor {

    private ContactsService contactsService = null;
    private CommandProcessor commandProcessor = null;

    public void startApp() {
        ApplicationContext applicationContext = ApplicationContext.getInstance();
        if (null == applicationContext) throw new RuntimeException("Failed to get application context");
        contactsService = applicationContext.getContactsService();
        commandProcessor = new CommandProcessor();
        commandProcessor.setContactsService(contactsService);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            printOptions();
            String command = scanner.nextLine();
            commandProcessor.process(scanner, command);
        }
    }

    private void printOptions() {
        for (CommandType commandType : CommandType.values()) {
            System.out.print(String.format("%d) %s ", commandType.getCode(), commandType.getText()));
        }
        System.out.println();
    }
}
