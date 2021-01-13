package com.example.mysensorlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    SensorManager sensorManager;
    List<Sensor> sensorList;
    myAdapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensorList=sensorManager.getSensorList(Sensor.TYPE_ALL);
        adapter=new myAdapter(this,sensorList);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }
}
class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {
    private List<Sensor> sensors;
    TextView textView;
    public myAdapter(Context context,List<Sensor> sensorlist) {
        this.sensors = sensorlist;
        textView= ((MainActivity)context).findViewById(R.id.textView);
    }

    @NonNull
    @Override
    public myAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.sensor,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myAdapter.ViewHolder holder, final int position) {
        holder.getTextView().setText(sensors.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(sensors.get(position).toString());
            }
        });

    }

    @Override
    public int getItemCount() {
        return sensors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView sensorName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sensorName=itemView.findViewById(R.id.sensorName);
        }
        public TextView getTextView(){
            return sensorName;
        }
    }
}
