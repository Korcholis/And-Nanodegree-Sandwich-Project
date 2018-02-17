package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.apache.commons.lang3.StringUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private TextView descriptionLbl;
    private TextView descriptionTv;

    private TextView placeOfOriginLbl;
    private TextView placeOfOriginTv;

    private TextView alsoKnownAsLbl;
    private TextView alsoKnownAsTv;

    private TextView ingredientsLbl;
    private TextView ingredientsTv;

    private ImageView ingredientsIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ingredientsIv = findViewById(R.id.image_iv);

        descriptionLbl = findViewById(R.id.description_lbl);
        descriptionTv = findViewById(R.id.description_tv);

        ingredientsLbl = findViewById(R.id.ingredients_lbl);
        ingredientsTv = findViewById(R.id.ingredients_tv);

        placeOfOriginLbl = findViewById(R.id.origin_lbl);
        placeOfOriginTv = findViewById(R.id.origin_tv);

        alsoKnownAsLbl = findViewById(R.id.also_known_lbl);
        alsoKnownAsTv = findViewById(R.id.also_known_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());


        if (sandwich.getDescription().isEmpty()) {
            descriptionLbl.setVisibility(View.GONE);
            descriptionTv.setVisibility(View.GONE);
        } else {
            descriptionTv.setText(sandwich.getDescription());
            descriptionLbl.setVisibility(View.VISIBLE);
            descriptionTv.setVisibility(View.VISIBLE);
        }


        if (sandwich.getPlaceOfOrigin().isEmpty()) {
            placeOfOriginLbl.setVisibility(View.GONE);
            placeOfOriginTv.setVisibility(View.GONE);
        } else {
            placeOfOriginTv.setText(sandwich.getPlaceOfOrigin());
            placeOfOriginLbl.setVisibility(View.VISIBLE);
            placeOfOriginTv.setVisibility(View.VISIBLE);
        }


        if (sandwich.getIngredients().isEmpty()) {
            ingredientsLbl.setVisibility(View.GONE);
            ingredientsTv.setVisibility(View.GONE);
        } else {
            ingredientsTv.setText(StringUtils.join(sandwich.getIngredients(), ", "));
            ingredientsLbl.setVisibility(View.VISIBLE);
            ingredientsTv.setVisibility(View.VISIBLE);
        }


        if (sandwich.getAlsoKnownAs().isEmpty()) {
            alsoKnownAsLbl.setVisibility(View.GONE);
            alsoKnownAsTv.setVisibility(View.GONE);
        } else {
            alsoKnownAsTv.setText(StringUtils.join(sandwich.getAlsoKnownAs(), ", "));
            alsoKnownAsLbl.setVisibility(View.VISIBLE);
            alsoKnownAsTv.setVisibility(View.VISIBLE);
        }
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

    }
}
