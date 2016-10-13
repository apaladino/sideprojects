package com.andyp.algorithms.strings;

/*
 * Given a string you need to print longest possible substring that has exactly M unique characters. 
 * If there are more than one substring of longest possible length, then print any one of them.

Examples: 
 * "aabbcc", k = 1
Max substring can be any one from {"aa" , "bb" , "cc"}.

"aabbcc", k = 2
Max substring can be any one from {"aabb" , "bbcc"}.

"aabbcc", k = 3
There are substrings with exactly 3 unique characters
{"aabbcc" , "abbcc" , "aabbc" , "abbc" }
Max is "aabbcc" with length 6.

"aaabbb", k = 3
There are only two unique characters, thus show error message. 
 */
public class LongestSubstringWithKUniqueExample {

	public static void main(String args[]){
		LongestSubstringWithKUniqueExample ex = new LongestSubstringWithKUniqueExample();
		
		String str = "aabbcc";
		int k = 1;
		
		String maxSubStr = ex.findMaxSubStrWithKUnique(str, k);
		System.out.println(maxSubStr);
	}

	public String findMaxSubStrWithKUnique(String str, int k) {
		
		int [] alphaCount = new int[26];
		
		int numUnique = 0;
		int n = str.length();
		
		// count unique chars
		for(int i=0; i < n; i++){
			
			char ch = str.charAt(i);
			if(alphaCount[ch - 'a'] == 0)
				numUnique++;
			
			alphaCount[ch - 'a'] ++;
		}
		
		if(numUnique < k)
			return "Not enough unique characters in input string";
		
		int curStart=0,
			curEnd=0;
		
		int maxWindowStart = 0,
			maxWindowSize = 0;
		
		alphaCount = new int[26]; // reset alphacount
		
		// add first character to alphaCount
		alphaCount[str.charAt(0) - 'a']++;
		
		for(int i=1; i < n; i++){
			char ch = str.charAt(i);
			
			// add ch to current window
			alphaCount[ch-'a']++;
			curEnd++;
			
			// if there are more than k unique chars from current window, the slide window right
			while(!isValid(alphaCount, k)){
				alphaCount[curStart]--;
				curStart++;
			}
			
			// update maxWindowStart and size if needed
			if(curEnd-curStart + 1 > maxWindowSize){
				maxWindowSize = curEnd - curStart + 1;
				maxWindowStart = curStart;
			}
			
		}
		
		return str.substring(maxWindowStart, maxWindowSize);
	}

	private boolean isValid(int[] alphaCount, int k) {

		int numUnique = 0;
		
		for(int i=0; i < alphaCount.length; i++){
			if(alphaCount[i] > 0)
				numUnique++;
		}
		
		return (k >= numUnique);
	}
}
