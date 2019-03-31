package com.andyp.algorithms.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by andy on 4/30/17.
 */
public class TrieExample {

    private final TrieNode root;

    public TrieExample(){
        root = new TrieNode();
    }

    public static void main(String [] a){
        new TrieExample().run();
    }

    public void run(){
        String[] inputs = { "abc", "abjk", "bcl", "bm", "ghkp"};

        for(String input : inputs){
            root.insert(input);
        }

        boolean search = root.search("abc");
        System.out.println("abc = " + search);
        System.out.println("bm = " + root.search("bm"));

    }

    class TrieNode{

        private Map<Character, TrieNode> children;
        private boolean endOfWord;

        public TrieNode(){
            children = new HashMap<>();
            endOfWord = false;
        }

        public void insert(String input) {
            if(input == null || input.isEmpty()) return;

            TrieNode current = root;

            for(int i=0; i < input.length(); i++){
                char ch = input.charAt(i);

                TrieNode node = current.children.get(ch);
                if(node == null){
                    node = new TrieNode();
                    current.children.put(ch, node);
                }

                current = node;
            }

            // we're at the end of the input string so set endOfWord flag to true
            current.endOfWord = true;
        }

        public boolean search(String word) {
            if(word == null || word.isEmpty()) return false;

            TrieNode current = root;
            for(int i=0; i < word.length(); i++){
                char ch = word.charAt(i);
                TrieNode node = current.children.get(ch);
                if(node == null)
                    return false;

                current = node;
            }

            return current.endOfWord;
        }
    }
}
