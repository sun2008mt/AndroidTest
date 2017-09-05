package sun.geonoon.wh.androidtest.messaging;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import sun.geonoon.wh.androidtest.R;

/**
 * Created by marc on 17-9-5.
 */

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {

    private List<Msg> msgList;

    static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout layoutLeft;

        LinearLayout layoutRight;

        TextView msgLeft;

        TextView msgRight;

        public ViewHolder(View itemView) {
            super(itemView);
            layoutLeft = itemView.findViewById(R.id.layout_left);
            layoutRight = itemView.findViewById(R.id.layout_right);
            msgLeft = itemView.findViewById(R.id.msg_left);
            msgRight = itemView.findViewById(R.id.msg_right);
        }
    }

    public MsgAdapter(List<Msg> msgList) {
        this.msgList = msgList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_msg, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Msg msg = msgList.get(position);
        if (Msg.TYPE_RECEIVED == msg.getType()) {
            //接收的消息
            holder.layoutLeft.setVisibility(View.VISIBLE);
            holder.layoutRight.setVisibility(View.GONE);
            holder.msgLeft.setText(msg.getContent());
        } else if (Msg.TYPE_SEND == msg.getType()) {
            //发送的消息
            holder.layoutLeft.setVisibility(View.GONE);
            holder.layoutRight.setVisibility(View.VISIBLE);
            holder.msgRight.setText(msg.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }
}
