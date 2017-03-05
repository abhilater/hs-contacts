package com.hs.tht.contacts.service.context;

import com.hs.tht.contacts.service.ContactsService;
import com.hs.tht.contacts.service.ContactsServiceImpl;

/**
 * @author abhishek
 * @version 1.0, 5/3/17
 */
public class ApplicationContext {
    /* singleton instance for ApplicationContext */
    private static ApplicationContext applicationContext = null;

    public static ApplicationContext getInstance() {
        if (applicationContext == null) {
            synchronized (ApplicationContext.class) {
                if (applicationContext == null) {
                    applicationContext = new ApplicationContext();
                    applicationContext.init();
                }
            }
        }
        return applicationContext;
    }

    private ApplicationContext() {
    }

    private ContactsService contactsService;

    /**
     * Initialize the application context
     * i.e initialize all singleton and inject dependencies
     */
    public void init() {
        contactsService = ContactsServiceImpl.getInstance();
    }

    public ContactsService getContactsService() {
        return contactsService;
    }
}
