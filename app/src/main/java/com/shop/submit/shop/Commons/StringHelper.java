package com.shop.submit.shop.Commons;

import android.content.Context;
import android.content.res.Resources;

public class StringHelper {

    public static String getByIdStr(Context context, String name) {
        Resources res = context.getResources();
        return res.getString(res.getIdentifier(name, "string", context.getPackageName()));
    }

    public static String getById(Context context, int id) {
        return context.getString(id);
    }

}
