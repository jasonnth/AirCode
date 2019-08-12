package retrofit2;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.ResponseBody;

public interface CallFactory<T> {
    Call create(Object... objArr) throws IOException;

    T toResponse(ResponseBody responseBody) throws IOException;
}
