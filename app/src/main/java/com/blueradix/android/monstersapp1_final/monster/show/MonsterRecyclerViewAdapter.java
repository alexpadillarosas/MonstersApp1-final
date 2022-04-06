package com.blueradix.android.monstersapp1_final.monster.show;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.blueradix.android.monstersapp1_final.databinding.RecyclerItemViewBinding;
import com.blueradix.android.monstersapp1_final.monster.Monster;

import java.util.List;


public class MonsterRecyclerViewAdapter extends ListAdapter<Monster, MonsterViewHolder> {

    private RecyclerItemViewBinding binding;

    protected MonsterRecyclerViewAdapter(){
        super(DIFF_CALLBACK);
    }

    /**
     * This helper method check the difference between 2 Monster
     */
    private static final DiffUtil.ItemCallback<Monster> DIFF_CALLBACK = new DiffUtil.ItemCallback<Monster>() {
        @Override
        public boolean areItemsTheSame(@NonNull Monster oldItem, @NonNull Monster newItem) {
            return oldItem.getId() == newItem.getId();
            // If the old monster and the new monster have the same ID then they represent
            // the same monster in our database
        }

        @Override
        public boolean areContentsTheSame(@NonNull Monster oldItem, @NonNull Monster newItem) {
            return  oldItem.getVotes() == newItem.getVotes() &&
                    oldItem.getStars() == newItem.getStars() &&
                    oldItem.getScariness() == newItem.getScariness() &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getImage().equals(newItem.getImage());
            //check if the content of the 2 monsters are the same
        }
    };

    /**
     * Creates a view holder whenever the RecyclerView needs a new one, it creates a view holder(data in one element of the recyclerView).
     * This is the moment when the row layout is inflated (grab the RecyclerItemViewBinding and turning it into GUI component).
     * Creates a new view Holder(MonsterViewHolder in this case) by passing the recently inflated view binding
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public MonsterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        binding = RecyclerItemViewBinding.inflate(inflater, parent, false);

        MonsterViewHolder monsterViewHolder = new MonsterViewHolder(binding);
        return monsterViewHolder;
    }

    /**
     * Takes a ViewHolder object and sets the proper list data (from the list) on the view
     * @param holder    an object of MonsterViewHolder class, representing each item (CardView content)
     *                  in the recyclerView
     * @param position  the position of the monster in the monsters list
     */
    @Override
    public void onBindViewHolder(@NonNull MonsterViewHolder holder, int position) {
        //get data from the list based on position
        Monster monster = getMonsterAt(position);
        //call the method to set the values in the MonsterViewHolder
        holder.updateMonster(monster);

    }

    /**
     * Get a Monster object at a particular position in the list
     * @param position
     * @return monster
     */
    public Monster getMonsterAt(int position){
        return getItem(position);
    }
}
