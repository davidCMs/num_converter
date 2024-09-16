package com.davidcms.decimaltobinary;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import org.davidCMs.Base;
import org.davidCMs.ConversionUtils;
/**
 * The Main activity that displays the ui on android
 *
 * @author davidCMs
 * @see Base
 * @see ConversionUtils
 * @since 1.0
 */
public class MainActivity extends AppCompatActivity {

    public static final String preferencesFile = "NumberConverter_preferences";

    private SharedPreferences sharedPreferences;
    public boolean extendedBaseSet;

    ArrayAdapter<Base> basesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Base.getBases();

        sharedPreferences = getSharedPreferences(preferencesFile, MODE_PRIVATE);
        try {
            extendedBaseSet = sharedPreferences.getBoolean("extendedBases", false);
        } catch (NullPointerException e) {
            extendedBaseSet = false;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("extendedBases", false);
            editor.apply();
        }

        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        LinearLayout topLayout = new LinearLayout(this);
        topLayout.setOrientation(LinearLayout.HORIZONTAL);
        topLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        //layout
        LinearLayout converterLayout = new LinearLayout(this);
        converterLayout.setOrientation(LinearLayout.VERTICAL);
        converterLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(20, 10, 20, 10);

        //main converterLayout gradient
        GradientDrawable layoutGradient = new GradientDrawable();
        layoutGradient.setShape(GradientDrawable.RECTANGLE);
        layoutGradient.setCornerRadius(20);
        layoutGradient.setStroke(5, Color.BLACK);
        layoutGradient.setColor(Color.LTGRAY);
        topLayout.setBackground(layoutGradient);
        converterLayout.setBackground(layoutGradient);

        GradientDrawable buttonDrawable = new GradientDrawable();
        buttonDrawable.setShape(GradientDrawable.RECTANGLE);
        buttonDrawable.setColor(Color.CYAN);
        buttonDrawable.setAlpha(200);
        buttonDrawable.setSize(20, 100);
        buttonDrawable.setCornerRadius(30);
        buttonDrawable.setStroke(5, Color.BLACK);

        LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(
                0,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1);

        TextView title = new TextView(this);
        title.setText("Number Converter");
        topLayout.addView(title);

