package com.cookandroid.kmap_test.util;

import android.content.res.Resources;

public class DisplayUtil {
    public static int dpToPx(int dp) {
        int res = (int) (dp * Resources.getSystem().getDisplayMetrics().density);
        return res;
    }
}