package xyz.egoistk21.iFantasy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import java.util.List;

import xyz.egoistk21.iFantasy.widget.FilterCheckedTextView;
import xyz.egoistk21.iFantasy.widget.OnFilterItemClickListener;
import xyz.egoistk21.iFantasy.widget.SingleListView;

import static android.view.Gravity.CENTER;

/**
 * author: baiiu
 * date: on 16/1/17 21:14
 * description:
 */
public class DropMenuAdapter implements MenuAdapter {
    private final Context mContext;
    private OnFilterDoneListener mOnFilterDoneListener;
    private String[] mTitles;
    private List<String> mTypes;

    public DropMenuAdapter(Context context, String[] titles, List<String> types, OnFilterDoneListener onFilterDoneListener) {
        mContext = context;
        mTitles = titles;
        mTypes = types;
        mOnFilterDoneListener = onFilterDoneListener;
    }

    @Override
    public int getMenuCount() {
        return mTitles.length;
    }

    @Override
    public String getMenuTitle(int position) {
        return mTitles[position];
    }

    @Override
    public int getBottomMargin(int position) {
        return 0;
    }

    @Override
    public View getView(int position, FrameLayout parentContainer) {
        return createSingleListView();
    }

    private View createSingleListView() {
        SingleListView<String> singleListView = new SingleListView<String>(mContext)
                .adapter(new SimpleTextAdapter<String>(null, mContext) {
                    @Override
                    public String provideText(String string) {
                        return string;
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        checkedTextView.setGravity(CENTER);
                    }
                })
                .onItemClick(new OnFilterItemClickListener<String>() {
                    @Override
                    public void onItemClick(String item) {
                        onFilterDone(item);
                    }
                });
        singleListView.setList(mTypes, -1);
        RelativeLayout.LayoutParams mParam = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 200);
        singleListView.setLayoutParams(mParam);
        return singleListView;
    }


    private void onFilterDone(String item) {
        if (mOnFilterDoneListener != null) {
            mOnFilterDoneListener.onFilterDone(0, "", item);
        }
    }

}
