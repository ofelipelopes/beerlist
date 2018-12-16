package teste.great.felipelopes.punk_api.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

class HTTPs {

    private HttpsURLConnection conn;
    private InputStream mIs = null;

    synchronized void get(final String url, final Callback<String> pResponse) {

        try {
            conn = (HttpsURLConnection) new URL(url).openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");

            int statusCode = conn.getResponseCode();

            if (statusCode >= 200 && statusCode < 400) {
                mIs = conn.getInputStream();
            } else {
                mIs = conn.getErrorStream();
            }

            final String response = readInputStream(mIs);


            if (pResponse != null) {
                pResponse.onSuccess(response);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            if (mIs != null) {
                try {
                    mIs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String readInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString("UTF-8");
    }
}

