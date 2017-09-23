package com.example.kapusta.elements2;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kapusta.elements2.ElementInterface.ElementListener;



public class MainActivity extends AppCompatActivity {

//    @BindView(R2.id.rv)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);

        getFragmentManager().beginTransaction().add(R.id.fragm_container, new ElemFragment()).commit();



    }

}
