#!/usr/bin/env python

# This is a simple little python script which connects to the 
# ascii star wars website and prints out the display, if it finds
# any valid words in the response text, it will say them using the 
# say command. 

import sys
import getpass
import telnetlib
import re
import os
import subprocess
from time import sleep
from sets import Set 
from collections import deque 

def isValidToken(token):
    # check to see if token contains invalid characters
    if len(re.findall('[^a-zA-Z-0-9,?! .\']', token)) > 0:
        return False
    
    # token must contain at least on alpha character
    if len(re.findall('[a-zA-Z]', token)) <= 0:
        return False
    
    # check for repeating alpha character
    ch1 = ""
    allCharsMatch = True
    for ch in token:
        if len(ch1) == 0:
            ch1=ch
            continue
        if len(ch1) > 0 and ch <> ch1:
            allCharsMatch=False
     
    if allCharsMatch == True:
        if len(token) > 1:
            return False
        else:
            return len(re.findall(r'[aAiI]', token)) > 0
        
    return True  

def containsDoubleSpacedWords(word):
    if len(re.findall('\w\w', word)) > 0:
        return False
    else:
        return True  
    
def parseLine(line):
    output = ''
    tokens = line.split('  ')
    for token in tokens:
        if len(token) == 0 or token.strip() == '':
            continue
        if not isValidToken(token):
            continue;
        if containsDoubleSpacedWords(token):
            output += " " + token.replace(" ", "").strip()
        else:
            output += " " + token.strip();
    if len(output.strip()) <= 1:
        return ""
    return output
        
HOST = "towel.blinkenlights.nl"
tn = telnetlib.Telnet(HOST)


#line = line.replace('"', '\"').replace("\n", "").replace("[H", "")
#line = line.replace("[J", "").replace("str:", "")
queue = deque([""])
history = Set([""])
ch = ""
line = ""
next = ""
speechEnabled = False
firstWords = False

while True:
    line = tn.read_until("\r").encode("ascii")
    if line.find('A long time ago in a galaxy far') > 0:
        speechEnabled = True

    if line == "\n\r" or line == "\n" or line == "\r" or len(line.strip()) == 0:
        print line
        continue
    
    print line
   
    words = parseLine(line)
    words = words.replace(" o ", "")
    words = words.replace(" oo ", "")
    words = words.replace("|oo", "")
    words = words.replace("oo|", "").strip()
        
    if words not in history:
        history.add(words)
        queue.append(words)
    
    if len(queue) > 0:
        next = queue.popleft()
        if speechEnabled and len(next) > 0:
            subprocess.call(["say", next])
    
    if len(history) > 100:
        history.clear()
    
          

