package com.unistudent.app.unistudentappv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.unistudent.app.unistudentappv1.adapters.ExerciseAdapter;
import com.unistudent.app.unistudentappv1.models.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ExerciseActivity extends AppCompatActivity {
    private RecyclerView mRecycleView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Exercise> exerciseList;
    private ExerciseAdapter exerciseAdapter;

    String[] questions = {"question 1", "question 2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        Log.d("TAG", "ExerciseActivity.class line : 29");
        mRecycleView = (RecyclerView) findViewById(R.id.recycler_view_exercise);

        if(mRecycleView != null){
            mRecycleView.setHasFixedSize(true);
        }
                // ini yg bikin error
        //mLayoutManager = new GridLayoutManager(this, 2);
        mLayoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(mLayoutManager);

        exerciseList = new ArrayList<>();

        for (int i = 0; i< questions.length; i++){
        Exercise exercise = new Exercise(questions[i]);
        exerciseList.add(exercise);
        }

        exerciseAdapter = new ExerciseAdapter(exerciseList);
        mRecycleView.setAdapter(exerciseAdapter);
        exerciseAdapter.notifyDataSetChanged();
    }
}

