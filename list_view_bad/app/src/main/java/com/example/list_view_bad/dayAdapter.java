package com.example.list_view_bad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class dayAdapter extends ArrayAdapter<day> {
        private day[] _day;
        private Context _context;

    public dayAdapter(Context context,day[] days){
        super(context,R.layout.custom_row,R.id.txt_name,days);
        _context=context;
        _day=days;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view=convertView;
        ViewHolder viewHolder;
        if(view==null) {
            LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_row, parent, false);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        }
        else {
            viewHolder=(ViewHolder)view.getTag();
        }

        viewHolder.getTxtname().setText(_day[position].getDay());
        viewHolder.getTxtdescription().setText(_day[position].getDescription());
        viewHolder.getImageView().setImageResource(_day[position].getImage());


        return view;
    }
    private class ViewHolder{
        View view;
        TextView txtname;
        TextView txtdescription;
        ImageView imageView;
        public ViewHolder(View convertView){
            view=convertView;
        }
        public  TextView getTxtname(){
            if(txtname==null){
                txtname=view.findViewById(R.id.txt_name);
            }
            return txtname;
        }
        public  TextView getTxtdescription(){
            if(txtdescription==null){
                txtdescription=view.findViewById(R.id.txt_description);
            }
            return txtdescription;
        }
        public  ImageView getImageView(){
            if(imageView==null){
                imageView=view.findViewById(R.id.imageView);
            }
            return imageView;
        }


    }
}
