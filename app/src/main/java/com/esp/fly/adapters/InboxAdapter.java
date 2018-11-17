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
import com.esp.fly.models.Conservation;
import com.esp.fly.models.Friend;
import com.esp.fly.models.Message;
import com.esp.fly.utils.MathUtils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.MessageViewHolder>{

    private Context context;
    private List<Conservation> conservations;

    public InboxAdapter(Context context, List<Conservation> conservations) {
        this.context = context;
        this.conservations = conservations;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_message_row, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        Conservation conservation = conservations.get(position);
        Friend friend = new Friend(conservation.getFriendId());
        Message lastMessage = conservation.getLastMessage();
        Glide.with(context).load(Uri.parse(friend.getAvatarUrl())).into(holder.avatar);
        holder.nameView.setText(friend.getName());
        holder.timeView.setText(MathUtils.calculateTimeAgo(lastMessage.getTime()));
        holder.messageView.setText(lastMessage.getContent());
    }

    @Override
    public int getItemCount() {
        return conservations.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {

        public CircleImageView avatar;
        public TextView nameView;
        public TextView timeView;
        public TextView messageView;

        public MessageViewHolder(View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.message_item_avatar);
            nameView = itemView.findViewById(R.id.message_item_name);
            timeView = itemView.findViewById(R.id.message_item_time_ago);
            messageView = itemView.findViewById(R.id.message_item_content);
        }
    }
}
