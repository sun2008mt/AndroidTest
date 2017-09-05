package sun.geonoon.wh.androidtest.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import sun.geonoon.wh.androidtest.R;

/**
 * Created by marc on 17-9-5.
 */

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.ViewHolder> {

    private List<Type> mTypeList;

    public TypeAdapter(List<Type> types) {
        mTypeList = types;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View typeView;
        ImageView typeImage;
        TextView typeName;

        public ViewHolder(View itemView) {
            super(itemView);
            typeView = itemView;
            typeImage = itemView.findViewById(R.id.type_image);
            typeName = itemView.findViewById(R.id.type_name);
        }
    }

    //创建ViewHolder实例
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_horizontal, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.typeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Type type = mTypeList.get(position);
                Toast.makeText(view.getContext(), "you clicked view " + type.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.typeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Type type = mTypeList.get(position);
                Toast.makeText(view.getContext(), "you clicked image " + type.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    //对子项数据进行赋值
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Type type = mTypeList.get(position);
        holder.typeImage.setImageResource(type.getImageId());
        holder.typeName.setText(type.getName());
    }

    @Override
    public int getItemCount() {
        return mTypeList.size();
    }
}
