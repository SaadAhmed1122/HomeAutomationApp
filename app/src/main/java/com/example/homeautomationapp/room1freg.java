package com.example.homeautomationapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class room1freg extends Fragment {
    private Switch bulbswitch,fanswitch;
    DatabaseReference reference;
    Context context;

    public room1freg(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View aa= inflater.inflate(R.layout.room1,container,false);
        bulbswitch = aa.findViewById(R.id.bulbswitch);
        fanswitch = aa.findViewById(R.id.fanswitch);

        reference= FirebaseDatabase.getInstance().getReference("Home");

        setswitches();

        bulbswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bulbswitch.isChecked()) {
                    reference = FirebaseDatabase.getInstance().getReference("Home").child("room1");
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("Light", "1");

                    reference.updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            //Update Successfully
                            Toast.makeText(context, "Light Opened", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(UserConttol.this, "Updation Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{
                    reference = FirebaseDatabase.getInstance().getReference("Home").child("room1");
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("Light", "0");

                    reference.updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            //Update Successfully
                            Toast.makeText(context, "Light Closed", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(UserConttol.this, "Updation Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });



        fanswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fanswitch.isChecked()) {
                    reference = FirebaseDatabase.getInstance().getReference("Home").child("room1");
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("fan", "1");

                    reference.updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            //Update Successfully
                            Toast.makeText(context, "Fan Opened", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(UserConttol.this, "Updation Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{
                    reference = FirebaseDatabase.getInstance().getReference("Home").child("room1");
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("fan", "0");

                    reference.updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            //Update Successfully
                            Toast.makeText(context, "Fan Closed", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(UserConttol.this, "Updation Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        return aa;
    }

    private void setswitches() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean mBool = true;
                boolean mboolfalse = false;

                String lightvalue = snapshot.child("room1").child("Light").getValue().toString();
                String fanvalue = snapshot.child("room1").child("fan").getValue().toString();

                if (lightvalue.equals("1")) {
                    bulbswitch.setChecked(mBool);
                }
                else {
                    bulbswitch.setChecked(mboolfalse);
                }
                if(fanvalue.equals("0")){
                    fanswitch.setChecked(mboolfalse);
                }
                else {
                    fanswitch.setChecked(mBool);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
