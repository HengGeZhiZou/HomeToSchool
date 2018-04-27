package com.hts.hometoschool.modules.InfoModule;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hts.hometoschool.R;

public class InfoFragment extends Fragment{
    private RelativeLayout infoRelativeLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.info_fragment,container,false);
        infoRelativeLayout=view.findViewById(R.id.updateInfo);
        infoRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),UpdateInfoActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
