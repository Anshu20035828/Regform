package digi.coders.regform;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    List<DataModel> dataModelList;

    public Adapter(Context context, List<DataModel> dataModelList) {
        this.context = context;
        this.dataModelList = dataModelList;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        DataModel dataModel = dataModelList.get(position);
        holder.sfname.setText("First Name : "+dataModel.getFname());
        holder.ssnmae.setText("Last Name : "+dataModel.getSname());
        holder.semail.setText("Email : "+dataModel.getEemail());
        holder.spassword.setText("Password : "+dataModel.getPpassword());
        holder.sdob.setText("Date Of Birth : "+dataModel.getDdob());
        holder.sfcheck.setText("Hobbies :  "+dataModel.getF_check());
        holder.sscheck.setText("  "+dataModel.getS_check());
        holder.stcheck.setText("  "+dataModel.getT_check());
        holder.sradio.setText("Gender : "+dataModel.getR_radio());
        holder.snumber.setText("Mobile No : "+dataModel.getMobileNo());

        holder.dataUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(context, MainActivity.class);
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                a.putExtra("flag","UPDATE");
                a.putExtra("number",dataModel.getMobileNo());
                context.startActivity(a);
            }
        });

        holder.dataDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Student Data");
                String num = dataModel.getMobileNo();
                databaseReference.child(num).removeValue();
                SecondActivity.showDatafirebase();
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView sfname,ssnmae,semail,spassword,sdob,sradio,sfcheck,sscheck,stcheck,snumber;

        ImageView dataUpdate, dataDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            sfname = itemView.findViewById(R.id.showfname);
            ssnmae = itemView.findViewById(R.id.showlname);
            semail = itemView.findViewById(R.id.showemail);
            spassword = itemView.findViewById(R.id.showpassword);
            sdob = itemView.findViewById(R.id.showdob);
            sradio = itemView.findViewById(R.id.showngender);
            sfcheck = itemView.findViewById(R.id.showcheck1);
            sscheck = itemView.findViewById(R.id.showcheck2);
            stcheck = itemView.findViewById(R.id.showcheck3);
            snumber = itemView.findViewById(R.id.shownnumber);

            dataUpdate = itemView.findViewById(R.id.update);
            dataDelete = itemView.findViewById(R.id.delete);
        }
    }
}
