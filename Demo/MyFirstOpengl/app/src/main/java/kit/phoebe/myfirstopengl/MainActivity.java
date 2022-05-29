package kit.phoebe.myfirstopengl;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private GLSurfaceView mGLView;
    public static float transferMatrix_param = 1.0f;
    public static int shape_type = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        // Create a GLSurfaceView instance and set it as the ContentView for this Activity.
        mGLView = new MyGLSurfaceView(this);
        setContentView(mGLView);

        Button b = new Button(this);
        b.setText( "Next Shape");
        this.addContentView(b, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT , ViewGroup.LayoutParams.WRAP_CONTENT));


        b.setOnClickListener( new View.OnClickListener()
        {
            public void onClick(View v)
            {
                mGLView.requestRender();
                shape_type++;
                if(shape_type == 4){
                    shape_type = 0;
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        //DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        transferMatrix_param = (float)((float)metrics.heightPixels / (float)metrics.widthPixels);
    }
}
