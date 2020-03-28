package com.example.wisielecdanielowy;

import android.media.Image;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    protected String mystery_word;
    protected String guess_word;
    int gallows_state;
    String[] words;
    String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        words = getResources().getStringArray(R.array.dictionary_words);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "New word generated", Snackbar.LENGTH_LONG).show();
                int number = (int)(words.length * Math.random());
                guess_word = words[number];
                mystery_word="";
                for(int i=0; i<guess_word.length(); i++){
                    mystery_word+="*";
                }
                TextView secretView = (TextView) findViewById(R.id.secretword);
                secretView.setText(mystery_word);

                gallows_state=0;
                gallowsUpdate();
            }
        });
        gallows_state=11;
        gallowsUpdate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void checkstateLetter(View view){
        if(gallows_state<10) checkLetter(view);
    }

    public void checkstateWord(View view){
        if(gallows_state<10) checkWord(view);
    }

    public void checkLetter(View view){
        EditText answer_word = (EditText) findViewById(R.id.answer);
        answer = answer_word.getText().toString().toLowerCase();
        if(!answer.isEmpty())
        {
            char guessed_char = answer.charAt(0);
            if(guess_word.contains(answer.substring(0,1))){
                for(int i=0; i<mystery_word.length();i++)
                {
                    if(guessed_char == guess_word.charAt(i))
                    {
                        StringBuilder tmp = new StringBuilder(mystery_word);
                        tmp.setCharAt(i, guessed_char);
                        mystery_word = tmp.toString();
                        TextView secretView = (TextView) findViewById(R.id.secretword);
                        secretView.setText(mystery_word);
                        if(mystery_word.equals(guess_word)){
                            youWon(view);
                        }
                    }
                }
            }
            else{
                gallows_state++;
                gallowsUpdate();
            }
        }
    }

    public void checkWord(View view){
        EditText answer_word = (EditText) findViewById(R.id.answer);
        answer = answer_word.getText().toString().toLowerCase();
        if(!answer.isEmpty()){
            if(answer.equals(guess_word)){
                TextView secretView = (TextView) findViewById(R.id.secretword);
                secretView.setText(guess_word);
                youWon(view);
            }
            else{
                gallows_state++;
                gallowsUpdate();
            }
        }
    }

    public void youWon(View view){
        Snackbar.make(view, "You won!", Snackbar.LENGTH_LONG).show();
        gallows_state=12;
        gallowsUpdate();
    }

    public void gallowsUpdate(){
        ImageView gallows = (ImageView) findViewById(R.id.hangman);
        switch(gallows_state){
            case 0:
                gallows.setImageResource(R.drawable.hangman0);
                break;
            case 1:
                gallows.setImageResource(R.drawable.hangman1);
                break;
            case 2:
                gallows.setImageResource(R.drawable.hangman2);
                break;
            case 3:
                gallows.setImageResource(R.drawable.hangman3);
                break;
            case 4:
                gallows.setImageResource(R.drawable.hangman4);
                break;
            case 5:
                gallows.setImageResource(R.drawable.hangman5);
                break;
            case 6:
                gallows.setImageResource(R.drawable.hangman6);
                break;
            case 7:
                gallows.setImageResource(R.drawable.hangman7);
                break;
            case 8:
                gallows.setImageResource(R.drawable.hangman8);
                break;
            case 9:
                gallows.setImageResource(R.drawable.hangman9);
                break;
            case 10:
                gallows.setImageResource(R.drawable.hangman10);
                break;
            case 11:
                gallows.setImageResource(R.drawable.start);
                break;
            case 12:
                gallows.setImageResource(R.drawable.win);
                break;
        }
    }
}