package com.samhan.manageu_db;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class mainExpense extends AppCompatActivity {

    RecyclerView recyclerExpense;
    FloatingActionButton btnOpenDialog;
    DatabaseReference databaseReference;

    ArrayList<expenseModel> arrExpense=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_expense);

        //txtTotalExpense=findViewById(R.id.txtTotalExpense);
        recyclerExpense=findViewById(R.id.recylerExpense);
        btnOpenDialog=findViewById(R.id.btnOpenDialog);

       // recyclerExpense.setLayoutManager(new LinearLayoutManager(this));

      //  recyclerExpenseAdapter adapter=new recyclerExpenseAdapter(this,arrExpense);
        //recyclerExpense.setAdapter(adapter);



       // databaseReference= FirebaseDatabase.getInstance().getReference("Expenses");
        //nayem er part
        recyclerExpense.setHasFixedSize(true);
        recyclerExpense.setLayoutManager(new LinearLayoutManager(this));
        recyclerExpenseAdapter adapter=new recyclerExpenseAdapter(this,arrExpense);
        recyclerExpense.setAdapter(adapter);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Expenses");
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrExpense.clear();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    expenseModel model = dataSnapshot.getValue(expenseModel.class) ;
                    String amount = model.amount ;
                    String note = model.note ;

                    arrExpense.add(dataSnapshot.getValue(expenseModel.class)) ;

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dialog = new Dialog(mainExpense.this);
                dialog.setContentView(R.layout.dialog_box_expense);

                //EditText edtName,edtNumber;
                // Button btnAction;

                EditText edtAmount = (EditText) dialog.findViewById(R.id.edtAmount);
                EditText edtNote = (EditText) dialog.findViewById(R.id.edtNote);
                Button btnAction = (Button) dialog.findViewById(R.id.btnAction);



                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String Amount = "", Note = "";

                        if (!edtAmount.getText().toString().equals(""))
                        {
                            Amount = edtAmount.getText().toString();


                        }
                        else
                            Toast.makeText(getApplicationContext(), "enter amount", Toast.LENGTH_SHORT).show();

                        if (!edtNote.getText().toString().equals(""))
                            Note = edtNote.getText().toString();
                        else
                            Toast.makeText(getApplicationContext(), "enter note", Toast.LENGTH_SHORT).show();


                       String key=databaseReference.push().getKey();

                       expenseModel theExpense= new expenseModel(Amount,Note);
                       databaseReference.child(key).setValue(theExpense);

                        arrExpense.add(new expenseModel(Amount, Note));
                        //arrExpense.add(theExpense);
                        adapter.notifyItemInserted((arrExpense.size()) - 1);
                        recyclerExpense.scrollToPosition((arrExpense.size()) - 1);


                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });

        //txtTotalExpense.setText(String.valueOf(total));


       /* arrExpense.add(new expenseModel("A","def",currentDate));
        arrExpense.add(new expenseModel("B","def",currentDate));
        arrExpense.add(new expenseModel("C","def",currentDate));
        arrExpense.add(new expenseModel("D","def",currentDate));
        arrExpense.add(new expenseModel("E","def",currentDate));
        arrExpense.add(new expenseModel("F","def",currentDate));
        arrExpense.add(new expenseModel("G","def",currentDate));
        arrExpense.add(new expenseModel("I","def",currentDate));
        arrExpense.add(new expenseModel("J","def",currentDate));
        arrExpense.add(new expenseModel("K","def",currentDate));
        arrExpense.add(new expenseModel("L","def",currentDate));
*/

    }
}