package sg.edu.np.s10185976.login01;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    public static final String MY_GLOBAL_PREFS = "myPref";
    public static final String USER_NAME = "";
    public static final String PASSWORD = "";
    //DbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView)findViewById(R.id.newUser);

        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent i = new Intent(MainActivity.this, NewUserPage.class);
                startActivity(i);
                return true;
            }
        });



    }


    //Name (Q2)
   public void onClick(View v)
   {

       //UserName
        EditText text = findViewById(R.id.editText);
        String userinput = text.getText().toString();

        Pattern pattern = Pattern.compile("^[A-Za-z0-9]{6,12}$"); //Checks for 1st letter uppercase and the rest in lowercase.
        Matcher matcher = pattern.matcher(userinput);


//
//        TextView result = findViewById(R.id.textView);
//        result.setText("" + matcher.matches()); //matches return true/false
//
//        //Password (Q3)
//        // at least 1 symbol
//        // at least 1 uppercase
//        // at least 1 numerical

       //Password
       EditText text1 = findViewById(R.id.editText3);
       String userinput1 = text1.getText().toString();

       Pattern pattern1 = Pattern.compile("^.*[!@#].*[A-Z].*[0-9].*$"); //Checks for 1st letter uppercase and the rest in lowercase.
       String regex = "^(?=.*[!@#])(?=.*[A-Z])(?=.*[0-9])[!@#A-Z0-9]{6,12}$";
       Pattern pattern2 = Pattern.compile(regex);
       Matcher matcher1 = pattern.matcher(userinput1);

       if (matcher.matches() == true && matcher1.matches() == true)
       {
           Toast.makeText(getApplicationContext(), "Valid", Toast.LENGTH_LONG).show();
       }
       else
       {
           Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_LONG).show();
       }


       //save data of USER_NAME
       SharedPreferences.Editor editor = getSharedPreferences(MY_GLOBAL_PREFS, MODE_PRIVATE).edit();
       editor.putString(USER_NAME, userinput);
       editor.apply();

       //read data of USER_NAME
       SharedPreferences prefs = getSharedPreferences(MY_GLOBAL_PREFS, MODE_PRIVATE);
       String username = prefs.getString(USER_NAME, "");

       //save data of PASSWORD
       SharedPreferences.Editor editor1 = getSharedPreferences(MY_GLOBAL_PREFS, MODE_PRIVATE).edit();
       editor.putString(PASSWORD, userinput1);
       editor.apply();

       //read data of PASSWORD
       SharedPreferences preferences = getSharedPreferences(MY_GLOBAL_PREFS, MODE_PRIVATE);
       String password = preferences.getString(PASSWORD, "");

//        TextView result1 = findViewById(R.id.textView);
//        result.setText("" + matcher.matches()); //matches return true/false

       UserData dbUserData = new UserData();
       dbUserData.setMyUserName(USER_NAME);
       dbUserData.setMyPassword(PASSWORD);
       DbHandler.addUser(dbUserData);

    }


}
