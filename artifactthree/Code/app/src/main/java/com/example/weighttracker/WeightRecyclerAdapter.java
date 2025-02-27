package com.example.weighttracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WeightRecyclerAdapter extends RecyclerView.Adapter<WeightRecyclerAdapter.ViewHolder> {
    private static ArrayList<Weight> dataSet = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final Button buttonDelete;
        private final EditText editTextDate;
        private final EditText editTextWeightNumber;

        public ViewHolder(View v) {
            super(v);

            buttonDelete = (Button) v.findViewById(R.id.buttonDelete);
            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("Deleted");
                }
            });
            editTextDate = (EditText) v.findViewById((R.id.editTextDate));
            editTextWeightNumber = (EditText) v.findViewById(R.id.editTextWeightNumber);
        }

        public EditText getEditTextDate() {
            return editTextDate;
        }

        public EditText getEditTextWeightNumber() {
            return editTextWeightNumber;
        }
    }

    public WeightRecyclerAdapter(ArrayList<Weight> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.weight_row_item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        if (dataSet.get(position) == null) return;
        viewHolder.getEditTextDate().setText(dataSet.get(position).getDate());
        viewHolder.getEditTextWeightNumber().setText(String.valueOf(dataSet.get(position).getWeight()));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
