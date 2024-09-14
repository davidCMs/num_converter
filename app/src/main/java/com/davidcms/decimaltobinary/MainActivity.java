package com.davidcms.decimaltobinary;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import org.davidCMs.Base;
import org.davidCMs.ConversionUtils;
/**
 * The Main activity that displays the ui on android
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Base.getBases();

        //layout
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        //dropdown options
        Base[] dropdownOptions = Base.getBases().toArray(new Base[0]);
        ArrayAdapter<Base> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dropdownOptions);

        //convert from selection
        LinearLayout convertFromLayout = new LinearLayout(this);
        convertFromLayout.setOrientation(LinearLayout.HORIZONTAL);
        convertFromLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 64));

        TextView convertFrom = new TextView(this);
        convertFrom.setText("Convert From");
        convertFromLayout.addView(convertFrom);

        Spinner convertFromDropdown = new Spinner(this);
        convertFromDropdown.setAdapter(adapter);
        convertFromLayout.addView(convertFromDropdown);

        layout.addView(convertFromLayout);

        //enter number to convert text
        TextView numPrompt = new TextView(this);
        numPrompt.setText("Enter number to convert");
        layout.addView(numPrompt);

        //input text field
        EditText input = new EditText(this);
        input.setText("0");
        layout.addView(input);

        //convert to selection
        LinearLayout convertToLayout = new LinearLayout(this);
        convertToLayout.setOrientation(LinearLayout.HORIZONTAL);
        convertToLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 64));

        TextView convertTo = new TextView(this);
        convertTo.setText("Convert To");
        convertToLayout.addView(convertTo);

        Spinner convertToDropdown = new Spinner(this);
        convertToDropdown.setAdapter(adapter);
        convertToLayout.addView(convertToDropdown);

        layout.addView(convertToLayout);

        //Converted output text
        TextView outNumPrompt = new TextView(this);
        outNumPrompt.setText("Converted output: ");
        layout.addView(outNumPrompt);

        //output text
        TextView output = new TextView(this);
        output.setText("0");
        layout.addView(output);

        setContentView(layout);

        AdapterView.OnItemSelectedListener selectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                output.setText(ConversionUtils.convert((Base) convertFromDropdown.getSelectedItem(), (Base) convertToDropdown.getSelectedItem(), input.getText().toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        convertFromDropdown.setOnItemSelectedListener(selectedListener);
        convertToDropdown.setOnItemSelectedListener(selectedListener);

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                output.setText(ConversionUtils.convert((Base) convertFromDropdown.getSelectedItem(), (Base) convertToDropdown.getSelectedItem(), input.getText().toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}