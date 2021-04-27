package com.example.covid_dash;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NYTAdpater extends RecyclerView.Adapter<NYTAdpater.ViewHolder> {
    Context context;
    List<NYT> nyts;

    //Pass in the context and list of tweets
    public NYTAdpater(Context context, List<NYT> nyts) {
        this.context = context;
        this.nyts = nyts;
    }

    //For each row, inflate the layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_headlines, parent, false);
        return new ViewHolder(view);
    }

    //Bind values based on the position
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Get the data at position
        NYT nyt = nyts.get(position);
        //Bind the tweet with view holder
        holder.bind(nyt);
    }

    @Override
    public int getItemCount() {
        return nyts.size();
    }

    //Clean all elements of the recycler
    public void clear(){
        nyts.clear();
        notifyDataSetChanged();
    }

    //Add a list of items - change
    public void addAll(List<NYT> tweetList) {
        nyts.addAll(tweetList);
        notifyDataSetChanged();
    }

    //Define a viewholder
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvHeadline;
        TextView tvDescription;
        TextView tvLink;
        TextView tvTimeStamp;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tvHeadline = itemView.findViewById(R.id.tvHeadline);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvLink = itemView.findViewById(R.id.tvLink);
            tvTimeStamp = itemView.findViewById(R.id.tvTimeStamp);
        }

        public void bind(NYT nyt) {
            tvHeadline.setText(nyt.getHeadline());
            tvDescription.setText(nyt.getDescription());
            tvLink.setText(nyt.getLink());
            tvTimeStamp.setText(nyt.getTimeStamp());
        }
    }
}
