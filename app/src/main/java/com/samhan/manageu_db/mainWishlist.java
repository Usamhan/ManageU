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

import java.util.ArrayList;

public class mainWishlist extends AppCompatActivity {
    FloatingActionButton btnOpenDialog;
    ArrayList<BookModel_wl>arrBooks=new ArrayList<>();
    RecyclerBookAdapter_wl adapter;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_wishlist2);

        RecyclerView  recyclerView=findViewById(R.id.recyclerBooks);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

       // databaseReference= FirebaseDatabase.getInstance().getReference("Wishes");

        btnOpenDialog=findViewById(R.id.btnOpenDialog);
       // RecyclerBookAdapter_wl adapter=new RecyclerBookAdapter_wl(this,arrBooks);
       // recyclerView.setAdapter(adapter);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerBookAdapter_wl adapter=new RecyclerBookAdapter_wl(this,arrBooks);
        recyclerView.setAdapter(adapter);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Wishes");
        databaseReference.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrBooks.clear();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    BookModel_wl model = dataSnapshot.getValue(BookModel_wl.class) ;
                    String bookName = model.bookName ;
                    String writerName = model.writerName ;

                    arrBooks.add(dataSnapshot.getValue(BookModel_wl.class)) ;

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

                Dialog dialog = new Dialog(mainWishlist.this);
                dialog.setContentView(R.layout.dialog_box);

                //EditText edtName,edtNumber;
                // Button btnAction;

                EditText edtBook = (EditText) dialog.findViewById(R.id.edtBook);
                EditText edtWriter = (EditText) dialog.findViewById(R.id.edtWriter);
                Button btnAction = (Button) dialog.findViewById(R.id.btnAction);

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String Book = "", writer = "";

                        if (!edtBook.getText().toString().equals(""))
                            Book = edtBook.getText().toString();
                        else
                            Toast.makeText(mainWishlist.this, "enter book name", Toast.LENGTH_SHORT).show();

                        if (!edtWriter.getText().toString().equals(""))
                            writer = edtWriter.getText().toString();
                        else
                            Toast.makeText(mainWishlist.this, "enter writer's name", Toast.LENGTH_SHORT).show();

                        String key=databaseReference.push().getKey();

                        BookModel_wl theBooks= new BookModel_wl(Book,writer);
                        databaseReference.child(key).setValue(theBooks);

                        arrBooks.add(theBooks);
                        adapter.notifyItemInserted((arrBooks.size()) - 1);
                        recyclerView.scrollToPosition((arrBooks.size()) - 1);


                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });
//        arrBooks.add(new BookModel_wl("A","def"));
//        arrBooks.add(new BookModel_wl("B","def"));
//        arrBooks.add(new BookModel_wl("C","def"));
//        arrBooks.add(new BookModel_wl("D","def"));
//        arrBooks.add(new BookModel_wl("E","def"));
//        arrBooks.add(new BookModel_wl("F","def"));
//        arrBooks.add(new BookModel_wl("G","def"));
//        arrBooks.add(new BookModel_wl("I","def"));
//        arrBooks.add(new BookModel_wl("J","def"));
//        arrBooks.add(new BookModel_wl("K","def"));
//        arrBooks.add(new BookModel_wl("L","def"));





    }
}
