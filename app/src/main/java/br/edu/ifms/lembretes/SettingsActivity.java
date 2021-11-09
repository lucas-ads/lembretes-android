package br.edu.ifms.lembretes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editNome;
    private Switch switchEconomia;
    private Switch switchTemaEscuro;
    private Button buttonSalvarConfigs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        editNome = findViewById(R.id.editNome);
        switchEconomia = findViewById(R.id.switchEconomia);
        switchTemaEscuro = findViewById(R.id.switchTemaEscuro);
        buttonSalvarConfigs = findViewById(R.id.buttonSalvarConfigs);

        buttonSalvarConfigs.setOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        editNome.setText( sp.getString("nomePref","") );
        switchEconomia.setChecked( sp.getBoolean("economiaDados", true) );
        switchTemaEscuro.setChecked( sp.getBoolean("temaEscuro", false) );
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.buttonSalvarConfigs){
            String nome = editNome.getText().toString();
            boolean economia = switchEconomia.isChecked();
            boolean temaEscuro = switchTemaEscuro.isChecked();

            SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("nomePref",nome);
            editor.putBoolean("economiaDados", economia);
            editor.putBoolean("temaEscuro", temaEscuro);

            editor.commit();

            Toast.makeText(this, getText(R.string.txtConfsGravadas),Toast.LENGTH_LONG).show();
        }
    }
}









