package io.scottware.fireandice.presentation.view.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;
import io.scottware.fireandice.data.local.model.CharacterRealmModel;
import io.scottware.fireandice.presentation.R;

public class CharacterRecyclerViewAdapter extends RealmRecyclerViewAdapter<CharacterRealmModel, CharacterRecyclerViewAdapter.CharacterViewHolder> {

    public CharacterRecyclerViewAdapter(@Nullable OrderedRealmCollection<CharacterRealmModel> data) {
        super(data, true);
        setHasStableIds(false);
    }

//    @Override
//    public long getItemId(int index) {
//        String url = getItem(index).getUrl();
//        String[] split = url.split("/");
//        Integer id = Integer.parseInt(split[split.length - 1]);
//        return id;
//    }

    @Override
    public CharacterRecyclerViewAdapter.CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_character, parent, false);
        return new CharacterRecyclerViewAdapter.CharacterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CharacterRecyclerViewAdapter.CharacterViewHolder holder, int position) {
        final CharacterRealmModel bookEntity = getItem(position);
        holder.data = bookEntity;
        if (bookEntity.getName() == null) {
            holder.title.setText("...");
        } else {
            holder.title.setText(bookEntity.getName());
        }
    }

    class CharacterViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        public CharacterRealmModel data;

        public CharacterViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.text_name);
        }

    }

}
