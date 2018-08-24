package br.pro.adalto.applista_m171;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListaActivity extends AppCompatActivity {

    private EditText nomeProduto;
    private Button botao;
    private ListView lvLista;
    private List<String> listaProdutos;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        nomeProduto = (EditText) findViewById(R.id.etNome);
        botao =       (Button)   findViewById(R.id.btnAdicionar);
        lvLista =     (ListView) findViewById(R.id.lvLista);

        listaProdutos = new ArrayList<>();
        adapter = new ArrayAdapter(ListaActivity.this ,
                android.R.layout.simple_list_item_1, listaProdutos );
        lvLista.setAdapter( adapter );


        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionar();
            }
        });

        lvLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {

                AlertDialog.Builder alerta =
                        new AlertDialog.Builder(ListaActivity.this);
                alerta.setTitle("Atenção!");
                alerta.setIcon( android.R.drawable.ic_dialog_alert );
                alerta.setMessage("Confirma a exclusão do produto: " +
                    listaProdutos.get( position ) + "?");
                alerta.setNeutralButton("Cancelar", null);
                alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listaProdutos.remove( position );
                        adapter.notifyDataSetChanged();
                    }
                });
                alerta.show();

                return true;
            }
        });

    }

    private void adicionar(){
        String nome = nomeProduto.getText().toString();
        listaProdutos.add( nome );
        adapter.notifyDataSetChanged();
        nomeProduto.setText("");
    }



}




