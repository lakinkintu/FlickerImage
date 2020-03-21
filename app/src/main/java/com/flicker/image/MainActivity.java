package com.flicker.image;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.flicker.image.adapter.ImageRecycleAdapter;
import com.flicker.image.model.TagListPhotos;
import com.flicker.image.presenter.TagPresenter;
import com.flicker.image.utility.TagImageViewer;
import com.flicker.image.utility.UtilityConstant;
import com.flicker.image.view.TagView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TagView, ImageRecycleAdapter.TagImageOnclick {

    private RecyclerView imageRecyclerView;
    private EditText tagSearchEdit;
    private LinearLayoutManager linearLayoutManager;
    private ImageRecycleAdapter imageRecycleAdapter;
    private List<TagListPhotos> tagImageList = new ArrayList<>();
    private TagPresenter tagPresenter;
    private int page = UtilityConstant.PAGE;
    private boolean isLoading = UtilityConstant.ISLOADING;
    private String preTag = UtilityConstant.PREVIOUS_TAG;
    private TagImageViewer tagImageViewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageRecyclerView = findViewById(R.id.image_Recycler);
        tagSearchEdit = findViewById(R.id.tagSearchEdit);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        imageRecyclerView.setLayoutManager(linearLayoutManager);
        imageRecyclerView.setHasFixedSize(true);
        tagPresenter = new TagPresenter(this::tagResulet);
        imageRecycleAdapter = new ImageRecycleAdapter(tagImageList, this);
        imageRecyclerView.setAdapter(imageRecycleAdapter);

        tagSearchEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && tagSearchEdit.getText().toString().length() > 2) {
                    UtilityConstant.hideKeyBoard(tagSearchEdit, getApplicationContext());
                    loadImage(tagSearchEdit.getText().toString(), page);
                }
            }
        });
        imageRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    int visibleItemCount = linearLayoutManager.getChildCount();
                    int totalItemCount = linearLayoutManager.getItemCount();
                    int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

                    if (isLoading && tagSearchEdit.getText().toString().length() > 2) {
                        if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {
                            isLoading = false;
                            page++;
                            loadImage(tagSearchEdit.getText().toString(), page);
                        }
                    }
                }
            }
        });

    }

    void loadImage(String searchTag, int page) {
        if (UtilityConstant.isConnected(this)) {
            tagPresenter.callApi(searchTag, page);
        }else {
            UtilityConstant.showMessage(this,getString(R.string.no_connection));
        }
    }

    @Override
    public void tagResulet(List<TagListPhotos> tagImageList) {
        if (preTag != null && !preTag.equals(tagSearchEdit.getText().toString())) {
            this.tagImageList.clear();
        }
        preTag = tagSearchEdit.getText().toString();
        isLoading = true;
        this.tagImageList.addAll(tagImageList);
        imageRecycleAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClickImage(int position) {
        tagImageViewer = new TagImageViewer(this, tagImageList, position);
        tagImageViewer.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        tagImageViewer.dismiss();
    }
}
