package com.packt.coolnewandroid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

public class CoolPagedListAdapter extends PagedListAdapter<Automobile, AutomobileRecyclerAdapter.AutomobileViewHolder> {

    private static DiffUtil.ItemCallback<Automobile> diffCallback = new DiffUtil.ItemCallback<Automobile>() {
        @Override
        public boolean areItemsTheSame(@NonNull Automobile oldItem, @NonNull Automobile newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Automobile oldItem, @NonNull Automobile newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    };


    private Context context;
//    private List<Automobile> automobiles = new ArrayList<>();


    protected CoolPagedListAdapter(Context context) {
        super(diffCallback);
        this.context = context;
    }

    @NonNull
    @Override
    public AutomobileRecyclerAdapter.AutomobileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        return new AutomobileRecyclerAdapter.AutomobileViewHolder((TextView) view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AutomobileRecyclerAdapter.AutomobileViewHolder holder, int position) {
        final Automobile automobile = getItem(position);
        if (automobile != null) {
            holder.getName().setText(automobile.getMake() + " " + automobile.getModel());
        }

    }
}
