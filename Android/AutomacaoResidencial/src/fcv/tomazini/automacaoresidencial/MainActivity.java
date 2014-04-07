package fcv.tomazini.automacaoresidencial;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import fcv.tomazini.automacaoresidencial.util.ApiController;

public class MainActivity extends Activity {

	protected static final int RESULT_SPEECH = 1;

	private ProgressDialog progressDialog;

	private Button btn02on, btn02off;

	private Button btn05on, btn05off;

	private Button btn06on, btn06off;

	private Button btn08on, btn08off;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		progressDialog = new ProgressDialog(this);

		btn02on = (Button) findViewById(R.id.pin02on);
		btn02off = (Button) findViewById(R.id.pin02off);

		btn05on = (Button) findViewById(R.id.pin05on);
		btn05off = (Button) findViewById(R.id.pin05off);

		btn06on = (Button) findViewById(R.id.pin06on);
		btn06off = (Button) findViewById(R.id.pin06off);

		btn08on = (Button) findViewById(R.id.pin08on);
		btn08off = (Button) findViewById(R.id.pin08off);

		createListeners();
	}

	private void createListeners() {

		// PIN2
		btn02on.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ApiController api = new ApiController(progressDialog);
				api.sincronizar("pin2on");
			}
		});

		btn02off.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ApiController api = new ApiController(progressDialog);
				api.sincronizar("pin2off");
			}
		});

		// PIN5
		btn05on.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ApiController api = new ApiController(progressDialog);
				api.sincronizar("pin5on");
			}
		});

		btn05off.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ApiController api = new ApiController(progressDialog);
				api.sincronizar("pin5off");
			}
		});

		// PIN6
		btn06on.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ApiController api = new ApiController(progressDialog);
				api.sincronizar("pin6on");
			}
		});

		btn06off.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ApiController api = new ApiController(progressDialog);
				api.sincronizar("pin6off");
			}
		});

		// PIN8
		btn08on.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ApiController api = new ApiController(progressDialog);
				api.sincronizar("pin8on");
			}
		});

		btn08off.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ApiController api = new ApiController(progressDialog);
				api.sincronizar("pin8off");
			}
		});
	}
}
