package fcv.tomazini.automacaoresidencial;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import fcv.tomazini.automacaoresidencial.util.ApiController;

public class MainActivity extends Activity {

	protected static final int RESULT_SPEECH = 1;

	private ProgressDialog progressDialog;

	private Button btn02on, btn02off;

	private Button btn05on, btn05off;

	private Button btn06on, btn06off;

	private Button btn08on, btn08off;

	private AutoCompleteTextView server;

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

		server = (AutoCompleteTextView) findViewById(R.id.autoCompleteServer);

		initAutoComplete();
		createListeners();
	}

	private void initAutoComplete() {
		// Get the string array
		String[] urlsServico = getResources().getStringArray(
				R.array.server_array);
		// Create the adapter and set it to the AutoCompleteTextView
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, urlsServico);
		server.setAdapter(adapter);
	}

	private void createListeners() {
		final ApiController api = new ApiController(progressDialog);

		// PIN2
		btn02on.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				api.sincronizar("pin2on", server.getText().toString());
			}
		});

		btn02off.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				api.sincronizar("pin2off", server.getText().toString());
			}
		});

		// PIN5
		btn05on.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				api.sincronizar("pin5on", server.getText().toString());
			}
		});

		btn05off.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				api.sincronizar("pin5off", server.getText().toString());
			}
		});

		// PIN6
		btn06on.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				api.sincronizar("pin6on", server.getText().toString());
			}
		});

		btn06off.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				api.sincronizar("pin6off", server.getText().toString());
			}
		});

		// PIN8
		btn08on.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				api.sincronizar("pin8on", server.getText().toString());
			}
		});

		btn08off.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				api.sincronizar("pin8off", server.getText().toString());
			}
		});
	}
}
