__d(function(e,i,t,a){"use strict";function n(e){if(e&&e.__esModule)return e;var i={};if(null!=e)for(var t in e)Object.prototype.hasOwnProperty.call(e,t)&&(i[t]=e[t]);return i.default=e,i}Object.defineProperty(a,"__esModule",{value:!0}),a.ReviewFlowClickLeaveAdditionalFeedbackEvent=void 0;var d=i(412),o=function(e){return e&&e.__esModule?e:{default:e}}(d),l=i(694),r=n(l),u=i(1965),s=n(u),c=i(698),f=n(c);a.ReviewFlowClickLeaveAdditionalFeedbackEvent={defaultProps:{schema:"com.airbnb.jitney.event.logging.ReviewFlow:ReviewFlowClickLeaveAdditionalFeedbackEvent:2.0.0",event_name:"reviewflow_click_leave_additional_feedback",page:"what_to_improve",operation:2,section:"additional_feedback"},propTypes:{schema:o.default.string,event_name:o.default.string.isRequired,context:o.default.shape(r.Context.propTypes).isRequired,page:o.default.string.isRequired,operation:o.default.oneOf(Object.values(f.Operation)).isRequired,section:o.default.string.isRequired,schedulable_info:o.default.shape(s.SchedulableInfo.propTypes).isRequired,key_words:o.default.arrayOf(o.default.string).isRequired}}},2187);