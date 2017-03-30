package io.scottware.fireandice.presentation.view.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;
import io.scottware.fireandice.data.local.model.BookRealmModel;
import io.scottware.fireandice.presentation.R;
import io.scottware.fireandice.presentation.view.model.BookViewModel;
import io.scottware.fireandice.presentation.view.model.mapper.BookRealmToViewModel;

public class BookRecyclerViewAdapter extends RealmRecyclerViewAdapter<BookRealmModel, BookRecyclerViewAdapter.BookViewHolder> {

    private OnItemClickListener onItemClickListener;
    private BookRealmToViewModel bookRealmToViewModel;

    public interface OnItemClickListener {
        void onItemClick(BookRealmModel bookRealmModel);
    }

    public BookRecyclerViewAdapter(@Nullable OrderedRealmCollection<BookRealmModel> data, OnItemClickListener onItemClickListener, BookRealmToViewModel bookRealmToViewModel) {
        super(data, true);
        this.onItemClickListener = onItemClickListener;
        this.bookRealmToViewModel = bookRealmToViewModel;
        setHasStableIds(false);
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_book, parent, false);
        return new BookViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        final BookRealmModel bookRealmModel = getItem(position);
        final BookViewModel viewModel = bookRealmToViewModel.transform(bookRealmModel);
        holder.bookNumber.setText(viewModel.getBookNumber());
        holder.title.setText(viewModel.getBookTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(bookRealmModel);
            }
        });
    }

    class BookViewHolder extends RecyclerView.ViewHolder {

        TextView bookNumber;
        TextView title;

        public BookViewHolder(View itemView) {
            super(itemView);
            bookNumber = (TextView) itemView.findViewById(R.id.bookNumber);
            title = (TextView) itemView.findViewById(R.id.title);
        }

    }
}
