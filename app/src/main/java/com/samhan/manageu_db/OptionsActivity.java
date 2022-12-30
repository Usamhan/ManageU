package com.samhan.manageu_db;



import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class OptionsActivity extends AppCompatActivity {

    mainWishlist wishlist;
    mainTodoList todoList;
    mainExpense mainExpense;
    FirebaseAuth firebaseAuth;

    Button btnWishlist,btnTodo,btnExpense,btnLogOut1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        btnExpense=findViewById(R.id.btnExpenses);
        btnWishlist=findViewById(R.id.btnWishlist);
        btnTodo=findViewById(R.id.btnTodo);
        btnLogOut1=findViewById(R.id.btnLogOut1);


        firebaseAuth=FirebaseAuth.getInstance();

        btnExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                // click();
                mainExpense mainExpense=new mainExpense();
                Intent iNext=new Intent(getApplicationContext(), mainExpense.class);
                startActivity(iNext);
            }
        });

        btnWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainWishlist wishlist=new mainWishlist();
                Intent iNext=new Intent(getApplicationContext(), mainWishlist.class);
                startActivity(iNext);

            }
        });

        btnTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //click();
                mainTodoList todoList=new mainTodoList();
                Intent iNext=new Intent(getApplicationContext(), mainTodoList.class);
                startActivity(iNext);
            }
        });

        btnLogOut1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();

            }
        });


    }

    private void signOut() {
        firebaseAuth.signOut();
        Intent in=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(in);
    }


//    private void signOut() {
//        AlertDialog.Builder builder=new AlertDialog.Builder(getApplicationContext());
//        builder.setTitle("Delete")
//                .setMessage("Are you sure ?")
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        firebaseAuth.signOut();
//                    }
//                })
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.cancel();
//                    }
//                });
//        builder.create().show();
//    }


    void click()

    {
        Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_SHORT).show();
    }
}