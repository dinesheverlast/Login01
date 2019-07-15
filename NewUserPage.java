package sg.edu.np.s10185976.login01;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewUserPage extends AppCompatActivity {

    public static final String USER_NAME = "";
    public static final String PASSWORD = "";
    public static final String MY_GLOBAL_PREFS = "myPref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_page);


    }

    public void OnClick(View view)
    {
        Button cancel = (Button)findViewById(R.id.button2);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(NewUserPage.this, MainActivity.class);
                startActivity(j);
            }
        });

        //USER_NAME
        EditText text = findViewById(R.id.editText4);
        String userinput = text.getText().toString();

        Pattern pattern = Pattern.compile("^[A-Za-z0-9]{6,12}$");
        Matcher matcher = pattern.matcher(userinput);

        //PASSWORD
        EditText text1 = findViewById(R.id.editText5);
        String userinput1 = text1.getText().toString();

        Pattern pattern1 = Pattern.compile("^.*[!@#].*[A-Z].*[0-9].*$"); //Checks for 1st letter uppercase and the rest in lowercase.
        String regex = "^(?=.*[!@#])(?=.*[A-Z])(?=.*[0-9])[!@#A-Z0-9]{6,12}$";
        Pattern pattern2 = Pattern.compile(regex);
        Matcher matcher1 = pattern.matcher(userinput1);

        if (matcher.matches() == true && matcher1.matches() == true)
        {
            Toast.makeText(getApplicationContext(), "New User Created Successfully", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Invalid User Creation. Please Try Again", Toast.LENGTH_LONG).show();
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

    }


}