        LinearLayout topButtonsLayout = new LinearLayout(this);
        topButtonsLayout.setOrientation(LinearLayout.HORIZONTAL);
        topButtonsLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));

        Button infoButton = new Button(this);
        infoButton.setText("i");
        infoButton.setBackground(buttonDrawable);
        infoButton.setPadding(5,0,5,10);
        infoButton.setTransformationMethod(null);
        infoButton.setLayoutParams(buttonLayoutParams);
        topButtonsLayout.addView(infoButton);

        Button helpButton = new Button(this);
        helpButton.setText("?");
        helpButton.setBackground(buttonDrawable);
        helpButton.setPadding(5,0,5,10);
        helpButton.setTransformationMethod(null);
        helpButton.setLayoutParams(buttonLayoutParams);
        topButtonsLayout.addView(helpButton);

        Button settingsButton = new Button(this);
        settingsButton.setText("settings");
        settingsButton.setBackground(buttonDrawable);
        settingsButton.setPadding(5,0,5,0);
        settingsButton.setTransformationMethod(null);
        settingsButton.setLayoutParams(buttonLayoutParams);
        topButtonsLayout.addView(settingsButton);

        topLayout.addView(topButtonsLayout);

        //dropdown options
        setDropdownMenu();

        //enter number to convert text
        TextView numPrompt = new TextView(this);
        numPrompt.setText("Enter number to convert");
        converterLayout.addView(numPrompt);

        //input text field
        EditText input = new EditText(this);
        input.setText("0");
        input.setMovementMethod(new ScrollingMovementMethod());
        input.setVerticalScrollBarEnabled(true);
        input.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET);
        input.setMaxLines(9);
        input.setOverScrollMode(View.OVER_SCROLL_IF_CONTENT_SCROLLS);
        GradientDrawable inputGradient = new GradientDrawable();
        inputGradient.setShape(GradientDrawable.RECTANGLE);
        inputGradient.setCornerRadius(10);
        inputGradient.setTint(Color.WHITE);
        inputGradient.setStroke(5, Color.BLACK);
        inputGradient.setColor(Color.WHITE);
        input.setBackground(inputGradient);
        input.setPadding(10,5,10,5);
        converterLayout.addView(input);

        //convert from selection
        LinearLayout convertFromLayout = new LinearLayout(this);
        convertFromLayout.setOrientation(LinearLayout.HORIZONTAL);
        convertFromLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 64));

        TextView convertFrom = new TextView(this);
        convertFrom.setText("Convert From");
        convertFromLayout.addView(convertFrom);

        Spinner convertFromDropdown = new Spinner(this);
        convertFromDropdown.setAdapter(basesAdapter);
        convertFromDropdown.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 64));
        convertFromLayout.addView(convertFromDropdown);

        converterLayout.addView(convertFromLayout);

        //divider
        View divider = new View(this);
        LinearLayout.LayoutParams dividerParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                8
        );
        dividerParams.setMargins(10, 20, 10, 20);
        divider.setLayoutParams(dividerParams);
        divider.setBackgroundColor(Color.GRAY);
        converterLayout.addView(divider);

        //convert to selection
        LinearLayout convertToLayout = new LinearLayout(this);
        convertToLayout.setOrientation(LinearLayout.HORIZONTAL);
        convertToLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 64));

        TextView convertTo = new TextView(this);
        convertTo.setText("Convert To");
        convertToLayout.addView(convertTo);

        Spinner convertToDropdown = new Spinner(this);
        convertToDropdown.setAdapter(basesAdapter);
        convertToDropdown.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 64));
        convertToLayout.addView(convertToDropdown);

        converterLayout.addView(convertToLayout);

        //Converted output text
        TextView outNumPrompt = new TextView(this);
        outNumPrompt.setText("Converted output: ");
        converterLayout.addView(outNumPrompt);

        //output text
        TextView output = new TextView(this);
        output.setText("0");
        output.setPadding(10,5,10,5);

        output.setMovementMethod(new ScrollingMovementMethod());
        output.setVerticalScrollBarEnabled(true);
        output.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET);

        output.setMaxLines(9);
        output.setOverScrollMode(View.OVER_SCROLL_IF_CONTENT_SCROLLS);
        GradientDrawable outputGradient = new GradientDrawable();
        outputGradient.setShape(GradientDrawable.RECTANGLE);
        outputGradient.setCornerRadius(20);
        outputGradient.setStroke(5, Color.BLACK);
        outputGradient.setColor(Color.BLACK);
        outputGradient.setAlpha(50);
        output.setBackground(outputGradient);
        converterLayout.addView(output);

        converterLayout.setPadding(10,10,10,10);
        converterLayout.setLayoutParams(params);

        topLayout.setPadding(20,10,20,10);
        topLayout.setLayoutParams(params);

        mainLayout.addView(topLayout);
        mainLayout.addView(converterLayout);

        setContentView(mainLayout);

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




        View.OnClickListener infoButtonListener = v -> {
            String versionName = "Unable to get Version";
            try {
                PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                versionName = packageInfo.versionName;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

            StringBuilder message = new StringBuilder();
            message.append("This is an application for converting numbers between bases.\n\n");
            message.append("Currently supported basses are: \n");

            int i = 0;
            for (Base base : Base.getBases()) {
                message.append("    ");
                if (i != Base.getBases().size()-1) {
                    message.append(base + ",\n");
                } else {
                    message.append(base + "\n");
                }
                i++;
            }

            message.append("\n\n");
            message.append("Created by DavidCMs\n");
            message.append("You are running Version: " + versionName);

            new AlertDialog.Builder(this)
                    .setTitle("Information")
                    .setMessage(message.toString())
                    .setPositiveButton("OK", (dialog, which) -> {})
                    .show();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        };
        infoButton.setOnClickListener(infoButtonListener);

        View.OnClickListener settingsButtonListener = v -> {
            LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setPadding(50, 40, 50, 40);



            Switch extendedBaseSetToggle = new Switch(this);
            extendedBaseSetToggle.setChecked(sharedPreferences.getBoolean("extendedBases", false));
            extendedBaseSetToggle.setText("Enable extended Base Set");
            layout.addView(extendedBaseSetToggle);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Settings")
                    .setView(layout)
                    .setPositiveButton("OK", (dialog, which) -> {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("extendedBases", extendedBaseSetToggle.isChecked());
                        extendedBaseSet = extendedBaseSetToggle.isChecked();
                        setDropdownMenu();
                        editor.apply();
                        convertFromDropdown.setAdapter(basesAdapter);
                        convertToDropdown.setAdapter(basesAdapter);
                    })
                    .setNegativeButton("Cancel", (dialog, which) -> {})
                    .create()
                    .show();
        };
        settingsButton.setOnClickListener(settingsButtonListener);
    }

    private void setDropdownMenu() {
        Base[] dropdownOptions;
        if (extendedBaseSet)
            dropdownOptions = Base.getBases().toArray(new Base[0]);
        else dropdownOptions = new Base[]{Base.BINARY, Base.DECIMAL, Base.HEXADECIMAL};
        basesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dropdownOptions);
    }

}