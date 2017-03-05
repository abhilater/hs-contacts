package com.hs.tht.contacts.service;

import com.hs.tht.contacts.service.context.ApplicationContext;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author abhishek
 * @version 1.0, 5/3/17
 */
public class ContactsServiceTest {
    ContactsService contactsService = ApplicationContext.getInstance().getContactsService();

    @Test
    public void testContactService() {
        Assert.assertNotNull(contactsService);
    }

    @Test
    public void testAdd() {
        contactsService.add(" Chris Harris");
        contactsService.add("Chris ");
        Assert.assertNotNull(contactsService.search("Ch"));
    }

    @Test
    public void testAddLargeName() {
        contactsService.add(" Chris Harris");
        contactsService.add("Chris Harris Ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
        contactsService.add("Chris");
        Assert.assertNotNull(contactsService.search("Ch"));
        Assert.assertEquals(3, contactsService.search("Ch").size());
    }
}
