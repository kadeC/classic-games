package com.kadecarter.classicgames;
        import android.app.Activity;
        import android.app.AlertDialog;
        import android.content.DialogInterface;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.util.concurrent.ExecutionException;

public class HangMan extends Activity {
    private String secretWord = null;
    private final int MAX_TRIES = 7;
    private int attempts = 0;
    private int currImage = 0;
    private int numChars = 0;
    private int numCorr = 0;

    private int[] hangSequence = {R.drawable.hangman,R.drawable.hangman2,R.drawable.hangman3,R.drawable.hangman4,
            R.drawable.hangman5,R.drawable.hangman5,R.drawable.hangman6,R.drawable.hangman7};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hang_man);


        if(secretWord==null) {
            AsyncTask rc = new RestClient().execute();
            try {
                rc.get(); // wait for it
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        createMask();
        setCurrentImage();
        numChars = secretWord.length();
    }
    private void createMask(){
        String m="";
        for (int i=0;i<secretWord.length();i++){
            m += "*";

        }
        ((TextView)findViewById(R.id.secret)).setText(m);
    }
   private void setCurrentImage() {

       final ImageView imageView = (ImageView) findViewById(R.id.hang1);
        imageView.setImageResource(R.drawable.hangman);
       currImage++;

    }
    private void changeImage(){
        final ImageView imageView = (ImageView) findViewById(R.id.hang1);
        imageView.setImageResource(hangSequence[currImage]);

    }
    public void checkLetter(View v) {

        boolean foundMatch = false;
        TextView chkEmpty = (TextView) findViewById(R.id.txtUsrGuess);
        if (chkEmpty.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Please enter a letter",
                    Toast.LENGTH_LONG).show();
            return;
        }
        TextView tmp = (TextView)findViewById(R.id.secret);
        char[] mask = tmp.getText().toString().toCharArray();
        char c = ((EditText) findViewById(R.id.txtUsrGuess)).getText().charAt(0);

        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == c) {
                mask[i] = c;
                foundMatch = true;
            }
            tmp.setText(String.valueOf(mask));
            ((EditText)findViewById(R.id.txtUsrGuess)).setText("");
        }
        if(foundMatch) {
            numCorr++;
            checkForWin();
        }
        else if(currImage < hangSequence.length){
            changeImage();
            currImage++;
            attempts++;

        }
        if (attempts == MAX_TRIES){
            loseGame();
        }

    }
    private void checkForWin(){
        if(numCorr == numChars) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Congrats!");
            alertDialogBuilder
                    .setMessage("You Win! You Guessed " + secretWord + ". Press OK to play again!")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            resetGame();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }
    private void loseGame(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Sorry!");
        alertDialogBuilder
                .setMessage("You Lose! The word is " + secretWord + ". Press OK to play again!")
                .setCancelable(false)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        resetGame();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    private void resetGame(){
        numCorr = 0;
        numChars = 0;
        attempts = 0;
        AsyncTask rc = new RestClient().execute();
        try {
            rc.get(); // wait for it
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        createMask();
        currImage = 0;
        setCurrentImage();
    }

    private  class RestClient extends AsyncTask {

        @Override
        protected String doInBackground(Object[] params) {
            String url = "http://ec2-52-25-153-243.us-west-2.compute.amazonaws.com:8080/dictionary/word";
            URL urlc = null;
            try {
                urlc = new URL(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection con = null;
            try {
                con = (HttpURLConnection) urlc.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(secretWord = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine());

            } catch (IOException e) {
                e.printStackTrace();
            }
            return secretWord;
        }
    }
}
