__d(function(e,i,t,n){"use strict";function r(e){if(e&&e.__esModule)return e;var i={};if(null!=e)for(var t in e)Object.prototype.hasOwnProperty.call(e,t)&&(i[t]=e[t]);return i.default=e,i}Object.defineProperty(n,"__esModule",{value:!0}),n.ReviewFlowDismissPublicReviewEvent=void 0;var s=i(412),u=function(e){return e&&e.__esModule?e:{default:e}}(s),l=i(694),o=r(l),a=i(1965),d=r(a),p=i(698),v=r(p);n.ReviewFlowDismissPublicReviewEvent={defaultProps:{schema:"com.airbnb.jitney.event.logging.ReviewFlow:ReviewFlowDismissPublicReviewEvent:2.0.0",event_name:"reviewflow_dismiss_public_review",page:"public_review",operation:9},propTypes:{schema:u.default.string,event_name:u.default.string.isRequired,context:u.default.shape(o.Context.propTypes).isRequired,page:u.default.string.isRequired,operation:u.default.oneOf(Object.values(v.Operation)).isRequired,schedulable_info:u.default.shape(d.SchedulableInfo.propTypes).isRequired,review_content:u.default.string.isRequired}}},2182);