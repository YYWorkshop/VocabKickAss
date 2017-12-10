package com.yyworkshop.vocabkickass.adapter;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yyworkshop.vocabkickass.R;
import com.yyworkshop.vocabkickass.data.DictConstarct;
import com.yyworkshop.vocabkickass.model.VocabModel;

/**
 * Created by hulonelyy on 2017/12/6.
 */

public class SearchResultRecyclerViewAdapter extends RecyclerView.Adapter<SearchResultRecyclerViewAdapter.ViewHolder>{

    private Cursor vocabCursor;
    private OnClickAdapterItemListener onClickAdapterItemListener;

    public SearchResultRecyclerViewAdapter(Cursor data) {
        this.vocabCursor = data;
    }

    public SearchResultRecyclerViewAdapter() {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemV = inflater.inflate(R.layout.layout_item_rv_search_result, parent, false);
        ViewHolder holder = new ViewHolder(itemV);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Log.wtf("SearchResultRecyclerViewAdapter", "onBindViewHolder =>"+position);

        vocabCursor.moveToPosition(position);
        final VocabModel vocabModel = DictConstarct.getVocabModel(vocabCursor);
        holder.tvDict.setText(vocabModel.getDictsName());
        holder.tvVocab.setText(vocabModel.getVocab());

        if (onClickAdapterItemListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickAdapterItemListener.OnClickAdapterItem(v, vocabModel);
                }
            });
        }


    }

    @Override
    public int getItemCount() {

        if (vocabCursor == null) {
            return 0;
        }

        return vocabCursor.getCount();
    }

    public void swapCursor(Cursor vocabCursor) {
        this.vocabCursor = vocabCursor;
        notifyDataSetChanged();
    }

    public OnClickAdapterItemListener getOnClickAdapterItemListener() {
        return onClickAdapterItemListener;
    }

    public void setOnClickAdapterItemListener(OnClickAdapterItemListener onClickAdapterItemListener) {
        this.onClickAdapterItemListener = onClickAdapterItemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvVocab;
        TextView tvDict;

        public ViewHolder(View itemView) {
            super(itemView);

            tvVocab = itemView.findViewById(R.id.tv_vocab_title);
            tvDict = itemView.findViewById(R.id.tv_dict);

        }
    }

    public interface OnClickAdapterItemListener {

        void OnClickAdapterItem(View v, VocabModel model);

    }

}
