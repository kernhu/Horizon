package cn.walkpast.horizon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Author: Kern
 * Time: 2019/2/13 11:00
 * Description: This is..
 */

public class SponsorActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sponsor);
        ButterKnife.bind(this);

    }
}
