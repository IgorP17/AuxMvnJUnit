package org.wiremock;

import java.util.List;

public class CompareItems {
    public static boolean compareItems(List<CompareItem> compareItems) {
        boolean result = true;
        for (CompareItem item : compareItems) {
            System.out.printf("%s: compare method = %s, expected = %s, actual = %s\n",
                    item.getMessage(), item.getCompareEnum(), item.getExpectedResult(), item.getActualResult());
            switch (item.getCompareEnum()) {
                case EQUALS:
                    if (item.getExpectedResult().equalsIgnoreCase(item.getActualResult())) {
                        System.out.println("result = TRUE");
                    } else {
                        System.out.println("result = FALSE");
                        result = false;
                    }
                    break;
                case CONTAINS:
                    if (item.getActualResult().contains(item.getExpectedResult())) {
                        System.out.println("result = TRUE");
                    } else {
                        System.out.println("result = FALSE");
                        result = false;
                    }
                    break;
            }
        }
        return result;
    }

}
