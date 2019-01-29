package cn.walkpast.horizon.competence;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import cn.walkpast.horizon.R;

/**
 * Author: Kern
 * Time: 2019/1/29 14:41
 * Description: This is..
 */

public class FileUploadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_file_upload);
        ButterKnife.bind(this);

    }
}
