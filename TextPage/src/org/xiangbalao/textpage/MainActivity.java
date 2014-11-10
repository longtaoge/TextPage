package org.xiangbalao.textpage;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

  private TextPage mTextPage;
  private TextView textView1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextPage =(TextPage) findViewById(R.id.mTextPage);
        textView1 =(TextView) findViewById(R.id.textView1);
        
        mTextPage.setOnTextChangedListener(new OnTextChangedListener() {
			
			@Override
			public void onTextChanged(String text) {
				textView1.setText(text);
				
			}
		});
       
        
        
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
