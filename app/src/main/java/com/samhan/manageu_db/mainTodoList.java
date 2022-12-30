package com.samhan.manageu_db;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class mainTodoList extends AppCompatActivity {

    RecyclerView recylerTodo;
    FloatingActionButton btnOpenDialog;

    ArrayList<TodoModel> arrTodo=new ArrayList<>();
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_todo_list);

        recylerTodo=findViewById(R.id.recylerTodo);
        btnOpenDialog=findViewById(R.id.btnOpenDialog);

       // recylerTodo.setLayoutManager(new LinearLayoutManager(this));

      //  TodoRecyclerAdapter adapter=new TodoRecyclerAdapter(this,arrTodo);
       // recylerTodo.setAdapter(adapter);

       // databaseReference= FirebaseDatabase.getInstance().getReference("Tasks");

        recylerTodo.setHasFixedSize(true);
        recylerTodo.setLayoutManager(new LinearLayoutManager(this));
        TodoRecyclerAdapter adapter=new TodoRecyclerAdapter(this,arrTodo);
        recylerTodo.setAdapter(adapter);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Tasks");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrTodo.clear();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    TodoModel model = dataSnapshot.getValue(TodoModel.class) ;
                    String priority = model.priority ;
                    String task = model.task ;

                    arrTodo.add(dataSnapshot.getValue(TodoModel.class)) ;

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

                Dialog dialog = new Dialog(mainTodoList.this);
                dialog.setContentView(R.layout.dialog_box_todo);

                //EditText edtName,edtNumber;
                // Button btnAction;

                EditText edtTask = (EditText) dialog.findViewById(R.id.edtTask);
                EditText edtPriority = (EditText) dialog.findViewById(R.id.edtPriority);
                Button btnAction = (Button) dialog.findViewById(R.id.btnAction);

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String task = "", priority = "";

                        if (!edtTask.getText().toString().equals(""))
                        {
                            task = edtTask.getText().toString();


                        }
                        else
                            Toast.makeText(getApplicationContext(), "enter task", Toast.LENGTH_SHORT).show();

                        if (!edtPriority.getText().toString().equals(""))
                            priority = edtPriority.getText().toString();
                        else
                            Toast.makeText(getApplicationContext(), "enter priority", Toast.LENGTH_SHORT).show();


                        String key=databaseReference.push().getKey();

                        TodoModel theTask= new TodoModel(task,priority);
                        databaseReference.child(key).setValue(theTask);

                        arrTodo.add(new TodoModel(task,priority));
                        adapter.notifyItemInserted((arrTodo.size()) - 1);
                        recylerTodo.scrollToPosition((arrTodo.size()) - 1);


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