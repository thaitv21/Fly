package com.esp.fly.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.esp.fly.R;
import com.esp.fly.models.Friend;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>{

    private Context context;
    private List<Friend> friendList;

    public ContactAdapter(Context context, List<Friend> friendList) {
        this.context = context;
        this.friendList = friendList;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.friend_row, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Friend friend = friendList.get(position);
        Glide.with(context).load(Uri.parse(friend.getAvatarUrl())).into(holder.avatar);
        holder.nameView.setText(friend.getName());
        holder.descriptionView.setText(friend.getDescription());
    }

    @Override
    public int getItemCount() {
        return friendList.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        public CircleImageView avatar;
        public TextView nameView;
        public TextView descriptionView;

        public ContactViewHolder(View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.friend_avatar);
            nameView = itemView.findViewById(R.id.friend_name);
            descriptionView = itemView.findViewById(R.id.friend_desc);
        }
    }
}
