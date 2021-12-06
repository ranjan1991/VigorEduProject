package com.vigoredu.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FontUtils {
    private static final String FONT_NAME_FRABK_REGULAR = "regular.ttf";
    private static final String FONT_NAME_FRABK_BOLD = "bold.ttf";
    private static final String FONT_NAME_FRABK_LIGHT = "light.ttf";

    public static void updateFontsForViews(Typeface face, View... views) {
        if (views == null || views.length < 1)
            return;
        if (face == null)
            face = getCustomFontNormal(views[0].getContext());
        for (View v : views)
            if (v instanceof TextView)
                ((TextView) v).setTypeface(face);
    }

    /**
     * Update font for entire screen all at once.
     *
     * @param view     - id of the root view of the layout
     * @param typeface - font typeface to set
     */
    public static void updateFonts(View view, Typeface typeface) {
        if (typeface == null)
            typeface = getCustomFontNormal(view.getContext());
        if (view instanceof TextView) {
            TextView t = (TextView) view;
            t.setTypeface(typeface);
        }

        if (view instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) view;
            for (int i = 0; i < vg.getChildCount(); i++) {
                updateFonts(vg.getChildAt(i), typeface);
            }
        }
    }
    public static void updateFontsBold(View view, Typeface typeface) {
        if (typeface == null)
            typeface = getCustomFontBold(view.getContext());
        if (view instanceof TextView) {
            TextView t = (TextView) view;
            t.setTypeface(typeface);
        }

        if (view instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) view;
            for (int i = 0; i < vg.getChildCount(); i++) {
                updateFonts(vg.getChildAt(i), typeface);
            }
        }
    }
    public static void updateFontsLight(View view, Typeface typeface) {
        if (typeface == null)
            typeface = getCustomFontLight(view.getContext());
        if (view instanceof TextView) {
            TextView t = (TextView) view;
            t.setTypeface(typeface);
        }

        if (view instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) view;
            for (int i = 0; i < vg.getChildCount(); i++) {
                updateFonts(vg.getChildAt(i), typeface);
            }
        }
    }


    public static Typeface getCustomFontNormal(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/"
                + FONT_NAME_FRABK_REGULAR);
    }

    public static Typeface getCustomFontBold(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/"
                + FONT_NAME_FRABK_BOLD);
    }
    public static Typeface getCustomFontLight(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/"
                + FONT_NAME_FRABK_LIGHT);
    }

}
