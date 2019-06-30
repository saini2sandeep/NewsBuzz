package com.example.newsbuzz.ui;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.newsbuzz.R;
import com.example.newsbuzz.database.entity.NewsEntity;
import com.example.newsbuzz.databinding.ItemNewsBinding;

import java.util.List;

/**
 * Created by sandeepsaini on 29,June,2019
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<NewsEntity> newsEntityList;
    private NewsClickListener newsClickListener;

    public interface NewsClickListener {
        void onNewsClick(NewsEntity newsEntity);
    }

    public NewsAdapter(List<NewsEntity> newsEntityList, NewsClickListener newsClickListener) {
        this.newsEntityList = newsEntityList;
        this.newsClickListener = newsClickListener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        ItemNewsBinding itemNewsBinding = DataBindingUtil.inflate(LayoutInflater.from(
                viewGroup.getContext()), R.layout.item_news, viewGroup, false);

        return new NewsViewHolder(itemNewsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int i) {
        newsViewHolder.bindItemData(newsEntityList.get(i));
    }

    @Override
    public int getItemCount() {
        return newsEntityList == null ? 0 : newsEntityList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        ItemNewsBinding itemNewsBinding;

        public NewsViewHolder(@NonNull ItemNewsBinding itemNewsBinding) {
            super(itemNewsBinding.getRoot());
            this.itemNewsBinding = itemNewsBinding;
        }

        public void bindItemData(NewsEntity newsEntity) {
            itemNewsBinding.setNewsEntity(newsEntity);
            itemNewsBinding.setNewsClickListener(newsClickListener);
        }
    }
}
