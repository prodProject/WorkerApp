package com.prod.app.CommonCode;

import java.util.ArrayList;
import java.util.List;

public class CommonHelper {
    public static <T> List<T> convertArrayToList(T array[]) {
        List<T> list = new ArrayList<>();
        for (T t : array) {
            list.add(t);
        }
        return list;
    }
}
