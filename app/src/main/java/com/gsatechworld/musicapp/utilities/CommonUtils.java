/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.gsatechworld.musicapp.utilities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CommonUtils {


    public static final String EMPTY_DASH_STRING = " - ";


    private static final String TAG = "CommonUtils";


    public CommonUtils() {


        // This utility class is not publicly instantiable
    }

    @SuppressLint("all")
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static boolean isEmailValid(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static String validateEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            String msg = "Enter Email";
            return msg;
        } else if (!TextUtils.isEmpty(email) && !isEmailValid(email)) {
            String msg = "Enter valid Email";
            return msg;
        } else {
            return null;
        }
    }

    public static String validatePassword(String pass) {
        if (TextUtils.isEmpty(pass)) {
            String msg = "Enter Password";
            return msg;
        } else if (pass.length() < 6) {
            String msg = "Password should have minimum 6 character";
            return msg;
        } else {
            return null;
        }
    }

    public static boolean isValidMobile(String phone) {
        return Patterns.PHONE.matcher(phone).matches();
    }

    public static String validatePhoneNumber(String phNo) {
        if (TextUtils.isEmpty(phNo)) {
            String msg = "Enter Mobile number";
            return msg;
        } else if (!isValidMobile(phNo)) {
            String msg = "Enter Valid Mobile number";
            return msg;
        } else if (phNo.length() != 10) {
            String msg = "Enter Valid Mobile number";
            return msg;
        } else {
            return null;
        }

    }

    public static String getAddressFromLocation(final double latitude, final double longitude, Context context) {
        Geocoder geocoder;
        String address = "";
        List<Address> addresses;
        if (context != null) {
            try {
                geocoder = new Geocoder(Objects.requireNonNull(context), Locale.getDefault());
                addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                if (addresses != null && addresses.size() > 0) {
                    address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return address;
    }


    public static void showerrormsg(String title, String message, Context context) {
        android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(context);

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        // Setting Negative "NO" Button

        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to invoke NO event

                dialog.cancel();
            }
        });

        alertDialog.show();
    }


    public static String basecode_convert(String mImagePath) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap myBitmap = BitmapFactory.decodeFile(mImagePath, options);
        final int maxSize = 800;
        int outWidth;
        int outHeight;
        int inWidth = myBitmap.getWidth();
        int inHeight = myBitmap.getHeight();
        if (inWidth > inHeight) {
            outWidth = maxSize;
            outHeight = (inHeight * maxSize) / inWidth;
        } else {
            outHeight = maxSize;
            outWidth = (inWidth * maxSize) / inHeight;
        }

        Bitmap resizedBitmap = Bitmap.createScaledBitmap(myBitmap, outWidth, outHeight, false);
        String imageBase64;
        imageBase64 = getEncoded64ImageStringFromBitmap(resizedBitmap);

        return imageBase64;
    }

    public static String getEncoded64ImageStringFromBitmap(Bitmap bitmap) {


        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        byte[] byteFormat = stream.toByteArray();
        // get the base 64 string
        String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);

        return imgString;

    }

    @BindingAdapter("imageResource")
    public static void setImageResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }



    @BindingAdapter(value = {"imageUrl1", "placeholder"}, requireAll = false)
    public static void setImageUrl(ImageView imageView, String url,
                                   Drawable placeHolder) {
        if (url.equals("")) {
            imageView.setImageDrawable(placeHolder);
        } else {
            Glide.with(imageView.getContext())
                    .load(url)
                    .into(imageView);

        }
    }

     public static void setFocus(EditText view, String error) {
        view.requestFocus();
        view.setError(error);
    }

}
