package com.hs.tht.contacts.service.model;

import java.util.*;

/**
 * Represent a Contacts data store that allows two operations i) Add contact ii) Prefix search in contacts by
 * firstName as well as lastName prefix
 * <p>
 * Assumptions: i) Search is case-sensitive
 * ii) First name and last name should be separated by a single space (if relevant)
 *
 * @author abhishek
 * @version 1.0, 5/3/17
 */
public class Contacts {

    /**
     * Uses a specific trie implementation to store all the contacts for fast prefix type search
     */
    private TrieWithSpaceSeparatedWordRecords trie = null;

    public Contacts() {
        trie = new TrieWithSpaceSeparatedWordRecords();
    }

    public void add(String name) {
        if (name == null || name.length() < 1) return;
        // add to trie after trimming the name
        name = name.trim();
        // check if it contains last name
        if (containsLastname(name)) {
            // if lastname, then add both forms i.e FName LName & LName FName to trie
            trie.insert(name, false);
            String lastNameFirstName = name.split(" ")[1] + " " + name.split(" ")[0];
            trie.insert(lastNameFirstName, true);
        } else {
            trie.insert(name, false);
        }
    }

    private boolean containsLastname(String name) {
        return name.indexOf(' ') != -1;
    }

    public List<String> search(String query) {
        List<String> results = new ArrayList<String>();
        if (null == query || query.length() < 1) return results;

        String sanitizedQuery = query.trim();
        List<String> trieResults = trie.search(sanitizedQuery);

        // filter out results that do not have searh query as substr
        for(String res : trieResults){
            if(res.contains(sanitizedQuery)) results.add(res);
        }

        // sort results based on length difference between search query and search result lengths
        results = getResultsSortedOnClosestMatch(sanitizedQuery, results);
        return results;
    }

    private List<String> getResultsSortedOnClosestMatch(String sanitizedQuery, List<String> trieResults) {
        List<String> results = new ArrayList<String>();
        SortedMap<Integer, List<String>> sortedMap = new TreeMap<Integer, List<String>>();
        int queryLength = sanitizedQuery.length();
        for (String record : trieResults) {
            int key = record.length() - queryLength;
            if (!sortedMap.containsKey(key)) {
                sortedMap.put(key, new ArrayList<String>());
            }
            sortedMap.get(key).add(record);
        }
        for (Map.Entry<Integer, List<String>> entry : sortedMap.entrySet()) {
            results.addAll(entry.getValue());
        }
        return results;
    }

    /**
     * Assumption: i) Search is case-sensitive, hence we maintain both upper
     * and lower case alphabets and treat each as different
     * <p>
     * ii) Input records in trie are a single space separated set of words
     */
    static class TrieNode {
        /**
         * Map of alphabets and TrieNode pointers
         */
        private Map<Character, TrieNode> children;

        /**
         * Flag to mark the TrieNode as leaf
         */
        private boolean isLeaf;
        /**
         * Set contains a record and its word reverse if any
         */
        private Set<Boolean> reverseSet;

        /**
         * Default constructor, initializes the root node
         */
        public TrieNode() {
            children = new HashMap<Character, TrieNode>();

            // initialize the root node with each alphabet (upper and lower case) pointing to null
            for (char i = 'a'; i <= 'z'; i++) {
                children.put(i, null);
            }
            for (char i = 'A'; i <= 'Z'; i++) {
                children.put(i, null);
            }
            // input records may have single space separated words
            children.put(' ', null);
            isLeaf = false;
            reverseSet = new HashSet<>();
        }
    }

    static class TrieWithSpaceSeparatedWordRecords {
        /**
         * Root node of the TrieWithSpaceSeparatedWordRecords
         */
        private TrieNode root = new TrieNode();

        /**
         * Inserts a new record to the TrieWithSpaceSeparatedWordRecords
         * isReverse flag is set on leaf node if record is reverse form of it's real self
         *
         * @param record
         */
        public void insert(String record, boolean isReverse) {
            if (null == record || record.length() < 1) return;
            // iterates over the height of the trie to find place to insert record
            TrieNode itr = root;
            for (int i = 0; i < record.length(); i++) {
                // check if character is present
                TrieNode nextNode = itr.children.get(record.charAt(i));
                // if not, then insert character to the map
                if (nextNode == null) {
                    nextNode = new TrieNode();
                    itr.children.put(record.charAt(i), nextNode);
                }
                // else move itr to next node
                itr = nextNode;

                // if its the last character of the word, mark the node as leaf
                if (i == record.length() - 1) {
                    itr.isLeaf = true;
                    itr.reverseSet.add(isReverse);
                }
            }
        }

        /**
         * Searches the trie for the input prefix
         *
         * @param str
         * @return
         */
        public List<String> search(String str) {
            List<String> res = new ArrayList<String>();
            TrieNode prevNode = root;

            StringBuilder prefix = new StringBuilder("");
            int len = str.length();

            for (int i = 0; i < len; i++) {
                // build the prefix one character at a time
                prefix.append(str.charAt(i));

                // find the node pointed to by the current character
                TrieNode curNode = prevNode.children.get(prefix.charAt(i));

                // if character not found return the current found set of records
                if (curNode == null) {
                    return res;
                }

                // if last character send the current node and prefix for search
                if (i == len - 1) {
                    searchUtil(curNode, prefix.toString(), res);
                }
                // update the prev node, and move to next character
                prevNode = curNode;
            }
            return res;
        }

        /**
         * Function to recursively follow all characters after the prefix till leaf
         * and generate the required result set.
         *
         * @param currNode
         * @param prefix
         * @param result
         */
        public void searchUtil(TrieNode currNode,
                               String prefix, List<String> result) {

            // if node is leaf, add the prefix as search record
            if (currNode.isLeaf) {
                for (boolean isReverse : currNode.reverseSet) {
                    if (isReverse) result.add(prefix.split(" ")[1] + " " + prefix.split(" ")[0]);
                    else result.add(prefix);
                }
            }

            // recurse for all the characters after the prefix
            char currChar;
            for (currChar = 'a'; currChar <= 'z'; currChar++) {
                recurse(currNode, prefix, result, currChar);
            }
            for (currChar = 'A'; currChar <= 'Z'; currChar++) {
                recurse(currNode, prefix, result, currChar);
            }
            currChar = ' ';
            recurse(currNode, prefix, result, currChar);
        }

        private void recurse(TrieNode curNode, String prefix, List<String> result, char currChar) {
            TrieNode nextNode = curNode.children.get(currChar);
            if (nextNode != null) {
                searchUtil(nextNode, prefix + currChar, result);
            }
        }
    }
}
