# Code-Impledge

**Compound Words Finder**

**Overview**

This Java application reads words from input files - Input_01.txt and Input_02.txt, finds the longest and the second-longest compounded words and calculates the time taken for the process.

**Features**

Uses Trie (Prefix Tree) for word storage in efficient manner.
Uses dynamic programming - Memoization to speed up processing.
Handles huge datasets efficiently.

**Input Format**

One lowercase word per line sorted alphabetically

**How It Works**

Read words and store in Trie
Search for compounded words using recursion with memoization.
Find the longest & second longest compounded words.
Measures execution time.

**Usage**

Compile: javac CompoundedWordsSolution.java

Run: java CompoundedWordsSolution

Assure that Input_01.txt and Input_02.txt both exist in the same directory.
