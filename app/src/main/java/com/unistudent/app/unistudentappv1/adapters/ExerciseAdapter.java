package com.unistudent.app.unistudentappv1.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.unistudent.app.unistudentappv1.R;
import com.unistudent.app.unistudentappv1.models.Exercise;
import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder>{

    private List<Exercise> exerciseList;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvQuestion;
        TextView tvNumber;
        EditText etAnswer;

        public ViewHolder(View v){
            super(v);
            tvQuestion = (TextView) v.findViewById(R.id.questionText);
            tvNumber  = (TextView) v.findViewById(R.id.numberText);
            etAnswer = (EditText) v.findViewById(R.id.answerText);
        }
    }

    public ExerciseAdapter(List<Exercise> exerciseList){this.exerciseList = exerciseList;}

    public ExerciseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_exercise, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public void onBindViewHolder(ExerciseAdapter.ViewHolder holder, int position){
        Exercise exercise = exerciseList.get(position);
        holder.tvQuestion.setText(exercise.getQuestion());
        holder.tvNumber.setText(exercise.getNumber());
    }
    public int getItemCount(){return exerciseList.size();}

}