'''
Frequency Counting of Words / Top N words in a document.

Given N terms, your task is to find the k most frequent terms from given N terms.

Created on Jan 19, 2014

@author: Mahindra
'''
import collections

words = {};
OrderedWords = {};

size = int(raw_input());
for i in range(0, size):
    ip = raw_input();
    if ip in words:
        words[ip] += 1;
    else:
        words[ip] = 1;
 
topN = int(raw_input());
count = 0;
OrderedWords = collections.OrderedDict(sorted(words.items(),key = lambda t : t[1],  reverse = True));
while(count < topN):
    maxCount = list(OrderedWords.values())[count];
    repeat = [k for k,v in OrderedWords.items() if v == maxCount];  #stores words with the same value
    for w in sorted(repeat):
    	if(count < topN):
	        print w;
	        count += 1;
        else:
        	exit();