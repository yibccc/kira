package com.kira.common;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Kira
 * @create 2023-02-10 22:10
 */
public class tool {
    public static List<String> extractNumbersFromString(String str){
        String regex = "(\\d+)";
        List<String> nums = new ArrayList<>();
        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(str);
        while (m.find()){
            nums.add(m.group());
        }

        return nums;
    }
}
