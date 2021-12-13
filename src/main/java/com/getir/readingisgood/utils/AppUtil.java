package com.getir.readingisgood.utils;

import java.util.Collections;
import java.util.List;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
public class AppUtil {
    public static <T> List<T> getPage(List<T> sourceList, int page, int pageSize) {

        if(pageSize <= 0 || page <= 0) {
            return sourceList;
        }

        int fromIndex = (page - 1) * pageSize;
        if(sourceList == null || sourceList.size() <= fromIndex){
            return sourceList;
        }

        // toIndex exclusive
        return sourceList.subList(fromIndex, Math.min(fromIndex + pageSize, sourceList.size()));
    }

    public static boolean isNullOrEmpty(String str){
        if(null == str){
            return true;
        }
        return "".equals(str);
    }
}
