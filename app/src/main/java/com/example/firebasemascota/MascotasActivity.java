package com.example.firebasemascota;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MascotasActivity extends AppCompatActivity {

    private EditText nom_mascota;
    private EditText raza_mascota;
    Button save;
    FirebaseFirestore mymascota ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas);
        nom_mascota=findViewById(R.id.id_nombre_mascota);
        raza_mascota=findViewById(R.id.id_raza_mascota);
        save=findViewById(R.id.id_guardarMascota);
         mymascota = FirebaseFirestore.getInstance();
    }

    public void guardarMascota(View view)
    {
        String nom=nom_mascota.getText().toString();
        String raz=raza_mascota.getText().toString();

        if(nom.equals(""))
        {
            nom_mascota.setError("Escriba nombre de la mascota");
        }
        if(raz.equals(""))
        {
            raza_mascota.setError("Escriba la raza  es Obligatorio");
        }


        nom_mascota.setText(""); /*estas son las variables del EditText*/
        raza_mascota.setText("");

        // Toast.makeText(this,"registro quedo guardado",Toast.LENGTH_SHORT).show();
        Map<String,Object> map = new HashMap<>();
        map.put("nom_mascota",nom);
        map.put("raza_mascota",raz);



        mymascota.collection("Mascotas")
                .add(map)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(),"Mascota quedo registrada", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}