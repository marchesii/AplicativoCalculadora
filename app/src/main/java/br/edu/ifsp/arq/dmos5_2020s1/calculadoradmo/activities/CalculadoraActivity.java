package br.edu.ifsp.arq.dmos5_2020s1.calculadoradmo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.ifsp.arq.dmos5_2020s1.calculadoradmo.R;
import br.edu.ifsp.arq.dmos5_2020s1.calculadoradmo.constants.Constantes;
import br.edu.ifsp.arq.dmos5_2020s1.calculadoradmo.model.Calculadora;

public class CalculadoraActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textview_output;
    private Button button_zerar;
    private Button button_multiplicar;
    private Button button_dividir;
    private Button button_somar;
    private Button button_subtrair;
    private Button button_calcular;
    private Button button_ponto;
    private Button button_limpar;
    private Button button_potencia;
    private Button _0;
    private Button _1;
    private Button _2;
    private Button _3;
    private Button _4;
    private Button _5;
    private Button _6;
    private Button _7;
    private Button _8;
    private Button _9;
    private int botoes[];
    private String tela;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        Calculadora.getInstance();
        button_zerar = findViewById(R.id.zerar_button);
        button_multiplicar = findViewById(R.id.multiplicar_button);
        button_dividir = findViewById(R.id.dividir_button);
        button_somar = findViewById(R.id.somar_button);
        button_subtrair = findViewById(R.id.subtrair_button);
        button_calcular = findViewById(R.id.igual_button);
        textview_output = findViewById(R.id.output_textview);
        button_ponto = findViewById(R.id.ponto_button);
        button_limpar = findViewById(R.id.limpar_button);
        button_potencia = findViewById(R.id.potencia_button);
        botoes = new int[]{R.id.zero_button, R.id.um_button, R.id.dois_button, R.id.tres_button, R.id.quatro_button, R.id.cinco_button, R.id.seis_button, R.id.sete_button,R.id.oito_button, R.id.nove_button};

        for(int id : botoes){
            View v = findViewById(id);
            v.setOnClickListener(this);
        }

        button_limpar.setOnClickListener(this);
        button_potencia.setOnClickListener(this);
        button_ponto.setOnClickListener(this);
        button_calcular.setOnClickListener(this);
        button_subtrair.setOnClickListener(this);
        button_somar.setOnClickListener(this);
        button_dividir.setOnClickListener(this);
        button_multiplicar.setOnClickListener(this);
        button_zerar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zerar_button:
                zerar();
                break;
            case R.id.multiplicar_button:
                contar(Constantes.MULTIPLICACAO);
                break;
            case R.id.dividir_button:
                contar(Constantes.DIVISAO);
                break;
            case R.id.somar_button:
                contar(Constantes.ADICAO);
                break;
            case R.id.subtrair_button:
                contar(Constantes.SUBTRACAO);
                break;
            case R.id.igual_button:
                contar(Constantes.RESULTADO);
                contar(Constantes.NULO);
                break;
            case R.id.ponto_button:
                atualizar(getString(R.string._ponto));
                break;
            case R.id.zero_button:
                atualizar(getString(R.string._0));
                break;
            case R.id.um_button:
                atualizar(getString(R.string._1));
                break;
            case R.id.dois_button:
                atualizar(getString(R.string._2));
                break;
            case R.id.tres_button:
                atualizar(getString(R.string._3));
                break;
            case R.id.quatro_button:
                atualizar(getString(R.string._4));
                break;
            case R.id.cinco_button:
                atualizar(getString(R.string._5));
                break;
            case R.id.seis_button:
                atualizar(getString(R.string._6));
                break;
            case R.id.sete_button:
                atualizar(getString(R.string._7));
                break;
            case R.id.oito_button:
                atualizar(getString(R.string._8));
                break;
            case R.id.nove_button:
                atualizar(getString(R.string._9));
                break;
            case R.id.potencia_button:
                contar(Constantes.POTENCIA);
                break;
            case R.id.limpar_button:
                limpar();
                textview_output.setText(String.format("%.2f", Float.parseFloat(tela)));
        }
    }

    private void zerar() {
        Calculadora.getInstance().c();
        limpar();
        textview_output.setText(String.format("%.2f", Calculadora.getInstance().calcular(Constantes.NULO, (Float.parseFloat(tela)))));
    }

    private void limpar() {
        tela = getString(R.string._0);
    }

    private void atualizar(String numero){
        if(tela == null || tela.equals(getString(R.string._0))){
            tela = (numero);
        } else {
            tela = tela+numero;
        }
        textview_output.setText(tela);
    }

    private void contar(int constante){
        try{
            if(tela == null || tela.equals(getString(R.string._ponto))){
                atualizar(getString((R.string._0)));
            }
            if(tela.equals(getString(R.string._0))){
                tela = String.valueOf(textview_output.getText());
            }
            tela = String.valueOf(Calculadora.getInstance().calcular(constante, Float.parseFloat(tela)));
        } catch (NumberFormatException ex){
            zerar();
            Toast.makeText(this, R.string.format_Error_pontos, Toast.LENGTH_SHORT).show();
        }

        textview_output.setText(tela);
        tela = getString(R.string._0);
    }
}
