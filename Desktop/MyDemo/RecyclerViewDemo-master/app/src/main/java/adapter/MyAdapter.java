package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import space.wanhu.www.recyclerviewdemo.R;

/**
 * Created by Iron Man on 2016/7/10.
 * 适用于RecyclerView的适配器
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    public Context context;
    public List<String> mDatas;
    public OnItemClickListener listener;

    /**
     * 通过构造方法传入数据
     */
    public MyAdapter(Context context, List<String> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * 在该方法中，需要返回我们定义的viewHolder,
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }


    /**
     * 在该方法中，需要进行一些数据的绑定操作
     */
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.textView.setText(mDatas.get(position));

        //设置我们自定义的事件处理
        if (listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //此处的position需要重新获得
                    int position = holder.getLayoutPosition();
                    listener.onClick(v, position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    //此处的position需要重新获得
                    int position = holder.getLayoutPosition();
                    listener.onLongClick(v, position);
                    return false;
                }
            });
        }
    }

    /**
     * 添加item的方法
     */
    public void add(int position) {
        mDatas.add(position, "Hello");
        notifyItemInserted(position);
    }

    /**
     * 删除item的动画
     */
    public void delete(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * 需要自定义ViewHolder
     */
    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_text);
        }
    }

    /**
     * 添加回调接口
     */
    public interface OnItemClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }
}
