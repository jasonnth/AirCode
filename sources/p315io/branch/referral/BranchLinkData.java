package p315io.branch.referral;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p315io.branch.referral.Defines.LinkParam;

/* renamed from: io.branch.referral.BranchLinkData */
class BranchLinkData extends JSONObject {
    private String alias;
    private String campaign;
    private String channel;
    private int duration;
    private String feature;
    private String params;
    private String stage;
    private Collection<String> tags;
    private int type;

    public void putTags(Collection<String> tags2) throws JSONException {
        if (tags2 != null) {
            this.tags = tags2;
            JSONArray tagArray = new JSONArray();
            for (String tag : tags2) {
                tagArray.put(tag);
            }
            put(LinkParam.Tags.getKey(), tagArray);
        }
    }

    public Collection<String> getTags() {
        return this.tags;
    }

    public void putAlias(String alias2) throws JSONException {
        if (alias2 != null) {
            this.alias = alias2;
            put(LinkParam.Alias.getKey(), alias2);
        }
    }

    public String getAlias() {
        return this.alias;
    }

    public void putType(int type2) throws JSONException {
        if (type2 != 0) {
            this.type = type2;
            put(LinkParam.Type.getKey(), type2);
        }
    }

    public int getType() {
        return this.type;
    }

    public void putDuration(int duration2) throws JSONException {
        if (duration2 > 0) {
            this.duration = duration2;
            put(LinkParam.Duration.getKey(), duration2);
        }
    }

    public int getDuration() {
        return this.duration;
    }

    public void putChannel(String channel2) throws JSONException {
        if (channel2 != null) {
            this.channel = channel2;
            put(LinkParam.Channel.getKey(), channel2);
        }
    }

    public String getChannel() {
        return this.channel;
    }

    public void putFeature(String feature2) throws JSONException {
        if (feature2 != null) {
            this.feature = feature2;
            put(LinkParam.Feature.getKey(), feature2);
        }
    }

    public String getFeature() {
        return this.feature;
    }

    public void putStage(String stage2) throws JSONException {
        if (stage2 != null) {
            this.stage = stage2;
            put(LinkParam.Stage.getKey(), stage2);
        }
    }

    public String getStage() {
        return this.stage;
    }

    public void putCampaign(String campaign2) throws JSONException {
        if (campaign2 != null) {
            this.campaign = campaign2;
            put(LinkParam.Campaign.getKey(), campaign2);
        }
    }

    public String getCampaign() {
        return this.campaign;
    }

    public void putParams(String params2) throws JSONException {
        this.params = params2;
        put(LinkParam.Data.getKey(), params2);
    }

    public String getParams() {
        return this.params;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        BranchLinkData other = (BranchLinkData) obj;
        if (this.alias == null) {
            if (other.alias != null) {
                return false;
            }
        } else if (!this.alias.equals(other.alias)) {
            return false;
        }
        if (this.channel == null) {
            if (other.channel != null) {
                return false;
            }
        } else if (!this.channel.equals(other.channel)) {
            return false;
        }
        if (this.feature == null) {
            if (other.feature != null) {
                return false;
            }
        } else if (!this.feature.equals(other.feature)) {
            return false;
        }
        if (this.params == null) {
            if (other.params != null) {
                return false;
            }
        } else if (!this.params.equals(other.params)) {
            return false;
        }
        if (this.stage == null) {
            if (other.stage != null) {
                return false;
            }
        } else if (!this.stage.equals(other.stage)) {
            return false;
        }
        if (this.campaign == null) {
            if (other.campaign != null) {
                return false;
            }
        } else if (!this.campaign.equals(other.campaign)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (this.duration != other.duration) {
            return false;
        }
        if (this.tags == null) {
            if (other.tags != null) {
                return false;
            }
            return true;
        } else if (!this.tags.toString().equals(other.tags.toString())) {
            return false;
        } else {
            return true;
        }
    }

    @SuppressLint({"DefaultLocale"})
    public int hashCode() {
        int hashCode;
        int hashCode2;
        int hashCode3;
        int hashCode4;
        int hashCode5;
        int i = 0;
        int i2 = 19 * (this.type + 19);
        if (this.alias == null) {
            hashCode = 0;
        } else {
            hashCode = this.alias.toLowerCase().hashCode();
        }
        int i3 = 19 * (i2 + hashCode);
        if (this.channel == null) {
            hashCode2 = 0;
        } else {
            hashCode2 = this.channel.toLowerCase().hashCode();
        }
        int i4 = 19 * (i3 + hashCode2);
        if (this.feature == null) {
            hashCode3 = 0;
        } else {
            hashCode3 = this.feature.toLowerCase().hashCode();
        }
        int i5 = 19 * (i4 + hashCode3);
        if (this.stage == null) {
            hashCode4 = 0;
        } else {
            hashCode4 = this.stage.toLowerCase().hashCode();
        }
        int i6 = 19 * (i5 + hashCode4);
        if (this.campaign == null) {
            hashCode5 = 0;
        } else {
            hashCode5 = this.campaign.toLowerCase().hashCode();
        }
        int i7 = 19 * (i6 + hashCode5);
        if (this.params != null) {
            i = this.params.toLowerCase().hashCode();
        }
        int result = (19 * (i7 + i)) + this.duration;
        if (this.tags != null) {
            for (String tag : this.tags) {
                result = (19 * result) + tag.toLowerCase().hashCode();
            }
        }
        return result;
    }

    public JSONObject getLinkDataJsonObject() {
        JSONObject linkDataJson = new JSONObject();
        try {
            if (!TextUtils.isEmpty(this.channel)) {
                linkDataJson.put("~" + LinkParam.Channel.getKey(), this.channel);
            }
            if (!TextUtils.isEmpty(this.alias)) {
                linkDataJson.put("~" + LinkParam.Alias.getKey(), this.alias);
            }
            if (!TextUtils.isEmpty(this.feature)) {
                linkDataJson.put("~" + LinkParam.Feature.getKey(), this.feature);
            }
            if (!TextUtils.isEmpty(this.stage)) {
                linkDataJson.put("~" + LinkParam.Stage.getKey(), this.stage);
            }
            if (!TextUtils.isEmpty(this.campaign)) {
                linkDataJson.put("~" + LinkParam.Campaign.getKey(), this.campaign);
            }
            if (has(LinkParam.Tags.getKey())) {
                linkDataJson.put(LinkParam.Tags.getKey(), getJSONArray(LinkParam.Tags.getKey()));
            }
            linkDataJson.put("~" + LinkParam.Type.getKey(), this.type);
            linkDataJson.put("~" + LinkParam.Duration.getKey(), this.duration);
        } catch (JSONException e) {
        }
        return linkDataJson;
    }
}
