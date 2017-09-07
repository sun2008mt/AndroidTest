package sun.geonoon.wh.androidtest.materialdesign;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//import com.bumptech.glide.Glide;

import java.util.List;

import sun.geonoon.wh.androidtest.R;
import sun.geonoon.wh.androidtest.recyclerview.Type;

/**
 * Created by Administrator on 2017/9/8.
 */

public class TypeCardViewAdapter extends RecyclerView.Adapter<TypeCardViewAdapter.ViewHolder> {

    private Context mContext;

    private List<Type> mTypeList;

    static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            imageView = itemView.findViewById(R.id.type_image);
            textView = itemView.findViewById(R.id.type_name);

        }
    }

    public TypeCardViewAdapter(List<Type> typeList) {
        mTypeList = typeList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_type_cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Type type = mTypeList.get(position);
        holder.textView.setText(type.getName());
        holder.imageView.setImageResource(type.getImageId());
        //使用Glide，当图片像素特别高的时候，Glide会封装压缩等许多对图像的处理，避免了内存溢出等问题
//        Glide.with(mContext).load(type.getImageId()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mTypeList.size();
    }
}
