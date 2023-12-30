package digi.coders.regform;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

   static RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        recyclerView = findViewById(R.id.recycler);
        showDatafirebase();

    }

    public static void showDatafirebase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Student Data");

        reference.addValueEventListener(new ValueEventListener() {
            List<DataModel> dataModelList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Log.d("DatabaseReference", "snapshot exists");

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        DataModel dataModel = dataSnapshot.getValue(DataModel.class);
                        dataModelList.add(dataModel);
                    }

                    Adapter adapter = new Adapter(recyclerView.getContext(), dataModelList);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(),LinearLayoutManager.VERTICAL, false));
                } else {
                    Log.d("DatabaseReference", "snapshot not exists");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("DatabaseReference", "onCancelled" + error.getMessage());
            }
        });
    }



}