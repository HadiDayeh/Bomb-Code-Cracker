package com.example.bombcodecracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Test
        Puzzle puzzle = new Puzzle(Puzzle.DIFFICULTIES[0]);
        puzzle.build();
        System.out.println(puzzle);
    }
}