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

public class RecyclerBookAdapter_wl extends RecyclerView.Adapter<RecyclerBookAdapter_wl.ViewHolder> {

    Context context;
    ArrayList<BookModel_wl>arrBooks;
    public RecyclerBookAdapter_wl(Context context,ArrayList<BookModel_wl>arrBooks) {

        this.context=context;
        this.arrBooks=arrBooks;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.book_row,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtBook.setText(arrBooks.get(position).bookName);
        holder.txtWriter.setText(arrBooks.get(position).writerName);

        holder.llRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.dialog_box);

                EditText edtBook= dialog.findViewById(R.id.edtBook);
                EditText edtWriter=dialog.findViewById(R.id.edtWriter);
                Button btnAction= dialog.findViewById(R.id.btnAction);
                TextView  txtTitle=dialog.findViewById(R.id.txtTitle);

                txtTitle.setText("Update wish");
                btnAction.setText("Update");

                edtBook.setText((arrBooks.get(holder.getAdapterPosition())).bookName);
                edtWriter.setText((arrBooks.get(holder.getAdapterPosition())).writerName);

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String book="",writerName="";

                        if(!edtBook.getText().toString().equals(""))
                            book=edtBook.getText().toString();
                        else
                            Toast.makeText(context, "enter book name", Toast.LENGTH_SHORT).show();

                        if(!edtWriter.getText().toString().equals(""))
                            writerName=edtWriter.getText().toString();
                        else
                            Toast.makeText(context, "enter writer name", Toast.LENGTH_SHORT).show();

                        arrBooks.set(holder.getAdapterPosition(),new BookModel_wl(book,writerName));
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
                        .setTitle("Delete wish")
                        .setMessage("Are you sure ?")
                        .setIcon(android.R.drawable.ic_menu_delete)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                arrBooks.remove(holder.getAdapterPosition());
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
        return arrBooks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtBook,txtWriter;
        LinearLayout llRow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtBook=itemView.findViewById(R.id.txtBook);
            txtWriter=itemView.findViewById(R.id.txtWriter);
            llRow=itemView.findViewById(R.id.llRow);
        }
    }
}


