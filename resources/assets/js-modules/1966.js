__d(function(e,i,t,r){"use strict";function n(e){if(e&&e.__esModule)return e;var i={};if(null!=e)for(var t in e)Object.prototype.hasOwnProperty.call(e,t)&&(i[t]=e[t]);return i.default=e,i}Object.defineProperty(r,"__esModule",{value:!0}),r.ItineraryClickToReviewEvent=void 0;var a=i(412),o=function(e){return e&&e.__esModule?e:{default:e}}(a),u=i(694),l=n(u),s=i(1965),d=n(s),c=i(698),f=n(c);r.ItineraryClickToReviewEvent={defaultProps:{schema:"com.airbnb.jitney.event.logging.Itinerary:ItineraryClickToReviewEvent:1.0.0",event_name:"itinerary_click_to_review",operation:2,section:"review"},propTypes:{schema:o.default.string,event_name:o.default.string.isRequired,context:o.default.shape(l.Context.propTypes).isRequired,page:o.default.string.isRequired,operation:o.default.oneOf(Object.values(f.Operation)).isRequired,section:o.default.string.isRequired,parent_schedulable_info:o.default.shape(d.SchedulableInfo.propTypes).isRequired,review_id:o.default.number.isRequired}}},1966);