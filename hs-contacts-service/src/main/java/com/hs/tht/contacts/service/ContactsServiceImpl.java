package com.hs.tht.contacts.service;

import com.hs.tht.contacts.service.model.Contacts;

import java.util.List;

/**
 * @author abhishek
 * @version 1.0, 5/3/17
 */
public class ContactsServiceImpl implements ContactsService {

    private static ContactsService contactsService;
    /**
     * Contacts datastore for effecient search by prefix
     */
    private Contacts contacts;

    private ContactsServiceImpl() {
        contacts = new Contacts();
    }

    public static ContactsService getInstance() {
        if (null == contactsService) {
            synchronized (ContactsServiceImpl.class) {
                if (null == contactsService) {
                    contactsService = new ContactsServiceImpl();
                }
            }
        }
        return contactsService;
    }

    public List<String> search(String query) {
        return contacts.search(query);
    }

    public void add(String name) {
        contacts.add(name);
    }
}
