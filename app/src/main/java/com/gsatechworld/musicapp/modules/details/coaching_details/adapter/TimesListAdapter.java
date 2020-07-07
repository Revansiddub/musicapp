package com.gsatechworld.musicapp.modules.details.coaching_details.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.musicapp.R;


import java.util.ArrayList;

import static android.view.LayoutInflater.from;
import static androidx.databinding.DataBindingUtil.inflate;

public class TimesListAdapter extends RecyclerView.Adapter<TimesListAdapter.TimesViewHolder> {
  public Context mContext;
  private ArrayList<RecyclerData> myList;
  int mLastPosition = 0;
  Onclick onclick;



  public TimesListAdapter(Context context,ArrayList<RecyclerData> myList,Onclick onclick) {
    this.mContext=context;
    this.myList = myList;
    this.onclick=onclick;
  }

  @NonNull
  @Override
  public TimesListAdapter.TimesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.list_times,parent,false);
    return new TimesViewHolder(view);

  }

  @Override
  public void onBindViewHolder(@NonNull TimesListAdapter.TimesViewHolder holder, int position) {
    final RecyclerData data=myList.get(position);
    if (data.getStarttime() !=null) {
      holder.editText_time.setText(data.getStarttime());
    }
    holder.removeImg.setOnClickListener(v -> {
      myList.remove(position);
      notifyDataSetChanged();
    });


    holder.llItem.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        onclick.onEvent(data,position);
      }
    });
  }



  public interface Onclick {
    void onEvent(RecyclerData model,int pos);
  }



  @Override
  public int getItemCount() {
    return myList.size();
  }

  public void notifyData(ArrayList<RecyclerData> myList) {
    Log.d("notifyData ", myList.size() + "");
    this.myList = myList;

  }

  public class TimesViewHolder extends RecyclerView.ViewHolder {
    private TextView editText_time;
    ImageView removeImg;
    LinearLayout llItem;
    public TimesViewHolder(final View parent) {
      super(parent);
      editText_time=(TextView)parent.findViewById(R.id.text_slot);
      removeImg=parent.findViewById(R.id.img_remove);
      llItem = itemView.findViewById(R.id.ll_item);

    }
  }
}
