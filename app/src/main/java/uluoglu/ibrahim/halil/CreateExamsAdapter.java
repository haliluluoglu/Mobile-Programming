package uluoglu.ibrahim.halil;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CreateExamsAdapter extends RecyclerView.Adapter<CreateExamsAdapter.ExampleViewHolder> {
    private ArrayList<QuestionItem> exampleList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView sentence;
        public TextView optionA;
        public TextView optionB;
        public TextView optionC;
        public TextView optionD;
        public TextView optionE;


        public ExampleViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            sentence = itemView.findViewById(R.id.sentence);
            optionA = itemView.findViewById(R.id.optionA);
            optionB = itemView.findViewById(R.id.optionB);
            optionC = itemView.findViewById(R.id.optionC);
            optionD = itemView.findViewById(R.id.optionD);
            optionE = itemView.findViewById(R.id.optionE);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public CreateExamsAdapter(ArrayList<QuestionItem> exampleList) {
        this.exampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        QuestionItem currentItem = exampleList.get(position);
        holder.imageView.setImageResource(R.drawable.exam_question);
        holder.sentence.setText(currentItem.getSentenceM());
        holder.optionA.setText(currentItem.getOptionA());
        holder.optionB.setText(currentItem.getOptionB());
        holder.optionC.setText(currentItem.getOptionC());
        holder.optionD.setText(currentItem.getOptionD());
        holder.optionE.setText(currentItem.getOptionE());
    }

    @Override
    public int getItemCount() {
        return exampleList.size();
    }
}