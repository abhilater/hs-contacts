package com.hs.tht.contacts.service;

import java.util.List;

/**
 * Represents a service for managing contacts. It supports two operations
 * i) search
 * ii) add
 *
 * @author abhishek
 * @version 1.0, 5/3/17
 */
public interface ContactsService {

    public List<String> search(String query);

    public void add(String name);
}
