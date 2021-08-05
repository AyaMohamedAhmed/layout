package com.example.list_view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class dayAdapter extends ArrayAdapter<day> {
    private day[] _days;
    private Context _context;

    public dayAdapter(Context context, day[] days) {
        super(context, R.layout.custom_row, R.id.text_name, days);
        _context = context;
        _days = days;
    }

    /*public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {

        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while (( halfHeight / inSampleSize ) > reqHeight && ( halfWidth / inSampleSize ) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }*/

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        ViewHolder viewHolder;
        //TextView txtname = view.findViewById(R.id.text_name);
        //TextView txtdescription = view.findViewById(R.id.text_description);
        //ImageView imageView = view.findViewById(R.id.imageView);

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_row, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.getTxtname().setText(_days[position].getDay());
        viewHolder.getTxtdescription().setText(_days[position].getDescription());
        viewHolder.getImageView().setImageResource(_days[position].getImage());
       // imageView.setImageBitmap(decodeSampledBitmapFromResource(_context.getResources(), _days[position].getImage(), 170, 170));


        return view;


    }

    private class ViewHolder {
        View view;
        TextView txtname;
        TextView txtdescription;
        ImageView imageView;

        public ViewHolder(View convertView) {
            view = convertView;
        }

        public TextView getTxtname() {
            if (txtname == null) {
                txtname = view.findViewById(R.id.text_name);
            }
            return txtname;
        }

        public TextView getTxtdescription() {
            if (txtdescription == null) {
                txtdescription = view.findViewById(R.id.text_description);
            }
            return txtdescription;
        }

        public ImageView getImageView() {
            if (imageView == null) {
                imageView = view.findViewById(R.id.imageView);
            }
            return imageView;
        }
    }
}

