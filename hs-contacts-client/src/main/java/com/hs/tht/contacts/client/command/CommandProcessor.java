package com.hs.tht.contacts.client.command;

import com.hs.tht.contacts.client.enums.CommandType;
import com.hs.tht.contacts.service.ContactsService;

import java.util.List;
import java.util.Scanner;

/**
 * Processes the user's commands
 *
 * @author abhishek
 * @version 1.0, 5/3/17
 */
public class CommandProcessor {
    private static final String INVALID_COMMAND = "Invalid command";
    private ContactsService contactsService;

    public void setContactsService(ContactsService contactsService) {
        this.contactsService = contactsService;
    }

    public void process(Scanner sc, String command) {
        if (command == null || command.trim().length() < 1) {
            printInvalid();
            return;
        }
        CommandType commandType = null;
        try {
            commandType = CommandType.getByCode(Integer.parseInt(command.trim()));
        } catch (Exception ex) {

        }
        if (null == commandType) {
            printInvalid();
            return;
        }

        switch (commandType) {
            case ADD_CONTACT:
                System.out.print(commandType.getInputPrompt());
                String name = sc.nextLine();
                contactsService.add(name);
                break;
            case SEARCH:
                System.out.print(commandType.getInputPrompt());
                String query = sc.nextLine();
                List<String> results = contactsService.search(query);
                results.forEach(n -> System.out.println(n));
                break;
            case EXIT:
                System.out.println(commandType.getInputPrompt());
                System.exit(0);
            default:
                printInvalid();
                return;
        }
    }

    private void printInvalid() {
        System.out.println(INVALID_COMMAND);
    }
}
