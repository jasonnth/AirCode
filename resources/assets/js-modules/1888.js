__d(function(e,t,i,n){"use strict";function r(e){if(e&&e.__esModule)return e;var t={};if(null!=e)for(var i in e)Object.prototype.hasOwnProperty.call(e,i)&&(t[i]=e[i]);return t.default=e,t}Object.defineProperty(n,"__esModule",{value:!0}),n.GiftingFlowClickNextRecipientEmailEvent=void 0;var a=t(412),l=function(e){return e&&e.__esModule?e:{default:e}}(a),o=t(694),u=r(o),s=t(698),c=r(s);n.GiftingFlowClickNextRecipientEmailEvent={defaultProps:{schema:"com.airbnb.jitney.event.logging.GiftingFlow:GiftingFlowClickNextRecipientEmailEvent:3.0.0",event_name:"giftingflow_click_next_recipient_email",page:"gifting_creation",section:"recipient_email",target:"next_button",operation:2},propTypes:{schema:l.default.string,event_name:l.default.string.isRequired,context:l.default.shape(u.Context.propTypes).isRequired,page:l.default.string.isRequired,section:l.default.string.isRequired,target:l.default.string.isRequired,operation:l.default.oneOf(Object.values(c.Operation)).isRequired}}},1888);