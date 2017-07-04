package sample.oguz.com.firebaseauthentication;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;

/**
 * Created by oguz on 03.07.2017.
 */
@EActivity(R.layout.activity_home)
@Fullscreen
public class HomeActivity extends Activity {

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @ViewById(R.id.nameText)
    TextView nameText;
    @ViewById(R.id.exitButton)
    Button exitButton;

    @AfterViews
    void afterViews () {
        auth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                // Eğer geçerli bir kullanıcı oturumu yoksa LoginActivity e geçilir.
                // Oturum kapatma işlemi yapıldığında bu sayede LoginActivity e geçilir.
                if (user == null) {

                    startActivity(new Intent(HomeActivity.this, MainActivity_.class));
                    finish();
                }
            }
        };
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        nameText.setText("Merhaba" + user.getEmail());

    }
    @Click(R.id.exitButton)
    void exit () {
        auth.signOut();
        startActivity(new Intent(HomeActivity.this, MainActivity_.class));
        finish();
    }
}
