package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.PreBookingQuestion;
import com.airbnb.android.core.responses.BookingSettingsResponse;
import com.airbnb.android.utils.ListUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.FluentIterable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UpdateBookingSettingsRequest extends BaseRequestV2<BookingSettingsResponse> {
    private final long listingId;
    private final RequestBody requestBody;

    private static class RequestBody {
        @JsonProperty("booking_custom_questions")
        final List<String> bookingCustomQuestions;
        @JsonProperty("booking_standard_questions")
        final List<StandardQuestion> bookingStandardQuestions;

        private static class StandardQuestion {
            @JsonProperty("checked")
            final Boolean checked;
            @JsonProperty("type")
            final String type;

            public StandardQuestion(PreBookingQuestion preBookingQuestion) {
                this.checked = Boolean.valueOf(preBookingQuestion.isChecked());
                this.type = preBookingQuestion.getType();
            }
        }

        RequestBody(List<String> bookingCustomQuestions2, List<PreBookingQuestion> bookingStandardQuestions2) {
            List<StandardQuestion> list;
            this.bookingCustomQuestions = bookingCustomQuestions2;
            if (ListUtils.isEmpty((Collection<?>) bookingStandardQuestions2)) {
                list = new ArrayList<>();
            } else {
                list = FluentIterable.from((Iterable<E>) bookingStandardQuestions2).transform(UpdateBookingSettingsRequest$RequestBody$$Lambda$1.lambdaFactory$()).toList();
            }
            this.bookingStandardQuestions = list;
        }

        static /* synthetic */ StandardQuestion lambda$new$0(PreBookingQuestion q) {
            return new StandardQuestion(q);
        }
    }

    public UpdateBookingSettingsRequest(long listingId2, List<String> bookingCustomQuestions, List<PreBookingQuestion> bookingStandardQuestions) {
        this.listingId = listingId2;
        this.requestBody = new RequestBody(bookingCustomQuestions, bookingStandardQuestions);
    }

    public String getPath() {
        return "booking_settings/" + this.listingId;
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public Type successResponseType() {
        return BookingSettingsResponse.class;
    }

    public Object getBody() {
        return this.requestBody;
    }
}
