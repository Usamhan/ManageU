package com.samhan.manageu_db;



import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class TodoRecyclerAdapter extends RecyclerView.Adapter<TodoRecyclerAdapter.ViewHolder> {


    Context context;
    ArrayList<TodoModel>arrTodo;

    public TodoRecyclerAdapter(Context context,ArrayList<TodoModel>arrTodo) {

        this.context=context;
        this.arrTodo=arrTodo;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.singletask_todo,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtTask.setText(arrTodo.get(position).task);
        holder.txtPriority.setText(arrTodo.get(position).priority);

        holder.llRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.dialog_box_todo);

                EditText edtTask= dialog.findViewById(R.id.edtTask);
                EditText edtPriority=dialog.findViewById(R.id.edtPriority);
                Button btnAction= dialog.findViewById(R.id.btnAction);
                TextView  txtTitle=dialog.findViewById(R.id.txtTitle);

                txtTitle.setText("Update wish");
                btnAction.setText("Update");

                edtTask.setText((arrTodo.get(holder.getAdapterPosition())).task);
                edtPriority.setText((arrTodo.get(holder.getAdapterPosition())).priority);


                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String task="",priority="";

                        if(!edtTask.getText().toString().equals(""))
                            task=edtTask.getText().toString();
                        else
                            Toast.makeText(context, "enter task", Toast.LENGTH_SHORT).show();

                        if(!edtPriority.getText().toString().equals(""))
                            priority=edtPriority.getText().toString();
                        else
                            Toast.makeText(context, "enter priority", Toast.LENGTH_SHORT).show();

                        arrTodo.set(holder.getAdapterPosition(),new TodoModel(task,priority));
                        notifyItemChanged(holder.getAdapterPosition());

                        dialog.dismiss();

                    }
                });

                dialog.show();



            }
        });

        holder.llRow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context)
                        .setTitle("Delete task")
                        .setMessage("Are you sure ?")
                        .setIcon(android.R.drawable.ic_menu_delete)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                arrTodo.remove(holder.getAdapterPosition());
                                notifyItemRemoved(holder.getAdapterPosition());

                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                builder.show();

                return true;
            }
        });


    }

    @Override
    public int getItemCount() {
        return arrTodo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTask,txtPriority,txtDate;
        LinearLayout llRow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTask=itemView.findViewById(R.id.txtTask);
            txtPriority=itemView.findViewById(R.id.txtPriority);
            llRow=itemView.findViewById(R.id.llRow);
        }
    }
}
