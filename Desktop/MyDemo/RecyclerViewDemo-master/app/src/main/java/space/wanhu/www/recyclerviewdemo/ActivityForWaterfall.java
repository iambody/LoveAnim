package space.wanhu.www.recyclerviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import adapter.MyAdapterForStagger;

/**
 * 模拟瀑布流布局的Activity
 */
public class ActivityForWaterfall extends AppCompatActivity {
    //工具对象
    private ActivityForWaterfall context;
    private List<String> mDatas;

    //布局对象
    private RecyclerView mRecyclerView;
    private MyAdapterForStagger adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        initView();
        initData();
        initViewOpe();
    }

    private void initViewOpe() {
        //处理事件，短按增加Item,长按删除Item
        adapter.setOnItemClickListener(new MyAdapterForStagger.OnItemClickListener() {
            @Override
            public void onShortClick(View view, int position) {
                adapter.add(position);
            }

            @Override
            public void onLongClick(View view, int position) {
                adapter.delete(position);

            }
        });

    }

    /**处理数据逻辑*/
    private void initData() {
        //获得数据源
        mDatas = new ArrayList<>();
        for(int i='A';i<'z';i++){
            mDatas.add((char)i +"");
        }

        //使用adapter
        adapter = new MyAdapterForStagger(context,mDatas);
        mRecyclerView.setAdapter(adapter);

        //设置RecyclerView的布局方向
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);

    }

    /**获取布局组件*/
    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

    }


}
