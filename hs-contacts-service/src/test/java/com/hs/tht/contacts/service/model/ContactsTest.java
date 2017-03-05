package com.hs.tht.contacts.service.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author abhishek
 * @version 1.0, 5/3/17
 */
public class ContactsTest {

    Contacts contacts = new Contacts();

    @Test
    public void testInsert() {
        contacts.add(" Chris Harris");
        contacts.add("Chris ");
        Assert.assertNotNull(contacts.search("Ch"));
    }

    @Test
    public void testInsertEmpty() {
        contacts.add(" ");
        Assert.assertNotNull(contacts.search("Ch"));
        Assert.assertEquals(0, contacts.search("Ch").size());
    }

    @Test
    public void testSearch() {
        contacts.add(" Chris Harris");
        contacts.add("Chris ");
        contacts.add("Chloe");
        contacts.add("DeeDee");
        List<String> results = contacts.search("Ch");
        Assert.assertNotNull(results);
        Assert.assertEquals(3, results.size());

        results = contacts.search("D");
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
    }

    @Test
    public void testSearchEmpty() {
        contacts.add("DeeDee");
        List<String> results = contacts.search("Ch");
        Assert.assertNotNull(results);
        Assert.assertEquals(0, results.size());
    }

    @Test
    public void testSearchOrder() {
        contacts.add(" Chris Harris");
        contacts.add("Chris ");
        contacts.add("Chloe");
        List<String> results = contacts.search("Chris");
        Assert.assertNotNull(results);
        Assert.assertEquals("Chris", results.get(0));
    }
}