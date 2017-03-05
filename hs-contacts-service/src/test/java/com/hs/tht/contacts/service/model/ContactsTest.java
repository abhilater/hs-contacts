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
    public void testSearchManyChildren() {
        String pref = "Ch";
        for (char i = 'a'; i <= 'z'; i++) {
            contacts.add(pref + i);
        }
        for (char i = 'A'; i <= 'Z'; i++) {
            contacts.add(pref + i);
        }

        List<String> results = contacts.search(pref);
        Assert.assertNotNull(results);
        Assert.assertEquals(52, results.size());

        results = contacts.search(pref+'Z');
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
    }

    @Test
    public void testSearchDeepPrefix() {
        String pref = "a";
        String word = "";
        for (char i = 'a'; i <= 'z'; i++) {
            word += i;
        }
        for (char i = 'A'; i <= 'Z'; i++) {
            word += i;
        }

        contacts.add(word);

        List<String> results = contacts.search(pref);
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertEquals(word, results.get(0));
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
