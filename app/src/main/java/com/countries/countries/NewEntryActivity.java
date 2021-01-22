package com.countries.countries;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class NewEntryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);
        setTitle("Entry");

        // Issitraukiame konkretu irasa is paspaustos korteles.
        Intent intent = getIntent();
        Countries countries = (Countries) intent.getSerializableExtra(Adapter.ENTRY);

        // Pasemame is vaizdo visus elementus su kuriais dirbsime.
        final CheckBox checkBoxNorway = findViewById(R.id.country_norway);
        final CheckBox checkBoxSweden = findViewById(R.id.country_sweden);
        final CheckBox checkBoxDenmark = findViewById(R.id.country_denmark);
        final CheckBox checkBoxFinland = findViewById(R.id.country_finland);

        final RadioGroup groupCapital = findViewById(R.id.country_capital);
        RadioButton button2 = findViewById(R.id.capital);

        final Spinner spinnerTimezone = findViewById(R.id.timezone);
        ArrayList<String> timezoneList = new ArrayList<String>();
        timezoneList.add(countries.getTimezone());
        timezoneList.add(getResources().getString(R.string.new_entry__country_timezone_1));
        timezoneList.add(getResources().getString(R.string.new_entry__country_timezone_2));
        timezoneList.add(getResources().getString(R.string.new_entry__country_timezone_3));
        // Adapteris reikalingas sujungti, isdestyma su stringArray.
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                timezoneList
        );
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Adapteri idedame i musu spinneri.
        spinnerTimezone.setAdapter(dataAdapter);

        final EditText editTextName = findViewById(R.id.country_input);
        final EditText editTextPopulation = findViewById(R.id.country_population);


        Button buttonDisplaySelected = findViewById(R.id.display_selected_btn);

//        // Uzpildome elementus (Countries) informacija. ????????????????????????????

        checkBoxNorway.setText(countries.getRegion());
        button2.setText(String.valueOf(countries.getCapital()));
        editTextName.setText(String.valueOf(countries.getName()));
        editTextPopulation.setText(String.valueOf(countries.getPopulation()));


//        // Ant mygtuko paspaudimo parodyti visa vartotojo ivesta informacija.
        buttonDisplaySelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String allCountries = "";
                if (checkBoxNorway.isChecked()) {
                    allCountries += checkBoxNorway.getText().toString() + " ";
                }
                if (checkBoxSweden.isChecked()) {
                    allCountries += checkBoxSweden.getText().toString() + " ";
                }
                if (checkBoxDenmark.isChecked()) {
                    allCountries += checkBoxDenmark.getText().toString() + " ";
                }
                if (checkBoxFinland.isChecked()) {
                    allCountries += checkBoxFinland.getText().toString() + " ";
                }

//                // get selected radio button from radioGroup
                int selectedId = groupCapital.getCheckedRadioButtonId();
//                // find the radiobutton by returned id
                RadioButton selectedButton = (RadioButton) findViewById(selectedId);
                String name = editTextName.getText().toString();
                String capital = selectedButton.getText().toString();
                String timezone = String.valueOf(spinnerTimezone.getSelectedItem());
                String population = editTextPopulation.getText().toString();

                editTextName.setError(null);
                editTextPopulation.setError(null);
                if (Validation.isValidPopulation(population)){
                    // Sukuriamas korona objektas is GUI elementu.

                    Countries countries = new Countries(allCountries, name, capital, Integer.parseInt(population), timezone);

//                    // Atvaizduojamas vartotojui objekto informacija.
                    Toast.makeText(
                            NewEntryActivity.this,
                            "Country(-ies): " + countries.getCapital() + "\n " +
                                    "Capital: " + countries.getRegion() + "\n " +
                                    "Region: " + countries.getName() + "\n " +
                                    "Population: " + countries.getPopulation() + "\n " +
                                    "Timezone: " + countries.getTimezone(),
                            Toast.LENGTH_SHORT
                    ).show();
                } else { // blogai įvesti confirmed duomenys
                    editTextPopulation.setError(getResources().getString(R.string.new_entry_invalid_code));
                    editTextPopulation.requestFocus();
                }
            }
        });
    }
}
