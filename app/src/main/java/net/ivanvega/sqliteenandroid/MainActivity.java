package net.ivanvega.sqliteenandroid;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import net.ivanvega.sqliteenandroid.db.DAOUsuarios;
import net.ivanvega.sqliteenandroid.db.MiAdaptadorUsuariosConexion;
import net.ivanvega.sqliteenandroid.db.Usuario;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText txtN,txtE;
    ListView lsv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtN = findViewById(R.id.txtNombre);
        txtE = findViewById(R.id.txtEmail);

         lsv = findViewById(R.id.lsv);

    }

    public void btnI_click(View v){
        DAOUsuarios ado =
                new DAOUsuarios(getApplicationContext());

        long result = ado.add(
                new Usuario(
                    0, txtN.getText().toString(), "4451022214",
                        txtE.getText().toString(), "@"+txtN.getText().toString()
                )
        );

        if (result>0){
            Toast.makeText(this, "Adición exitosa",
                    Toast.LENGTH_LONG).show();
        }

    }

    public void btnCargar_click(View v){
        DAOUsuarios dao = new DAOUsuarios(this);
        List<Usuario> lst =  dao.getAll();
        for (Usuario item: lst
             ) {
            Log.d("USUARIO: " ,  String.valueOf( item.getId()));
            Log.d("USUARIO: " , item.getNombre());
        }
        Cursor c =  dao.getAllC();



        SimpleCursorAdapter adp = new SimpleCursorAdapter(
          this, android.R.layout.simple_list_item_2 ,
          c , MiAdaptadorUsuariosConexion.COLUMNS_USUARIOS,
          new int[]{android.R.id.text1, android.R.id.text2},
                SimpleCursorAdapter.NO_SELECTION

        );

        lsv.setAdapter(adp);
    }

}
