package com.kadecarter.classicgames;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.HashMap;

public class TicTacToe extends AppCompatActivity {
    public boolean xTurn;
    private boolean hasWon = false;
    boolean startingPlayer = true;
    private HashMap<Integer, String> valuesMap = new HashMap<>();
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
        xTurn = true;

        button1 = (Button)findViewById(R.id.button1);
        button1.setBackgroundResource(R.drawable.blank);
        button2 = (Button)findViewById(R.id.button2);
        button2.setBackgroundResource(R.drawable.blank);
        button3 = (Button)findViewById(R.id.button3);
        button3.setBackgroundResource(R.drawable.blank);
        button4 = (Button)findViewById(R.id.button4);
        button4.setBackgroundResource(R.drawable.blank);
        button5 = (Button)findViewById(R.id.button5);
        button5.setBackgroundResource(R.drawable.blank);
        button6 = (Button)findViewById(R.id.button6);
        button6.setBackgroundResource(R.drawable.blank);
        button7 = (Button)findViewById(R.id.button7);
        button7.setBackgroundResource(R.drawable.blank);
        button8 = (Button)findViewById(R.id.button8);
        button8.setBackgroundResource(R.drawable.blank);
        button9 = (Button)findViewById(R.id.button9);
        button9.setBackgroundResource(R.drawable.blank);
    }

    public void nextMove(View v) {
        if(v == button1) {
            button1.setText("");
            button1.setBackgroundResource(getPlayerImage(xTurn));
            valuesMap.put(0, String.valueOf(xTurn));
            button1.setEnabled(false);
            checkLeftDiagRow();
            checkXTopRow();
            checkYTopRow();
        } else if(v == button2) {
            button2.setText("");
            button2.setBackgroundResource(getPlayerImage(xTurn));
            valuesMap.put(1, String.valueOf(xTurn));
            button2.setEnabled(false);
            checkXTopRow();
            checkYMiddleRow();
        } else if(v == button3) {
            button3.setText("");
            button3.setBackgroundResource(getPlayerImage(xTurn));
            valuesMap.put(2, String.valueOf(xTurn));
            button3.setEnabled(false);
            checkXTopRow();
            checkYBottomRow();
            checkRightDiagRow();
        } else if(v == button4) {
            button4.setText("");
            button4.setBackgroundResource(getPlayerImage(xTurn));
            valuesMap.put(3, String.valueOf(xTurn));
            button4.setEnabled(false);
            checkXMiddleRow();
            checkYTopRow();
        } else if(v == button5) {
            button5.setText("");
            button5.setBackgroundResource(getPlayerImage(xTurn));
            valuesMap.put(4, String.valueOf(xTurn));
            button5.setEnabled(false);
            checkXMiddleRow();
            checkYMiddleRow();
            checkLeftDiagRow();
            checkRightDiagRow();
        } else if(v == button6) {
            button6.setText("");
            button6.setBackgroundResource(getPlayerImage(xTurn));
            valuesMap.put(5, String.valueOf(xTurn));
            button6.setEnabled(false);
            checkXMiddleRow();
            checkYBottomRow();
        } else if(v == button7) {
            button7.setText("");
            button7.setBackgroundResource(getPlayerImage(xTurn));
            valuesMap.put(6, String.valueOf(xTurn));
            button7.setEnabled(false);
            checkXBottomRow();
            checkYTopRow();
            checkRightDiagRow();
        } else if(v == button8) {
            button8.setText("");
            button8.setBackgroundResource(getPlayerImage(xTurn));
            valuesMap.put(7, String.valueOf(xTurn));
            button8.setEnabled(false);
            checkXBottomRow();
            checkYMiddleRow();
        } else if(v == button9) {
            button9.setText("");
            button9.setBackgroundResource(getPlayerImage(xTurn));
            valuesMap.put(8, String.valueOf(xTurn));
            button9.setEnabled(false);
            checkXBottomRow();
            checkYBottomRow();
            checkLeftDiagRow();
        }

        if(hasWon) {
            winGame();
            return;
        }
        if(valuesMap.size() == 9) {
            tieGame();
            return;
        }

        xTurn = xTurn ? false : true;
    }
    private int getPlayerImage(boolean xTurn) {

        return xTurn ? R.drawable.x : R.drawable.o;
    }

    private void checkXTopRow() {
        if(valuesMap.get(0) == null || !valuesMap.get(0).equals(String.valueOf(xTurn))) return;
        if(valuesMap.get(1) == null || !valuesMap.get(1).equals(String.valueOf(xTurn))) return;
        if(valuesMap.get(2) == null || !valuesMap.get(2).equals(String.valueOf(xTurn))) return;
        hasWon = true;
    }

    private void checkXMiddleRow() {
        if(valuesMap.get(3) == null || !valuesMap.get(3).equals(String.valueOf(xTurn))) return;
        if(valuesMap.get(4) == null || !valuesMap.get(4).equals(String.valueOf(xTurn))) return;
        if(valuesMap.get(5) == null || !valuesMap.get(5).equals(String.valueOf(xTurn))) return;
        hasWon = true;
    }

    private void checkXBottomRow() {
        if(valuesMap.get(6) == null || !valuesMap.get(6).equals(String.valueOf(xTurn))) return;
        if(valuesMap.get(7) == null || !valuesMap.get(7).equals(String.valueOf(xTurn))) return;
        if(valuesMap.get(8) == null || !valuesMap.get(8).equals(String.valueOf(xTurn))) return;
        hasWon = true;
    }

    private void checkYTopRow() {
        if(valuesMap.get(0) == null || !valuesMap.get(0).equals(String.valueOf(xTurn))) return;
        if(valuesMap.get(3) == null || !valuesMap.get(3).equals(String.valueOf(xTurn))) return;
        if(valuesMap.get(6) == null || !valuesMap.get(6).equals(String.valueOf(xTurn))) return;
        hasWon = true;
    }

    private void checkYMiddleRow() {
        if(valuesMap.get(1) == null || !valuesMap.get(1).equals(String.valueOf(xTurn))) return;
        if(valuesMap.get(4) == null || !valuesMap.get(4).equals(String.valueOf(xTurn))) return;
        if(valuesMap.get(7) == null || !valuesMap.get(7).equals(String.valueOf(xTurn))) return;
        hasWon = true;
    }

    private void checkYBottomRow() {
        if(valuesMap.get(2) == null || !valuesMap.get(2).equals(String.valueOf(xTurn))) return;
        if(valuesMap.get(5) == null || !valuesMap.get(5).equals(String.valueOf(xTurn))) return;
        if(valuesMap.get(8) == null || !valuesMap.get(8).equals(String.valueOf(xTurn))) return;
        hasWon = true;
    }

    private void checkLeftDiagRow() {
        if(valuesMap.get(0) == null || !valuesMap.get(0).equals(String.valueOf(xTurn))) return;
        if(valuesMap.get(4) == null || !valuesMap.get(4).equals(String.valueOf(xTurn))) return;
        if(valuesMap.get(8) == null || !valuesMap.get(8).equals(String.valueOf(xTurn))) return;
        hasWon = true;
    }

    private void checkRightDiagRow() {
        if(valuesMap.get(2) == null || !valuesMap.get(2).equals(String.valueOf(xTurn))) return;
        if(valuesMap.get(4) == null || !valuesMap.get(4).equals(String.valueOf(xTurn))) return;
        if(valuesMap.get(6) == null || !valuesMap.get(6).equals(String.valueOf(xTurn))) return;
        hasWon = true;
    }

    private void resetTable() {
        button1.setBackgroundResource(R.drawable.blank);
        button2.setBackgroundResource(R.drawable.blank);
        button3.setBackgroundResource(R.drawable.blank);
        button4.setBackgroundResource(R.drawable.blank);
        button5.setBackgroundResource(R.drawable.blank);
        button6.setBackgroundResource(R.drawable.blank);
        button7.setBackgroundResource(R.drawable.blank);
        button8.setBackgroundResource(R.drawable.blank);
        button9.setBackgroundResource(R.drawable.blank);

        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
        button5.setEnabled(true);
        button6.setEnabled(true);
        button7.setEnabled(true);
        button8.setEnabled(true);
        button9.setEnabled(true);
    }

    private void winGame() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("You won!");
        String playerText = xTurn ? "X" : "O";
        alertDialogBuilder
                .setMessage("Player " + playerText + " won!")
                .setCancelable(false)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        startingPlayer = startingPlayer ? false : true;
                        xTurn = startingPlayer;
                        resetTable();
                        valuesMap.clear();
                        hasWon = false;

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void tieGame() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Tie Game");
        alertDialogBuilder
                .setMessage("It was a tie!")
                .setCancelable(false)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        resetTable();
                        startingPlayer = startingPlayer ? false : true;
                        xTurn = startingPlayer;
                        valuesMap.clear();
                        hasWon = false;

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    }

    



