package com.mxw.leetcode.Demo;

import java.lang.reflect.Field;
import java.util.*;

public class Demo1 {

    public static String minWindow(String s, String t) {
        HashMap<Character, Integer> window = new HashMap<>();
        HashMap<Character, Integer> needs = new HashMap<>();

        for (char c : t.toCharArray()) {
            needs.put(c,needs.getOrDefault(c,0)+1);
        }

        int left=0,right=0;
        int valid=0;
        int start=0,len=Integer.MAX_VALUE;

        while (right<s.length()){
            char c = s.charAt(right);
            right++;

            if (needs.containsKey(c)){
                window.put(c, window.getOrDefault(c,0)+1);
                if (needs.get(c)==window.get(c)){
                    valid++;
                }
            }

            while (valid==needs.size()){
                if (right - left < len) {
                    start=left;
                    len=right-left;
                }
                char d = s.charAt(left);
                left++;
                if (needs.containsKey(d)){
                    if (needs.get(d)==window.get(d)){
                        valid--;
                    }
                    window.put(d,window.get(d)-1);
                }
            }
        }

        return len==Integer.MAX_VALUE?"":s.substring(start,start+len);

    }


}
