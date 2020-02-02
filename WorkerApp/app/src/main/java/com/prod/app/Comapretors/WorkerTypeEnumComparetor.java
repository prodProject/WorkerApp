package com.prod.app.Comapretors;

import java.util.Comparator;

public class WorkerTypeEnumComparetor implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }
}
