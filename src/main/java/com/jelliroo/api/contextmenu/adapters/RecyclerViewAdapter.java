package com.jelliroo.api.contextmenu.adapters;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created on 3/6/2017 by roger
 * RecyclerView Adapter for ActionMode
 */

@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class RecyclerViewAdapter<T extends RecyclerView.ViewHolder, Key, Val> extends RecyclerView.Adapter<T> {

    private final List<Key> selectedItems = new ArrayList<>();

    private final HashMap<Key, Val> hashMap = new LinkedHashMap<>();

    public final void toggleSelection(int pos) {

        Key key = getKeyAtPosition(pos);

        Val object = hashMap.get(key);
        if(object == null) return;

        if(selectedItems.contains(key)){
            selectedItems.remove(key);
        } else {
            selectedItems.add(key);
        }
        notifyDataSetChanged();
    }

    public final void toggleSelectAll() {
        List<Key> list = new ArrayList<>(hashMap.keySet());
        if(selectedItems.size() == getItemCount()){
            selectedItems.clear();
            for(Key object : list){
                selectedItems.remove(object);
            }
        } else {
            selectedItems.clear();
            for(Key object : list){
                selectedItems.add(object);
            }
        }
        notifyDataSetChanged();
    }

    public final void removeItem(Key key){
        hashMap.remove(key);
        selectedItems.remove(key);
        notifyDataSetChanged();
    }

    public final void addItem(Key key, Val value){
        hashMap.put(key, value);
        notifyDataSetChanged();
    }

    public final Val getItem(Key key){
        return hashMap.get(key);
    }

    public final Val getItemAt(int pos){
        return new ArrayList<>(hashMap.values()).get(pos);
    }

    public final boolean isItemAtPositionSelected(int pos){
        return selectedItems.contains(new ArrayList<>(hashMap.keySet()).get(pos));
    }

    public final Key getKeyAtPosition(int pos){
        return new ArrayList<>(hashMap.keySet()).get(pos);
    }

    public final void removeAll(){
        hashMap.clear();
        selectedItems.clear();
        notifyDataSetChanged();
    }

    public final void removeByKeys(List<Key> keys){
        for(Key key : keys){
            hashMap.remove(key);
        }
        notifyDataSetChanged();
    }

    public final void addAll(List<Key> keys, List<Val> values){
        if(keys.size() != values.size()) return;

        for(int i=0; i < keys.size(); i++){
            hashMap.put(keys.get(i), values.get(i));
        }
    }

    public final void addAll(HashMap<Key, Val> items){
        hashMap.putAll(items);
    }

    public final void clearSelections() {
        selectedItems.clear();
        notifyDataSetChanged();
    }

    public final int getSelectedItemCount() {
        return selectedItems.size();
    }

    public final List<Key> getSelectedItems() {
        return selectedItems;
    }

    @Override
    public final int getItemCount() {
        return hashMap.size();
    }


}
