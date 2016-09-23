package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import space.wanhu.www.recyclerviewdemo.R;

/**
 * Created by Iron Man on 2016/7/11.
 * 用来形成瀑布流效果的adapter
 */
public class MyAdapterForStagger extends RecyclerView.Adapter<MyAdapterForStagger.MyViewHolder>{

    private Context context;
    private List<String> mDatas;
    private List<Integer> mHeights;//使用随机数生成高度
    private OnItemClickListener listener;//回调接口对象

    public MyAdapterForStagger(Context context, List<String> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
        mHeights = new ArrayList<>();
        for(String s:mDatas){
            mHeights.add((int) (150+Math.random()*400));//添加随机数据
        }
    }

    /**注册事件*/
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public MyAdapterForStagger.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_item,parent,false);
        MyAdapterForStagger.MyViewHolder viewHolder = new MyAdapterForStagger.MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyAdapterForStagger.MyViewHolder holder, int position) {

        //设置不同的布局高度
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
        params.height = mHeights.get(position);
        holder.itemView.setLayoutParams(params);
        holder.textView.setText(mDatas.get(position));

        //在每个Item生成的时候，处理事件（在设置了事件处理时）
        if(listener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //通过视图精确获得每个Item的位置
                    int position = holder.getLayoutPosition();
                    listener.onShortClick(v,position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //通过视图精确获得每个Item的位置
                    int position = holder.getLayoutPosition();
                    listener.onLongClick(v,position);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * 动态添加数据的方法
     * @param position 添加数据的位置
     */
    public void add(int position){
        mDatas.add(position,"I'm here");//插入数据
        mHeights.add((int) (150+Math.random()*400));//为新添加的Item设置高度，不处理的话，会有数组越界异常
        notifyItemInserted(position); //注意此处调用的是这种notify

    }

    /**
     * 动态删除数据的方法
     * @param position 要删除的数据的位置
     */
    public void delete(int position){
        mDatas.remove(position);
        notifyItemRemoved(position);//注意此处调用的是这种notify
    }

    /**自定义的ViewHolder用来缓存组件*/
    static class MyViewHolder extends RecyclerView.ViewHolder{

        //视图组件
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_text);
        }
    }

    /**自定义回调接口，实现短按和长按Item的效果*/
    public interface OnItemClickListener{
        //短按时
        void onShortClick(View view,int position);
        //长按时
        void onLongClick(View view,int position);
    }
}
