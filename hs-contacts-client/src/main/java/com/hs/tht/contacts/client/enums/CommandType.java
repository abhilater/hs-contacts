package com.hs.tht.contacts.client.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author abhishek
 * @version 1.0, 5/3/17
 */
public enum CommandType {
    ADD_CONTACT(1, "Add contact", "Enter name: "), SEARCH(2, "Search", "Enter name: "), EXIT(3, "Exit", "Happy Searching");

    private int code;
    private String text;
    private String inputPrompt;

    private static Map<Integer, CommandType> codeCommandMap = new HashMap<Integer, CommandType>();

    static {
        for (CommandType commandType : CommandType.values()) {
            codeCommandMap.put(commandType.getCode(), commandType);
        }
    }

    CommandType(int code, String text, String inputPrompt) {
        this.code = code;
        this.text = text;
        this.inputPrompt = inputPrompt;
    }

    public static CommandType getByCode(int code) {
        return codeCommandMap.get(code);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getInputPrompt() {
        return inputPrompt;
    }

    public void setInputPrompt(String inputPrompt) {
        this.inputPrompt = inputPrompt;
    }
}
