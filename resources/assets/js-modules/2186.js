__d(function(e,t,i,o){"use strict";function r(e){if(e&&e.__esModule)return e;var t={};if(null!=e)for(var i in e)Object.prototype.hasOwnProperty.call(e,i)&&(t[i]=e[i]);return t.default=e,t}Object.defineProperty(o,"__esModule",{value:!0}),o.ReviewFlowDismissWhatToImproveEvent=void 0;var a=t(412),s=function(e){return e&&e.__esModule?e:{default:e}}(a),n=t(694),u=r(n),l=t(1965),d=r(l),p=t(698),f=r(p);o.ReviewFlowDismissWhatToImproveEvent={defaultProps:{schema:"com.airbnb.jitney.event.logging.ReviewFlow:ReviewFlowDismissWhatToImproveEvent:2.0.0",event_name:"reviewflow_dismiss_what_to_improve",page:"what_to_improve",operation:9},propTypes:{schema:s.default.string,event_name:s.default.string.isRequired,context:s.default.shape(u.Context.propTypes).isRequired,page:s.default.string.isRequired,operation:s.default.oneOf(Object.values(f.Operation)).isRequired,schedulable_info:s.default.shape(d.SchedulableInfo.propTypes).isRequired,key_words:s.default.arrayOf(s.default.string).isRequired}}},2186);