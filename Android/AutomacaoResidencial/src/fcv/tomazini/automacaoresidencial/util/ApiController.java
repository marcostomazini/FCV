package fcv.tomazini.automacaoresidencial.util;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import fcv.tomazini.automacaoresidencial.R;

public class ApiController {

	private ProgressDialog progressDialog;
	private String location;
	private GetApi api;

	public ApiController(ProgressDialog progressDialog) {
		this.progressDialog = progressDialog;
		location = progressDialog.getContext().getResources()
				.getString(R.string.ip);
	}

	public void sincronizar(String action) {
		api = new GetApi(location);
		new SincronizarDados().execute(action);
	}

	private class SincronizarDados extends AsyncTask<String, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog.setCancelable(false);
			progressDialog.setMessage("efetuando comunicação...");
			progressDialog.show();
		}

		@Override
		protected Void doInBackground(String... params) {
			SincronizarConsumo(params[0]);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			progressDialog.dismiss();
		}

		private Void SincronizarConsumo(String action) {
			api.getFromApi(action);
			return null;
		}
	}
}
