__d(function(e,t,i,n){"use strict";function r(e){if(e&&e.__esModule)return e;var t={};if(null!=e)for(var i in e)Object.prototype.hasOwnProperty.call(e,i)&&(t[i]=e[i]);return t.default=e,t}Object.defineProperty(n,"__esModule",{value:!0}),n.GiftingFlowClickCheckOutEvent=void 0;var o=t(412),u=function(e){return e&&e.__esModule?e:{default:e}}(o),a=t(694),l=r(a),f=t(1883),s=r(f),c=t(698),d=r(c);n.GiftingFlowClickCheckOutEvent={defaultProps:{schema:"com.airbnb.jitney.event.logging.GiftingFlow:GiftingFlowClickCheckOutEvent:3.0.0",event_name:"giftingflow_click_check_out",page:"gifting_creation",section:"recipient_related_input",target:"check_out_button",operation:2},propTypes:{schema:u.default.string,event_name:u.default.string.isRequired,context:u.default.shape(l.Context.propTypes).isRequired,page:u.default.string.isRequired,section:u.default.string.isRequired,target:u.default.string.isRequired,operation:u.default.oneOf(Object.values(d.Operation)).isRequired,gifting_context:u.default.shape(s.GiftingContext.propTypes).isRequired,gifting_email_error:u.default.string}}},1882);