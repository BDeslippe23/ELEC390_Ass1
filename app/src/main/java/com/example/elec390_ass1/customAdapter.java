package com.example.elec390_ass1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;


public class customAdapter extends ArrayAdapter {

    Context context;
    ArrayList<Course> courseList = new ArrayList<>();
    boolean numVals = true;
    private final String TAG = "__customAdapter";
    public customAdapter(@NonNull Context context, int resource, ArrayList<Course> data, boolean numVals) {
        super(context, resource, data);
        this.context = context;
        this.courseList = data;
        this.numVals = numVals;
    }

    private static class ViewHolder {
        TextView txtCourse;
        TextView txtAssignment;
        TextView txtAverage;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        final View result;
        Course course = courseList.get(position);
        Double average = course.getCourseMark();
        int assSize = course.getAssignments().size();
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.custom_layout, parent, false);
            viewHolder.txtCourse = (TextView) convertView.findViewById(R.id.textView_CourseTitle);
            viewHolder.txtAssignment = (TextView) convertView.findViewById(R.id.textView_Assignments);
            viewHolder.txtAverage = (TextView) convertView.findViewById(R.id.textView_Average);

            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }


        viewHolder.txtCourse.setText(course.getCourseTitle());
        if (numVals) {
            String assString = course.getAssignments().get(0).getAssignmentTitle()
                    + ": " + course.getAssignments().get(0).getAssignmentGrade();
            for (int i = 1; i < assSize; i++) {
                assString += "\n" + course.getAssignments().get(i).getAssignmentTitle()
                        + ": " + course.getAssignments().get(i).getAssignmentGrade();
            }
            viewHolder.txtAssignment.setText(assString);
            viewHolder.txtAverage.setText("Average is: " + average);
        }
        else{
            String assString = course.getAssignments().get(0).getAssignmentTitle()
                    + ": " + converToLetterGrade(course.getAssignments().get(0).getAssignmentGrade());
            for (int i = 1; i < assSize; i++) {
                assString += "\n" + course.getAssignments().get(i).getAssignmentTitle()
                        + ": " + converToLetterGrade(course.getAssignments().get(i).getAssignmentGrade());
            }
            viewHolder.txtAssignment.setText(assString);

            viewHolder.txtAverage.setText("Average is: " + converToLetterGrade(average));
        }
        Log.d(TAG,"Assignments.size() = " + course.getAssignments().size());
        return convertView;
    }
    protected String converToLetterGrade(double average){
        if(average > 89){
            return "A";
        }else if(average > 79){
            return "B";
        }else if(average > 69){
            return "C";
        }else if(average > 59){
            return "D";
        }else return "You failed.";
    }
}
