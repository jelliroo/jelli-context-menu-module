# jelli-context-menu-module
A convenience module that implements multi-select for recycler views

# Usage:

Add the library to your android project and extend the SupportActionModeActivity or SupportActionModeFragment:

```java
public class MainActivity extends SupportActionModeActivity {...}
```

or

```java
public class ActionModeFragment extends SupportActionModeFragment {...}
```

Set title of the context menu:

```java
setPluralItemTitle("Items");
setSingularItemTitle("Item");
```

Declare the adapter for your recycler view. Pass the ViewHolder, key and value classes as parameters:

```java
//an adapter  with key of integer type and value of string type
private RecyclerViewAdapter<MyViewHolder, Integer, String> adapter;
```

After instantiating the adapter, handler the data inside onBindViewHolder:

```java
@Override
public void onBindViewHolder(ViewHolder holder, int position) {
    //set the data
    holder.textView.setText(getItemAt(position));

    if(isItemAtPositionSelected(position)){
        //set selected background
    } else {
        //set unselected background
    }
}
```

In your activity or fragment, add the data to the adapter:

```java
for(int i=0; i<255; i++){
            adapter.addItem(i, "test " + i);
  }
```

In your activity or fragment, tell jelli-context-menu-module where it should find the recyclerView

```java
setRecyclerView(R.id.context_recycler);
```

In your activity or fragment, set the adapter to your recyclerView

```java
getRecyclerView().setAdapter(adapter);
```

Make other initializations to your recyclerView

```java
LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
getRecyclerView().setLayoutManager(linearLayoutManager);
DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
        getRecyclerView().getContext(),
        linearLayoutManager.getOrientation());
getRecyclerView().addItemDecoration(dividerItemDecoration);
```

Return the adapter you created from your activity or fragment so jelli-context-menu-module can use it

```java
@Override
public RecyclerViewAdapter<? extends RecyclerView.ViewHolder, ?, ?> getAdapter() {
    return adapter;
}
```

Implement methods to catch context menu events

```java
@Override
public void onContextMenuClosed() {
  //context menu was closed
}

@Override
public void onContextMenuOpened() {
  //context menu opened
}

@Override
public boolean onContextMenuItemSelected(
  List selectedItems,
  int actionCode) {
  //perform action on selectedItems based on the selected actionCode
  return false;
}
```
