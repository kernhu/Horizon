package cn.walkpast.horizon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Author: Kern
 * Time: 2019/1/14 14:52
 * Description: This is..
 */

public class AboutUsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);

    }
}
