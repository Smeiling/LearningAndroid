package com.smeiling.learning.draglayout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smeiling.learning.Logg;
import com.smeiling.learning.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: Smeiling
 * @Date: 2019-04-10 15-37
 * @Description:
 */
public class DragAdapter extends RecyclerView.Adapter<DragAdapter.Holder> {

    private List<String> mData;
    private LinkedList<String> mRealData = new LinkedList<>();
    private Context mContext;

    public DragAdapter(Context mContext) {
        this.mContext = mContext;
        mData = new ArrayList<>();
        mData.add("one");
        mData.add("two");
        mData.add("three");
        mData.add("four");
        mData.add("five");
        mData.add("six");
        mData.add("seven");
        mRealData.addAll(mData);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_drag_layout_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        holder.tv.setText(mData.get(position));
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String deletedItem = mData.remove(position);
                mRealData.remove(deletedItem);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private int startPosition = -1;
    private int finalPosition;

    public void onMove(int fromPosition, int toPosition) {
        Logg.error("fromPosition = " + fromPosition + " toPosition = " + toPosition);
        /**
         * 在这里进行给原数组数据的移动
         */
        Collections.swap(mData, fromPosition, toPosition);
        if (startPosition < 0) {
            startPosition = fromPosition;
        }
        finalPosition = toPosition;
        /**
         * 通知数据移动
         */
        notifyItemMoved(fromPosition, toPosition);
    }

    public void onSwipe(int position) {
        /**
         * 原数据移除数据
         */
        mData.remove(position);
        /**
         * 通知移除
         */
        notifyItemRemoved(position);
    }

    public void onStop() {
        if (startPosition >= 0) {
            Logg.error("onStop StartPosition = " + startPosition + ",StopPosition = " + finalPosition);
            String tmp = mRealData.remove(startPosition);
            mRealData.add(finalPosition, tmp);
            mData.clear();
            mData.addAll(mRealData);
            notifyDataSetChanged();
            startPosition = -1;
        }
    }

    public List<String> getData() {
        return mRealData.subList(0, mRealData.size());
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView tv;

        public Holder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }

    /**
     * 拖动布局回调
     */
    public static class MyItemTouchHelperCallback extends ItemTouchHelper.Callback {

        private DragAdapter mAdapter;

        public MyItemTouchHelperCallback(DragAdapter mAdapter) {
            this.mAdapter = mAdapter;
        }

        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            //上下拖拽，若有其他需求同理
            int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
            //向右侧滑，若有其他需求同理
            int swipeFlags = 0;
            return makeMovementFlags(dragFlags, swipeFlags);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            //通知Adapter更新数据和视图
            mAdapter.onMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
            return true;
        }

        @Override
        public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
            super.onSelectedChanged(viewHolder, actionState);
            mAdapter.onStop();

        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            //通知Adapter更新数据和视图
            mAdapter.onSwipe(viewHolder.getAdapterPosition());
        }

        @Override
        public boolean isItemViewSwipeEnabled() {
            //是否可以左右侧滑，默认返回true
            return true;
        }

        @Override
        public boolean isLongPressDragEnabled() {
            //是否可以长按上下拖拽，默认返回false
            return true;
        }
    }
}
