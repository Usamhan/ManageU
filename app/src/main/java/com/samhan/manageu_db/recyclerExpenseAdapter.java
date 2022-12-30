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

import java.util.ArrayList;

public class recyclerExpenseAdapter extends RecyclerView.Adapter<recyclerExpenseAdapter.ViewHolder> {


    Context context;
    ArrayList<expenseModel>arrExpense;

    public recyclerExpenseAdapter(Context context,ArrayList<expenseModel>arrExpense) {

        this.context=context;
        this.arrExpense=arrExpense;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.singel_expense_row,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtAmount.setText(arrExpense.get(position).amount);
        holder.txtNote.setText(arrExpense.get(position).note);

        holder.llRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.dialog_box_expense);

                EditText edtAmount= dialog.findViewById(R.id.edtAmount);
                EditText edtNote=dialog.findViewById(R.id.edtNote);
                Button btnAction= dialog.findViewById(R.id.btnAction);
                TextView  txtTitle=dialog.findViewById(R.id.txtTitle);

                txtTitle.setText("Update wish");
                btnAction.setText("Update");

                edtAmount.setText((arrExpense.get(holder.getAdapterPosition())).amount);
                edtNote.setText((arrExpense.get(holder.getAdapterPosition())).note);

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String amount="",note="";

                        if(!edtAmount.getText().toString().equals(""))
                            amount=edtAmount.getText().toString();
                        else
                            Toast.makeText(context, "enter book name", Toast.LENGTH_SHORT).show();

                        if(!edtNote.getText().toString().equals(""))
                            note=edtNote.getText().toString();
                        else
                            Toast.makeText(context, "enter writer name", Toast.LENGTH_SHORT).show();

                        arrExpense.set(holder.getAdapterPosition(),new expenseModel(amount,note));
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
                        .setTitle("Delete expense")
                        .setMessage("Are you sure ?")
                        .setIcon(android.R.drawable.ic_menu_delete)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                arrExpense.remove(holder.getAdapterPosition());
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
        return arrExpense.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtAmount,txtNote;
        LinearLayout llRow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtAmount=itemView.findViewById(R.id.txtAmount);
            txtNote=itemView.findViewById(R.id.txtNote);
            llRow=itemView.findViewById(R.id.llRow);
        }
    }
}
