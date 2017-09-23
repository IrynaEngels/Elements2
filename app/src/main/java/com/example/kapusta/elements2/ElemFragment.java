package com.example.kapusta.elements2;

/**
 * Created by Kapusta on 23.09.2017.
 */


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.kapusta.elements2.ElementInterface.ElementListener;
import com.example.kapusta.elements2.RealmData.RealmReminder;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kapusta on 13.09.2017.
 */

public class ElemFragment extends Fragment {


    @BindView(R2.id.rv)
    RecyclerView resView;
    @BindView(R2.id.editText)
    EditText et_name;
    @BindView(R2.id.editText2)
    EditText et_info;
    @BindView(R2.id.button)
    Button btn_save;

    RealmReminder realmReminder;
    ResViewAdapter adapter;
    List<Element> list;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recycler,
                container, false);
        ButterKnife.bind(this, view);


        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        resView.setLayoutManager(llm);



        realmReminder = new RealmReminder();

//        initializeData()
//                .subscribeOn(Schedulers.io())
//                .flatMap(Observable::fromIterable)
////                .filter(element -> element.getNumber() > 10)
//                .doOnNext(element -> adapter.addElement(element))
//
//                .toList()
//                .toObservable()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(elements -> {
//                    Log.d("TAG", "done");
//                }, throwable -> {
//                    Log.e("TAG", throwable.toString());
//                });
        btn_save.setOnClickListener(view1 -> {
            dataBaseR();
            displayElements();
        });

        return view;
    }
    private long elementId(){
        list = realmReminder.readeReminders(getActivity());
        if (list.size()==0) return 1;
        else return list.get(list.size()-1).getId()+1;
    }
    private void dataBaseR(){
        Element element = new Element();
        element.setId(elementId());
        element.setName(et_name.getText().toString());
        element.setInfo(et_info.getText().toString());
        realmReminder.saveReminder(getActivity(), element);

    }
    private void displayElements(){
        adapter = new ResViewAdapter(new ArrayList<>(), s -> ElemFragment.this.showDialog(s));
        resView.setAdapter(adapter);
        list = realmReminder.readeReminders(getActivity());
        for (Element e:list) {
            adapter.addElement(e);
        }
    }

    private Observable<List<Element>> initializeData() {
        list = new ArrayList<>();
        Element element = new Element();
        element.setName(et_name.getText().toString());
        element.setInfo(et_info.getText().toString());
        return Observable.just(list)
                .doOnNext(elements -> elements.add(element));
    }


    private void showDialog(Element element) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Info")
                .setMessage("Delete " + element.getName() + "," + element.getId()+ " element?")
                .setIcon(R.drawable.info)
                .setCancelable(false)
                .setNegativeButton("OK", (dialogInterface, i) -> {
                    realmReminder.removeReminder(getActivity(),element.getId());
                    displayElements();
                })
                .setPositiveButton("CANCEL",
                        (dialog, id) -> dialog.cancel());
        AlertDialog alert = builder.create();
        alert.show();
    }
}

